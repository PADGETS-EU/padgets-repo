#Created by EPU NTUA

The echobot.js establishes the communication with XMPP, through Strophe.js library, and then it waits to receive a new message from the server. Then the ActivityStreams message is parsed and presented as html tags.

The .htaccess helps to redirect to the proper Proxy Server.

The .txt files are for testing purposes, to test the messages read on the client.