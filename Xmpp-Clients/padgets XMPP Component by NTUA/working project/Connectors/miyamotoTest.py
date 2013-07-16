'''
Created on Mar 21, 2011

@author: michaelpetuxakis
'''

#!/usr/bin/python
import sys

from twisted.internet import reactor
from twisted.web.server import Site
from twisted.python import log
from optparse import OptionParser

sys.path.append('.')

from miyamoto.web import MiyamotoResource

parser = OptionParser()
parser.add_option('--port', '-p',
                  help="Port number for Miyamoto's HTTP server (default 8080)",
                  default=8081, type='int')
parser.add_option('--verbose', '-v', help="Show http activity",
                  action="store_true")
opts, args = parser.parse_args()

if opts.verbose:
    log.startLogging(sys.stdout)

reactor.listenTCP(opts.port, Site(MiyamotoResource.setup()))
reactor.run()

