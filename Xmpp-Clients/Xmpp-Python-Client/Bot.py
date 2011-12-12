'''
Created on 2 Dec 2011

@author: mpetyx
'''

#!/usr/bin/env python

import sys
#sys.path.append("../3rdParty")
import logging
import sleekxmpp

# Uncomment the following line to turn on debugging
#logging.basicConfig(level=logging.DEBUG, format="%(levelname)-8s %(message)s")

def main() : 
  bot = EchoBot("mpetyx@gic", "emp")
  bot.run() 

class EchoBot : 
  def __init__(self, jid, password) : 
    self.xmpp = sleekxmpp.ClientXMPP(jid, password) 
    self.xmpp.add_event_handler("session_start", self.handleXMPPConnected) 
    self.xmpp.add_event_handler("message", self.handleIncomingMessage) 

  def run(self) : 
    self.xmpp.connect(("147.102.6.34",5222)) 
    self.xmpp.process(threaded=False) 

  def handleXMPPConnected(self, event): 
    self.xmpp.sendPresence(pstatus = "Send me a message")

  def handleIncomingMessage(self, message) : 
    print "elava auto "+message["body"]+" apo auton "
    self.xmpp.reply(message['body'])

if __name__ == "__main__" :
  main()
