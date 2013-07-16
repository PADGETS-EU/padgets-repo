'''
Created on Mar 16, 2011

@author: michaelpetuxakis
'''
#import sys
#sys.path.append("../3rdParty")
import sleekxmpp

class Bot :
    def __init__(self, jid, password, backend, url) :
        self.url = url
        self.xmpp = sleekxmpp.ClientXMPP(jid, password)
        self.xmpp.add_event_handler("session_start", self.handleXMPPConnected)
        for event in ["message", "got_online", "got_offline", "changed_status"] :
            self.xmpp.add_event_handler(event, self.handleIncomingXMPPEvent)
        self.backend = backend
        self.backend.addMessageHandler(self.handleMessageAddedToBackend)

    def handleXMPPConnected(self, event):
        self.xmpp.sendPresence()

    def handleIncomingXMPPEvent(self, event):
        msgLocations = {sleekxmpp.stanza.presence.Presence: "status",
                        sleekxmpp.stanza.message.Message: "body"}

        message = event[msgLocations[type(event)]]
        user = self.backend.getUserFromJID(event["from"].jid)
        if user is not None:
                self.backend.addMessageFromUser(message, user)
  
    def handleMessageAddedToBackend(self, message) :
        body = message.user + ": " + message.text
        htmlBody = "<p><a href=\"%(uri)s\">%(user)s</a>: %(message)s</p>" % {
      "uri": self.url + "/" + message.user,
      "user" : message.user, "message" : message.text }
        for subscriberJID in self.backend.getSubscriberJIDs(message.user) :
            self.xmpp.sendMessage(subscriberJID, body, mhtml=htmlBody)
  
    def start(self) :
        self.xmpp.connect(("147.102.6.34",5222))
        self.xmpp.process()