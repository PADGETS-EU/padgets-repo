#!/usr/bin/env python

import logging
from SimpleBackend import SimpleBackend
from HTTPFrontend import HTTPFrontend
from ConfigurableBot import ConfigurableBot

# Uncomment the following line to turn on debugging
#logging.basicConfig(level=logging.DEBUG, format='%(levelname)-8s %(message)s')

def main() :
	backend = SimpleBackend()
	bot = ConfigurableBot("test@gic", "test", backend, server="147.102.6.34")
	bot.start()
	httpFrontend = HTTPFrontend(8080, backend)
	httpFrontend.start()

if __name__ == '__main__' :
	main()
