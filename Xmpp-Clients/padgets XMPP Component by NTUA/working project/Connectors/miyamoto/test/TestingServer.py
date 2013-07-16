'''
Created on Mar 22, 2011

@author: michaelpetuxakis
'''
import sys

from twisted.internet import reactor
from twisted.web.server import Site
from twisted.python import log

sys.path.append('.')

#from miyamoto.web import MiyamotoResource
from miyamoto.test import test_pubsub
MyStuff = test_pubsub.TestPubSub()


print "yo yo yo"

MyStuff.setUp()
MyStuff.testPublisherMock()
MyStuff.testSubscriberMock()
MyStuff.tearDown()
#reactor.listenTCP(8080, Site(MyStuff.setUp()))
#reactor.run()
