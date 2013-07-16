'''
Created on 16 May 2011

@author: GIC
'''

class Retrieve:
    
    def __init__(self):
        
        from BloggerConnector import BloggerConnector
        self.blog = BloggerConnector('6524405923717584259','gic.epu.test@gmail.com','g1c.3pu.t3st')
        
    def comments_on_post(self, post_id):
        
        comments = self.blog.client.get_post_comments(self.blog.blog_id, post_id = post_id)
        #print "\n"
        #print comments
        #print " "
        return comments
        
    def feed(self):
        
        feeds = self.blog.client.get_posts( self.blog.blog_id ).entry
        
        return feeds
        
        
    def id(self, postaki):
        
        import re
        return re.findall(r'.post-(.*)<.ns0*',str(postaki.id))[0]
    

res = Retrieve()
feeds = res.feed()
for feed in feeds:
    print res.comments_on_post(res.id(feed))
    
