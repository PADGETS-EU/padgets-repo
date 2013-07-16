
import os
import random
from twisted.trial import unittest

from twisted.web import server, resource, static

from twisted.internet import defer, reactor

from twisted.words.xish import domish

from punjab.httpb import HttpbService
from punjab.xmpp import server as xmppserver
from punjab import httpb_client

class DummyTransport:
    
    def __init__(self):
        self.data = []
 	       
    def write(self, bytes):
        self.data.append(bytes)
 	
    def loseConnection(self, *args, **kwargs):
        self.data = []

class TestCase(unittest.TestCase):
    """Basic test class for Punjab 
    """

    def setUp(self):
        # set up punjab
        try:
            os.mkdir("./html") # create directory in _trial_temp
        except:
            print "The directory already exists!"
        self.root = static.File("./html") # make _trial_temp/html the root html directory
        self.rid = random.randint(0,10000000)
        self.hbs = HttpbService(1)
        self.b = resource.IResource(self.hbs)
        self.root.putChild('xmpp-bosh', self.b)

        self.site  = server.Site(self.root)
        
        self.p =  reactor.listenTCP(0, self.site, interface="127.0.0.1")
        self.port = self.p.getHost().port

        # set up proxy
        
        self.proxy = httpb_client.Proxy(self.getURL())
        self.sid   = None
        self.keys  = httpb_client.Keys()

        print "set up dummy xmpp server"
        
        self.server_service = xmppserver.XMPPServerService()
        self.server_factory = xmppserver.IXMPPServerFactory(self.server_service)
        self.server = reactor.listenTCP(5222, self.server_factory, interface="127.0.0.1")

        # Hook the server's buildProtocol to make the protocol instance
        # accessible to tests.
        buildProtocol = self.server_factory.buildProtocol
        d1 = defer.Deferred()
        def _rememberProtocolInstance(addr):
            self.server_protocol = buildProtocol(addr)
            # keeping this around because we may want to wrap this specific to tests
            # self.server_protocol = protocol.wrappedProtocol
            d1.callback(None)
            return self.server_protocol
        self.server_factory.buildProtocol = _rememberProtocolInstance


    def getURL(self, path = "xmpp-bosh"):
        return "http://127.0.0.1:%d/%s" % (self.port, path)


    def key(self,b):
        if self.keys.lastKey():
            self.keys.setKeys()
        
        if self.keys.firstKey():
            b['newkey'] = self.keys.getKey()
        else:
            b['key'] = self.keys.getKey()
        return b 

    def resend(self, ext = None):
        self.rid = self.rid - 1
        return self.send(ext)

    def send(self, ext = None, sid = None, rid = None):
        self.rid = self.rid + 1
        if sid is None:
            sid = self.sid
        if rid is None:
            rid = self.rid
        b = domish.Element(("http://jabber.org/protocol/httpbind","body"))
        b['content']  = 'text/xml; charset=utf-8'

        b['rid']      = str(rid)
        b['sid']      = str(sid)
        b['xml:lang'] = 'en'

        if ext is not None:
            if isinstance(ext, domish.Element):
                b.addChild(ext)
            else:
                b.addRawXml(ext)

        b = self.key(b)
        d = self.proxy.send(b)
        return d

        

    def _error(self, e):
        # self.fail(e)
        pass

    def _cleanPending(self):
        pending = reactor.getDelayedCalls()
        if pending:
            for p in pending:
                if p.active():
                    p.cancel()

    def _cleanSelectables(self):
        removedSelectables = reactor.removeAll()
        # Below is commented out to remind us how to see what selectable is sticking around
        #if removedSelectables:
        #    for sel in removedSelectables:
        #        # del sel
        #        print sel.__class__
        #        print dir(sel)
        
    def tearDown(self):
        def cbStopListening(result=None):
            
            self.root = None
            self.site = None
            self.proxy.factory.stopFactory()
            self.server_factory.stopFactory()            
            self.server = None
            self._cleanPending()
            self._cleanSelectables()

        os.rmdir("./html") # remove directory from _trial_temp
        self.b.service.poll_timeouts.stop()
        self.b.service.stopService()
        self.p.stopListening()
        for s in self.b.service.sessions.keys():
            sess = self.b.service.sessions.get(s)
            if sess:
                self.b.service.endSession(sess)
        if hasattr(self.proxy.factory,'client'):
            self.proxy.factory.client.transport.stopConnecting()
        

        d = defer.maybeDeferred(self.server.stopListening)
        d.addCallback(cbStopListening)

        return d
        

yo = TestCase()
yo.setUp()
print yo.getURL()
import time
while 1:
    time.sleep(1)