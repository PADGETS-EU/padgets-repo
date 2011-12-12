'''
Created on Mar 16, 2011

@author: michaelpetuxakis
'''

#The object MAY have a variety of properties, depending on the type of the object. 
#It can be described accordingly to Object Constructor 

#Supported Objects: Article, Comment, Status
class ActivityStreamsObject :
    id = None
    name = None
    url = None
    object_type = None
    summary = None
    image = None
    in_reply_to_object = None
    attached_objects = None
    reply_objects = None
    reaction_activities = None
    action_links = None
    upstream_duplicate_ids = None
    downstream_duplicate_ids = None
    links = None
    content=None
    published=None
    updated=None
    isTarget=False
    generatedObject=None
    
    def __init__(self, id=None, name=None, url=None, object_type=None, summary=None, image=None, attached_objects=None, reply_objects=None, reaction_activities=None, action_links=None, upstream_duplicate_ids=None, downstream_duplicate_ids=None, links=None, content=None, published=None, updated=None, isTarget=False):
        self.id = id
        self.name = name
        self.url = url
        self.object_type = object_type
        if object_type !=None:
            self.object_type = object_type.upper()
        self.summary = summary
        self.image = image
        self.content=content
        self.published=published
        self.updated=updated
        self.isTarget=isTarget
        self.properties = []
        if attached_objects is not None:
            self.attached_objects = attached_objects
        else:
            self.attached_objects = []

        if reply_objects is not None:
            self.reply_objects = reply_objects
        else:
            self.reply_objects = []

        if reaction_activities is not None:
            self.reaction_activities = reaction_activities
        else:
            self.reaction_activities = []

        if action_links is not None:
            self.action_links = action_links
        else:
            self.action_links = []

        if upstream_duplicate_ids is not None:
            self.upstream_duplicate_ids = upstream_duplicate_ids
        else:
            self.upstream_duplicate_ids = []

        if downstream_duplicate_ids is not None:
            self.downstream_duplicate_ids = downstream_duplicate_ids
        else:
            self.downstream_duplicate_ids = []

        if links is not None:
            self.links = links
        else:
            self.links = []
    
        if ("ARTICLE" in self.object_type):
            if self.content is not None:
                self.generatedObject=Article(content=self.content, displayName=self.name,id=self.id, published=self.published, summary=self.summary, updated=self.updated, url = self.url)
            else:
                self.generatedObject=Article(content="", displayName=self.name,id=self.id, published=self.published, summary=self.summary, updated=self.updated, url = self.url)
        elif ("COMMENT" in self.object_type):
            if self.content is not None:
                self.generatedObject=Comment(content=self.content, displayName=self.name,id=self.id, published=published, updated=self.updated)
            else:
                self.generatedObject=Comment(content="", displayName=self.name,id=self.id, published=published, updated=self.updated)
        elif ("STATUS" in self.object_type):
            if self.content is not None:
                self.generatedObject=Status(content=content, id=self.id, published=published, updated=updated, url=self.url)
            else:
                self.generatedObject=Status(content=" ", id=self.id, published=published, updated=updated, url=self.url)
        elif ("PERSON" in self.object_type):
            from ObjectTypes import Person
            if self.content is not None:
                self.generatedObject=Person(content=content, id=self.id, published=published, updated=updated, url=self.url)
            else:
                self.generatedObject=Person(content=" ", id=self.id, published=published, updated=updated, url=self.url)
        elif ("QUESTION" in self.object_type):
            from ObjectTypes import Question
            if self.content is not None:
                self.generatedObject=Question(content=content, id=self.id, published=published, updated=updated, url=self.url)
            else:
                self.generatedObject=Question(content=" ", id=self.id, published=published, updated=updated, url=self.url)
        elif ("BOOKMARK" in self.object_type):
            from ObjectTypes import Bookmark
            if self.content is not None:
                self.generatedObject=Bookmark(content=content, id=self.id, published=published, updated=updated, url=self.url)
            else:
                self.generatedObject=Bookmark(content=" ", id=self.id, published=published, updated=updated, url=self.url)
        
            
   
    def setInReplyTo(self, id=None, name=None, url=None, object_type=None, summary=None, image=None, reaction_activities=None, action_links=None, upstream_duplicate_ids=None, downstream_duplicate_ids=None, links=None, content=None, published=None, updated=None):
        #try:
            self.reply_objects.append(ActivityStreamsObject(id=id, name=name, url=url, object_type=object_type, summary=summary, image=image, reaction_activities=reaction_activities, action_links=action_links, upstream_duplicate_ids=upstream_duplicate_ids, downstream_duplicate_ids=downstream_duplicate_ids, links=links, content=content, published=published, updated=updated))   
        #except:
         #   return 0
        #return 1
        
    """def setInReplyToObject(self,inReplyObject=None):
        if inReplyObject is not None:
            self.reply_objects.append(inReplyObject)"""
            
    def getProperty( self, property_name ):
        for entry in self.properties:
            if property_name in entry:
                return entry[property_name]
        
    def setProperty(self, property_name, property_value):
        
        self.properties.append({ property_name: property_value})


    def addObjectType(self, object_type):
        for entry in self.properties:
            if 'object-type' in entry:
                return 1
        self.properties.append({'object-type':object_type})
        
        
    def getString( self ):
        string="None"
        if not self.isTarget:
            string="<activity:object>"
            string+=self.generatedObject.getString()
            if self.reply_objects != []:
                for replies in self.reply_objects:
                    # namespace xmlns:thr="http://purl.org/syndication/thread/1.0"
                    string+="<thr:in-reply-to>%s</thr:in-reply-to>"%replies.getString()  
            string+="</activity:object>"
        else:
            string="<activity:target>%s</activity:target>"%self.generatedObject.getString()
        return string
        

