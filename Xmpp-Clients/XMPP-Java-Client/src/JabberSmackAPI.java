

import java.util.*;
import java.io.*;
import java.net.*;
import main.ActivityStreamsEntryType;
import main.JythonObjectFactory;
import java.util.regex.*;

import javax.xml.parsers.DocumentBuilderFactory;
 
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.w3c.dom.Element;
 
public class JabberSmackAPI implements MessageListener {
	
    XMPPConnection connection=null;
 
    public void login(String userName, String password) throws XMPPException
    {
    	ConnectionConfiguration config = new ConnectionConfiguration(configureClient.XMPPdomain,configureClient.XMPPport, configureClient.XMPPservice);
    	//config.setCompressionEnabled(true);
    	//config.setSASLAuthenticationEnabled(false);
    	connection = new XMPPConnection(config);
 
    	connection.connect();
    	connection.login(userName, password);
    }
 
    public void sendMessage(String message, String to) throws XMPPException
    {
    	Chat chat = connection.getChatManager().createChat(to, this);
    	chat.sendMessage(message);
    }
    
    public void sendMessage(Message message, String to) throws XMPPException
    {
    	Chat chat = connection.getChatManager().createChat(to, this);
    	chat.sendMessage(message);
    }
 
    public void displayBuddyList()
    {
    	Roster roster = connection.getRoster();
    	Collection<RosterEntry> entries = roster.getEntries();
 
    	System.out.println("\n\n" + entries.size() + " buddy(ies):");
    	for(RosterEntry r:entries)
    	{
    		System.out.println(r.getUser());
    	}
    }
 
    public void disconnect()
    {
    connection.disconnect();
    }
 
    public void processMessage(Chat chat, Message message)
    {
    if(message.getType() == Message.Type.chat)
    System.out.println(chat.getParticipant() + " says: " + message.getBody());
    }
 
    public static void main(String args[]) throws XMPPException, IOException
    {
    	
    //test AS Creator
    JythonObjectFactory factory = new JythonObjectFactory(ActivityStreamsEntryType.class, "activitystreamsentry", "ActivityStreamsEntry");

    ActivityStreamsEntryType building = (ActivityStreamsEntryType) factory.createObject();
  
    //building.addTargetPlatform("facebook");
    building.addAuthor("Iosif Alvertis", "padgets:http://www.ppadgets.eu/alvertisjo", "342342342", "http://www.ppadgets.eu/alvertisjo");
    //building.addObject("id", "name", "url", "ARTICLE", "summary",  "addressLocality", "addressRegion", "addressPostalCode", "addressCountry", "embedCode", "mediaType", "mediaDuration", "mediaWidth", "mediaHeight", "mediaRel", "true");
    String msg=building.getAtom();
    //Debug Output AS
    System.out.println(msg);


    // declare variables
    JabberSmackAPI c = new JabberSmackAPI();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //XMPPConnection connection = new XMPPConnection("jabber.org");
    //connection.login(configureClient.clientUsername, configureClient.clientPassword);
 
    // turn on the enhanced debugger
    XMPPConnection.DEBUG_ENABLED = true;
 
   
    // Enter your login information here
    c.login( configureClient.clientUsername,  configureClient.clientPassword);
 
    c.displayBuddyList();
 
    System.out.println("-----");
 
    String talkTo=configureClient.pubsubUsername;
 
    System.out.println("-----");
    System.out.println("All messages will be sent to " + talkTo);
    System.out.println("-----\n");
    
    
    
    msg = msg.replaceAll("&gt;",">");
    msg = msg.replaceAll("&lt;","<");
    
    //System.out.println(msg);
    
   //testing on objects (messages)
    Message message = new Message();
    message.setTo(talkTo);
    message.setSubject("New notification");
    message.setBody(msg);
    //message.setDefaultXmlns("http://www.w3.org/2005/Atom");
    message.setType(Message.Type.chat);
    //message.addBody(null, msg);
    //message.addExtension(new MessagePacketExtension(getTypeNotification(chat)));                           
   
   // message.createReturnExtension(String, String, Map, List) 
    
    //c.sendPacket(message);
    //connection.close();
    
    
    c.sendMessage(msg, talkTo);
    c.sendMessage(message, talkTo);
    
    
    //exit message from the commandline
    while( !(msg=br.readLine()).equals("bye"))
    {
    	System.out.println(msg);
        c.sendMessage(msg, talkTo);
    }
 
    c.disconnect();
    System.exit(0);
    }
 
}