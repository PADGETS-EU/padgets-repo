'''
Created on 28 Apr 2011

@author: Jo
'''
import gdata.blogger.client
import gdata.client
import gdata.sample_util
import gdata.data
import atom.data
import gdata.gauth
import re

CLIENT_LOGIN = 1
AUTHSUB = 2
OAUTH = 3

HMAC = 1
RSA = 2


class BloggerConnector:
    '''
    classdocs
    '''
    blog_id=None
    postURL=None
    postID=None
    email=None
    password=None
    title= 'Test Title'
    content= """<b>Test post</b> This is michael's post <a href="http://a1.twimg.com/profile_images/943476591/IMG_0049.JPG">photo</a> """
    
    def __init__(self, target_blog_id, email, password):
        """Creates a GDataService and provides ClientLogin auth details to it.
        The email and password are required arguments for ClientLogin.  The
        'source' defined below is an arbitrary string, but should be used to
        reference your name or the name of your organization, the app name and
        version, with '-' between each of the three values."""

        # Authenticate using ClientLogin, AuthSub, or OAuth.
        self.client = gdata.blogger.client.BloggerClient()
        #gdata.sample_util.authorize_client(
        #self.client, service='blogger', source='Blogger_Python_Sample-2.0',
        #scopes=['http://www.blogger.com/feeds/'])
        #Client Login
        source='Blogger_Python_Sample-2.0'
        service='blogger'
        self.client.client_login(email, password, source, service)
        #Set the target blog ID 
        self.blog_id=target_blog_id
        
        

        # Get the blog ID for the first blog.
        #feed = self.client.get_blogs()
        #self.blog_id = feed.entry[0].get_blog_id()
        
    def CreatePost(self, title, content, labels,is_draft):
        """This method creates a new post on a blog.  The new post can be stored as
        a draft or published based on the value of the is_draft parameter.  The
        method creates an GDataEntry for the new post using the title, content,
        author_name and is_draft parameters.  With is_draft, True saves the post as
        a draft, while False publishes the post.  Then it uses the given
        GDataService to insert the new post.  If the insertion is successful, the
        added post (GDataEntry) will be returned.
        """
        response=self.client.add_post(self.blog_id, title, content, labels,draft=is_draft)
        self.postURL=response.GetEditLink().href
        #self.postID=response.id
        temp=re.findall(r'.*default/(.*)',self.postURL)
        self.postID=temp[0]
        return response
    
    def DeletePost(self, postID):
        """This method removes the post specified by the given edit_link_href, the
        URI for editing the post.
        """
        uri="http://www.blogger.com/feeds/"+self.blog_id+"/posts/default/"+postID
        self.client.delete( uri)
        #print 'Successfully deleted public post: "' + uri + '".\n'
    
    def run(self):
        """Runs each of the example methods defined above, demonstrating how to
        interface with the Blogger service.
        """
    
        # Demonstrate retrieving a list of the user's blogs.
        #self.PrintUserBlogTitles()
        
        # Demonstrate how to create a draft post.
        #temp=self.PublishPost(self.title, self.content, ["test", "label"], True)
        #print 'Successfully created draft post: "' + temp + '".\n'
        
        # Delete the draft blog post.
        #self.client.delete(draft_post)
      
        # Demonstrate how to publish a public post.
        temp=self.CreatePost(self.title, self.content, ["test", "label"], False)
        print 'Successfully created public post: "' + temp.id.text + '".\n'
        print 'BlogID: "' + self.postID+ '".\n'
        print 'FeedURL: "' + self.postURL + '".\n'
        
        #Demonstrate how to delete a post given its postID
        #self.DeletePost(self.postID)
        #print 'Successfully deleted public post: "' + self.postURL + '".\n'
        
      
        # Demonstrate various feed queries.
        #print "Now listing all posts."
        #self.PrintAllPosts()
        #print "Now listing all posts between 2007-04-04 and 2007-04-23."
        #self.PrintPostsInDateRange("2007-04-04", "2007-04-23")
    
        # Demonstrate updating a post's title.
        #print "Now updating the title of the post we just created:"
        #public_post = self.UpdatePostTitle(public_post, "The party's over")
        #print "Successfully changed the post's title to \"" + public_post.title.text + "\".\n"
      
        # Demonstrate how to retrieve the comments for a post.
    
        # Get the post ID and build the comments feed URI for the specified post
        #post_id = public_post.get_post_id()
        
        #print "Now posting a comment on the post titled: \"" + public_post.title.text + "\"."
        #comment = self.CreateComment(post_id, "Did you see any sharks?")
        #print "Successfully posted \"" + comment.content.text + "\" on the post titled: \"" + public_post.title.text + "\".\n"
        
        #comment_id = comment.GetCommentId()
        
        #print "Now printing all comments"
        #self.PrintAllComments(post_id)
       
        # Delete the comment we just posted
        #print "Now deleting the comment we just posted"
        #self.DeleteComment(comment)
        #print "Successfully deleted comment." 
        #self.PrintAllComments(post_id)
    
        # Demonstrate deleting posts.
        #print "Now deleting the post titled: \"" + public_post.title.text + "\"."
        #self.DeletePost(public_post)
        #print "Successfully deleted post." 
        #self.PrintAllPosts()
   
        """The main function runs the BloggerExample application.
  
         NOTE:  It is recommended that you run this sample using a test account.
        """


#blog = BloggerConnector('6524405923717584259','gic.epu.test@gmail.com','g1c.3pu.t3st')
#blog.run()