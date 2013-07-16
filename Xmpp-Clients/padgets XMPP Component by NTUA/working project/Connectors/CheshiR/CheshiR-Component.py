#!/usr/bin/env python

import logging
from SimpleBackend import SimpleBackend
from HTTPFrontend import HTTPFrontend
from Component import Component

# Uncomment the following line to turn on debugging
#logging.basicConfig(level=logging.DEBUG, format='%(levelname)-8s %(message)s')

def main() :
	backend = SimpleBackend()
	component = Component(
		jid = "test@gic", password = "test",
		server = "147.102.6.34", port = 5222, backend = backend)
	component.start()
	httpFrontend = HTTPFrontend(8081, backend)
	httpFrontend.start()

if __name__ == '__main__' :
	main()
