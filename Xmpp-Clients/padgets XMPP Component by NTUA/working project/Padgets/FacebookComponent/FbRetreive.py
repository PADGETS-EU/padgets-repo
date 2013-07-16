'''
Created on 10 05 2011

@author: PanosSp
'''
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
import time


APP_ID = '121051994628297'
APP_SECRET = '15eba27bb4b8e2ac6056190bb5eb5a89'
ENDPOINT = 'graph.facebook.com'
REDIRECT_URI = 'http://147.102.6.34:8080/'
ACCESS_TOKEN = None
LOCAL_FILE = '.fb_access_token10'
STATUS_TEMPLATE = u"{name}\033[0m: {message}"


class FacebookRetreive(object):
   


    def __init__(self):
        '''
        Constructor
        '''
        
    def retreive(self,id,xmpp):
        ACCESS_TOKEN = open(LOCAL_FILE).read()
        graph = facebook.GraphAPI(ACCESS_TOKEN)
        profile = graph.get_object("me")   
        #feed = graph.get_connections(profile["id"],"feed")
        l_1=-1
        l_2=-1
        while True :                                            
                comments_feed = graph.get_connections(id,'comments')  
                likes_feed = graph.get_connections(id,'likes')   
                try :   
                    l1=len(comments_feed['data'])                      
                    l2=len(likes_feed['data'])
                    if (l1>l_1) :                    
                        print comments_feed['data'][l1-1]['from']['name']+' commented : '+comments_feed['data'][l1-1]['message']                
                        xmpp.sendMessage('hub.gic',comments_feed['data'][l1-1]['from']['name']+' commented : '+comments_feed['data'][l1-1]['message'])
                        l_1=l1                 
                except:
                    print 'not any comments yet'
                try :                    
                    if (l2>l_2) :
                        print likes_feed['data'][l2-1]['name']+' liked your post! '
                        xmpp.sendMessage('hub.gic',likes_feed['data'][l2-1]['name']+' liked your post! ')
                        l_2=l2   
                except:
                    print 'not any likes yet'
                time.sleep(0.5)
                
            
        #for feed in thefeed['data']:
         #   print feed['from']['name'],':',feed['message']
            
            
        
#FacebookRetreive().retreive("posts")            
print 'completed'           
            
            
            