class MediaLink(object):
    url = None
    media_type = None
    width = None
    height = None
    duration = None

    def __init__(self, url=None, media_type=None, width=None, height=None, duration=None):
        self.url = url
        self.media_type = media_type
        self.width = width
        self.height = height
        self.duration = duration


class ActionLink(object):
    url = None
    caption = None

    def __init__(self, url=None, caption=None):
        self.url = url
        self.caption = caption


class Link(object):
    url = None
    media_type = None
    rel = None

    def __init__(self, url=None, media_type=None, rel=None):
        self.url = url
        self.media_type = media_type
        self.rel = rel
    def getString(self):
        return "rel=\"%s\" type=\"%s\" href=\"%s\""%(self.rel,self.media_type,self.url)
        

#Below there are documented all the supported objects
#as referenced on http://activitystrea.ms/head/activity-schema.html
class Article(object):
    content=None
    displayName=None
    id=None
    published=None
    summary=None
    updated=None
    url = None

    def __init__(self, content=None, displayName=None, id=None, published=None, summary=None, updated=None, url=None, media_type=None):
        self.content = content
        self.displayName = displayName
        self.published = published
        self.summary = summary
        self.updated = updated
        #self.url = Link(url, "text/html","alternate").getString()
        if url is not None:
            self.url = Link(url, "text/html","alternate").getString()
        self.media_type = media_type
        self.id = id

    def getContent(self):
        return {"content":self.content, "displayName":self.displayName,"id":self.id, "published":self.published,"summary":self.summary,"updated":self.updated,"link":self.url}
    def getString(self):
        string="<activity:object-type>article</activity:object-type>"
        if self.content is not None:
            string+="<content type=\"text/html\">%s</content>"%self.content
        if self.displayName is not None:
            string+="<displayName>%s</displayName>"%self.displayName
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.summary is not None:
            string+="<summary>%s</summary>"%self.summary
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.url is not None:
            #string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
            string+="<link %s/>"%self.url
        return string
        

class Comment(object):
    content=None
    displayName=None
    id=None
    inReplyTo=None
    published=None
    updated=None

    def __init__(self, content=None, displayName=None, id=None, inReplyTo=None, published=None, updated=None):
        self.content = content
        self.displayName = displayName
        self.id = id
        if inReplyTo is not None:
            self.inReplyTo =inReplyTo
        else:
            self.inReplyTo = []
        self.published = published
        self.updated = updated

    def getContent(self):
        return {"content":self.content, "displayName":self.displayName,"id":self.id, "published":self.published,"summary":self.summary,"updated":self.updated,"link":self.url}
        
    def getString(self):
        string="<activity:object-type>comment</activity:object-type>"
        if self.content is not None:
            string+="<content type=\"text/plain\">%s</content>"%self.content
        if self.displayName is not None:
            string+="<displayName>%s</displayName>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.inReplyTo !=[]:
            for reply in self.inReplyTo:
                #print the string of another object that is passed as a reply
                string+=reply.getString().toString()
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        return string
            
class Status(object):
    content=None
    id=None
    published=None
    updated=None
    url=None

    def __init__(self, content=None, id=None, published=None, updated=None, url=None):
        self.content = content
        self.id = id
        self.published = published
        self.updated = updated
        self.url = url
    #def getContent(self):
        #return {"content":self.content, "displayName":self.displayName,"id":self.id, "published":self.published,"summary":self.summary,"updated":self.updated,"link":self.url}
        
    def getString(self):
        string="<activity:object-type>status</activity:object-type>"
        if self.content is not None:
            string+="<content type=\"text/html\">%s</content>"%self.content
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
        return string
        