'''
Created on Mar 21, 2011

@author: michaelpetuxakis
'''

from SimpleBackend import SimpleBackend
from HTTPFrontend import HTTPFrontend
import sys
'''sys.path.append("../3rdParty")'''
import sleekxmpp.componentxmpp
import sleekxmpp.plugins.xep_0030
import sleekxmpp.plugins.xep_0060

def main() :   
    component = SimpleComponent(jid="test2", password="test2xm")    
    component.start()
    '''httpFrontend = HTTPFrontend(8080, backend)'''
    '''httpFrontend.start()'''


class SimpleComponent :
  def __init__(self, jid, password) :
    
      self.xmpp = sleekxmpp.componentxmpp.ComponentXMPP(jid, password,"147.102.6.34",5060)
      self.xmpp.add_event_handler("session_start", self.handleXMPPConnected)
      self.xmpp.add_event_handler("message", self.handleIncomingMessage)
      self.xmpp.add_event_handler("publish", self.handlePublishEvent)
      '''try:
          self.xmpp.register_plugin("xep_0060")
      except: 
          print "error registering plugin!"
      print "plugin registered!"
      try:
          
      except:
          print "failed to create new node!" '''
          
  def handlePublishEvent(self):
      print "someone just published..."
      
                 

  def handleXMPPConnected(self, event) :
      print "handling of connection" 
      '''self.xmpp.sendPresence(pstatus = "Send me a message")  
      self.xmpp.send_presence(pshow=None,pstatus=None ,ppriority=None,pto="panos@gic",ptype="subscribe",pfrom="component@test2.gic" ) 
      self.xmpp.send_presence(pshow=None,pstatus=None ,ppriority=None,pto="panos@gic",ptype="subscribed", pfrom="component@test2.gic")'''    
      self.xmpp.registerPlugin("xep_0030")
      self.xmpp.registerPlugin("xep_0060")
      self.xmpp.plugin["xep_0060"].createNode("test2.gic","pubsubnode")
      '''self.xmpp.plugin["xep_0060"].subscribe("test3@gic","pubsubnode",True,"test2.gic")'''    
      self.xmpp.plugin["xep_0060"].setItem("test2.gic","pubsubnode",items=["item_1"])
      print self.xmpp.plugin["xep_0060"].getItems("test2.gic","pubsubnode")
      
  def handleXMPPPresenceSubscription(self, subscription) :
      if subscription["type"] == "subscribe" :
          userJID = subscription["from"]
          self.xmpp.send_presence_subscription(pto=userJID, ptype="subscribed")
          self.xmpp.send_presence(pto = userJID)
          self.xmpp.send_presence_subscription(pto=userJID, ptype="subscribe")
          
  def handleXMPPPresenceProbe(self, event) :
      self.xmpp.sendPresence(pto = event["from"])
    
    
  def handleIncomingMessage(self, message):   
      self.xmpp.send_presence(pshow="online",pstatus="available" ,ppriority=None,pto="panos@gic",ptype="subscribed", pfrom="component@test2.gic") 
      print message["from"]," :",message["body"]
      to = message["from"] 
      '''self.xmpp.send_presence("online", "available" , None,"panos@gic", "component@test2.gic", "subscribe")'''
      self.xmpp.send_message( "panos@gic/b82612c0" , "just answering..." ) 
      
  def handleIncomingPresence (self,presence):  
      print "handling subscription request"    
         

  def start(self) :
    try:
        self.xmpp.connect()       
        print "connected" 
    except:
        print "error connecting / creating plugin"
    self.xmpp.process()                
       
    

if __name__ == "__main__" :
  main()