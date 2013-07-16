#!/usr/bin/env python

import logging
from SimpleBackend import SimpleBackend
from HTTPFrontend import HTTPFrontend
from RegistrableComponent import RegistrableComponent

# Uncomment the following line to turn on debugging
#logging.basicConfig(level=logging.DEBUG, format='%(levelname)-8s %(message)s')

def main() :
	backend = SimpleBackend()
	backend.jidToUser = {}
	backend.userToJID = {}
	component = RegistrableComponent(
		jid = "test@gic", password = "test",
		server = "147.102.6.34", port = 5222, backend = backend)
	component.start()
	httpFrontend = HTTPFrontend(8080, backend)
	httpFrontend.start()

if __name__ == '__main__' :
	main()
