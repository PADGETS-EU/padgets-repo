'''
Created on Apr 7, 2011

@author: michaelpetuxakis
'''


class User:
    
    def __init__(self, Person=None):
        
        import storing
        self.api  = None
        self.storing = storing.store()
        
        self.consumer_key = "DQdFKO3c7hn6VBB7hK7rw"
        self.consumer_secret = "o5oCXeugeW2RktnqlzS2czwlWDK0nZXPvRE0Ma9s"
        
        if Person==None:
            
            self.Person = self.new_user()
        else:
            self.Person = Person
            
        existing =  self.exists(self.Person)
        
        if not existing:
            self.Person = self.new_user()
            existing = self.exists(self.Person)
        
        import re 
        res = re.findall("key=(.*)PAUSEsecret=(.*)",existing)
        res = res[0]
        res = {'key':res[0],'secret':res[1]}
        self.key = res['key']
        self.secret = res['secret']
        
    def key(self):
        return self.key
    
    def secret(self):
        return self.secret    
            
    def exists(self, Person=None):
        """
        Here we check our database to see if we have interacted with the person again and return
        his credentials
        """
        
        
        if not self.storing.search_for_item(Person):
            return 0
        else:
            return self.storing.load(Person)
    
    def new_user(self, Person=None):
        """
        Here we are a new user with his credentials
        """
        import tweepy
        import webbrowser

        consumer_key = "DQdFKO3c7hn6VBB7hK7rw"
        consumer_secret = "o5oCXeugeW2RktnqlzS2czwlWDK0nZXPvRE0Ma9s"
        auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
        webbrowser.open(auth.get_authorization_url())

        pin = raw_input('Verification pin number from twitter.com: ').strip()
        token = auth.get_access_token(verifier=pin)
        api = tweepy.API(auth)
        self.api = api
        name = api.me().screen_name
        self.storing.save("%s\n%s\n%s\n"%(name,token.key,token.secret))
        return name
    
    def setUp(self):
        self.api.retry_count = 2
        self.api.retry_delay = 5
        