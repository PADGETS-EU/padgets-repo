'''
Created on 2 Dec 2011

@author: mpetyx
'''

import feedparser



class Feeds():
    
    def __init__(self):
        #self.feed = feedparser.parse("http://www.mahalo.com/search/?q=feed")
        mahalo_url = "http://www.mahalo.com/search/?q=feed"
        #url = 'http://search.twitter.com/search.json?q=%40twitterapi'
        #mpetyx = "http://twitter.com/statuses/user_timeline/mpetyx.rss"
        self.feed = feedparser.parse(mahalo_url)

                
    def feedaki(self):
        return self.feed['feed']['summary']
    
    
#print Feeds().feedaki()