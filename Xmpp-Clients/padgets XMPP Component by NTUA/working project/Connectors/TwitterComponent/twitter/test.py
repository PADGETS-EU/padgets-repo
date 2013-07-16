'''
Created on Apr 7, 2011

@author: michaelpetuxakis
'''

import FullAccess
import tweepy

temp = FullAccess.User("petmiker")
consumer_key = "DQdFKO3c7hn6VBB7hK7rw"
consumer_secret = "o5oCXeugeW2RktnqlzS2czwlWDK0nZXPvRE0Ma9s"
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(temp.key, temp.secret)
api = tweepy.API(auth)
api.update_status("My first app test!")

