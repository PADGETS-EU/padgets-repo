'''
Created on 2 Dec 2011

@author: mpetyx
'''

#!/usr/bin/env python
# -*- coding: utf-8 -*-



import ssl
import sleekxmpp

# Python versions before 3.0 do not use UTF-8 encoding
# by default. To ensure that Unicode is handled properly
# throughout SleekXMPP, we will set the default encoding
# ourselves to UTF-8.


class SendMsgBot(sleekxmpp.ClientXMPP):

    """
    A basic SleekXMPP bot that will log in, send a message,
    and then log out.
    """

    def __init__(self, jid, password, recipient, message):
        sleekxmpp.ClientXMPP.__init__(self, jid, password)

        self.recipient = recipient
        self.msg = message

        self.add_event_handler("session_start", self.start)

    def start(self, event):

        self.send_presence()
        self.get_roster()
        self.send_message(mto=self.recipient,
                              mbody="connect",
                              mtype='chat')
        
        from Rss import Feeds
        from time import sleep
        from Messages import Messages
        i = 0
        while True:
            i = i + 1
            sleep(15)
            feed = Feeds().feedaki()
            #self.msg = "publish:face3 "+feed
            self.msg = "publish:face3 I am on my "+str(i)+"  cycle!"
            self.send_message(mto=self.recipient,
                              #mbody=self.msg,
                              mbody=Messages().choose_message(),
                              mtype='chat')
        # Using wait=True ensures that the send queue will be
        # emptied before ending the session.
        self.disconnect(wait=True)


if __name__ == '__main__':

    # Setup the EchoBot and register plugins. Note that while plugins may
    # have interdependencies, the order in which you register them does
    # not matter.
    jid = "mpetyx@gic"
    password = "emp"
    #to = "publishsubscribe@facebooknode.gic"
    to= "initiator1@gic"
    message = "magkas2"
    xmpp = SendMsgBot(jid, password, to, message)
    xmpp.register_plugin('xep_0030') # Service Discovery
    xmpp.register_plugin('xep_0199') # XMPP Ping

    # If you are working with an OpenFire server, you may need
    # to adjust the SSL version used:
    xmpp.ssl_version = ssl.PROTOCOL_SSLv3

    # If you want to verify the SSL certificates offered by a server:
    # xmpp.ca_certs = "path/to/ca/cert"

    # Connect to the XMPP server and start processing XMPP stanzas.
    if xmpp.connect(("147.102.6.34",5222)):
        # If you do not have the pydns library installed, you will need
        # to manually specify the name of the server if it does not match
        # the one in the JID. For example, to use Google Talk you would
        # need to use:
        #
        # if xmpp.connect(('talk.google.com', 5222)):
        #     ...
        
        xmpp.process(threaded=False)
        print("Done")
    else:
        print("Unable to connect.")
