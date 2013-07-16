import time
import thread
import sys
import sleekxmpp
import logging
import msvcrt


def main() :
 client = BaseClient("bg_comp@gic", "bgcomp")
 client.run()
 client.publishtoPUBSUB()
 thread.start_new_thread(client.sendMsgCmd, ())

class BaseClient :

  def __init__(self, jid, password) :
    self.xmpp = sleekxmpp.ClientXMPP(jid, password)
    self.xmpp.add_event_handler("session_start", self.handleXMPPConnected)
    self.xmpp.add_event_handler("message", self.handleIncomingMessage)
    
  def run(self) :
   print "let's connect!"
   try:
      self.xmpp.connect(("147.102.6.34",5222))
   except:
      print "oops!"
   print "connected"
   self.xmpp.process(threaded=True)
   
   
  def publishtoPUBSUB(self):
   self.xmpp.register_plugin("xep_0030") 
   self.xmpp.register_plugin("xep_0060")   
   self.xmpp.plugin["xep_0060"].setItem("test2.gic","fb_node")
   
    
  def handleXMPPConnected(self, event):
   self.xmpp.sendPresence(pstatus = "Send me a message")
   #self.xmpp.send_presence_subscription("panos@gic", ptype="subscribed")  
    
 
  def sendMsgCmd(self):
   input1 = raw_input("insert the user's jid :")   
   while True:
      time.sleep(0.5)
      input2 =raw_input("me :")      
      self.xmpp.sendMessage(input1,input2)   

        
  def handleIncomingMessage(self, message):  
   print " "   
   print message["from"]," :",message["body"]
   from BloggerConnector import BloggerConnector
   blog = BloggerConnector.BloggerConnector('6524405923717584259','gic.epu.test@gmail.com','g1c.3pu.t3st')
   blog.CreatePost(title='titleIsHere', content=message["body"], labels=['tag1','tag2'],is_draft='false')
   
if __name__ == "__main__" :
  main()
