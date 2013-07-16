'''
Created on 05 2011

@author: PanosSp
'''

#!/usr/bin/python2.6

import os.path
import json
import urllib2
import urllib
import urlparse
import BaseHTTPServer
import webbrowser
import facebook
import argparse
import sys

APP_ID = '121051994628297'
APP_SECRET = '15eba27bb4b8e2ac6056190bb5eb5a89'
ENDPOINT = 'graph.facebook.com'
REDIRECT_URI = 'http://147.102.6.34:8080/'
ACCESS_TOKEN = None
LOCAL_FILE = '.fb_access_token10-'
STATUS_TEMPLATE = u"{name}\033[0m: {message}"

class FacebookPublish:
    
    def __init__(self,message=None):
        self.message = message
        
    def publish(self,message,xmpp):
        ACCESS_TOKEN = open(LOCAL_FILE).read()
        graph = facebook.GraphAPI(ACCESS_TOKEN)
        profile = graph.get_object("me") 
        post = graph.put_object("me", "feed", message=message)
        print post['id']
        from FbRetreive import FacebookRetreive
        FacebookRetreive().retreive(post['id'],xmpp)

