'''
Created on Apr 7, 2011

@author: michaelpetuxakis
'''

class friends:
    
    def __init__(self,Person=None):
        
        import tweepy
        
        user = tweepy.api.get_user(Person)
        
        for friend in user.friends():
            print friend.screen_name
            
#friends("petmiker")