'''
Created on Apr 28, 2011

@author: michaelpetuxakis
'''

class publishAtTwitter:
    
    def __init__(self, message=None):
        
        self.message = message
        
        
        
        
    def publish(self, user, status):
        
        if ( user==None) or (status==None):
            return 0
        
        from twitter import FullAccess
        
        import tweepy

        temp = FullAccess.User(user)
        consumer_key = "DQdFKO3c7hn6VBB7hK7rw"
        consumer_secret = "o5oCXeugeW2RktnqlzS2czwlWDK0nZXPvRE0Ma9s"
        auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
        auth.set_access_token(temp.key, temp.secret)
        api = tweepy.API(auth)
        s = api.update_status(status)
        """
        Here we store the result of the status that we published
        store( s.id, "status",account)
        
        
        """
        return 1
        