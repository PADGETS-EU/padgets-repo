'''
Created on Mar 14, 2011

@author: michaelpetuxakis
'''

import sleekxmpp
import logging

def main() :
	bot = EchoBot("mpetyx@gic", "emp")
  	bot.run()

class EchoBot :
  def __init__(self, jid, password) :
    self.xmpp = sleekxmpp.ClientXMPP(jid, password)
    self.xmpp.add_event_handler("session_start", self.handleXMPPConnected)
    self.xmpp.add_event_handler("message", self.handleIncomingMessage)

  def run(self) :
  	print "let's connect!"
  	try:
   		self.xmpp.connect(("147.102.6.34",5222))
   	except:
   		print "fuck"
   	print "Connected!!!!!"
   	self.xmpp.process(threaded=False)

  def handleXMPPConnected(self, event):
    self.xmpp.sendPresence(pstatus = "Send me a message")

  def handleIncomingMessage(self, message):
  	print message["body"]
  	self.xmpp.sendMessage(message["from"], message["body"])

if __name__ == "__main__" :
  main()
