'''
Created on Mar 16, 2011

@author: alvertisjo
'''

#The object MAY have a variety of properties, depending on the type of the object. 
#It can be described accordingly to Object Constructor 

#Supported Objects are: Article, Comment, Status, Bookmark, Image, Video, Place, Person, Question, Event

#Full Documentation for objects and the supported properties can be found on http://activitystrea.ms/head/activity-schema.html, the Activity Streams Schema Draft specification (till 15/6/2011)

class ActivityStreamsObject :
    id = None
    name = None
    url = None
    object_type = None
    summary = None
    image = None
    imageWidth=None
    imageHeight=None
    imageFormat=None
    in_reply_to_object = None
    attached_objects = None
    reply_objects = None
    reaction_activities = None
    action_links = None
    upstream_duplicate_ids = None
    downstream_duplicate_ids = None
    links = None
    content=None
    content_type=None
    published=None
    updated=None
    isTarget=False
    generatedObject=None
    displayName=None
    attending=None
    maybeAttending=None
    notAttending=None
    longitude=None
    latitude=None
    startTime=None
    endTime=None 
    fullImage=None
    fullImageWidth=None 
    fullImageHeight=None 
    fullImageFormat=None
    addressFormatted=None
    addressStreetAddress=None
    addressLocality=None 
    addressRegion=None 
    addressPostalCode=None 
    addressCountry=None
    embedCode=None
    mediaType=None
    mediaDuration=None
    mediaWidth=None
    mediaHeight=None
    mediaRel=None
    options=None
    
    def __init__(self, id=None, name=None, url=None, object_type=None, summary=None, image=None, imageWidth=None, imageHeight=None,imageFormat=None, attached_objects=None, reply_objects=None, reaction_activities=None, action_links=None, upstream_duplicate_ids=None, downstream_duplicate_ids=None, links=None, content=None,content_type=None, published=None, updated=None, isTarget=False, displayName=None, attending=None, maybeAttending=None, notAttending=None, longitude=None, latitude=None, startTime=None, endTime=None,fullImage=None, fullImageWidth=None, fullImageHeight=None, fullImageFormat=None, addressFormatted=None, addressStreetAddress=None, addressLocality=None, addressRegion=None, addressPostalCode=None, addressCountry=None, embedCode=None, mediaType=None, mediaDuration=None, mediaWidth=None, mediaHeight=None, mediaRel=None, options=None):
        self.id = id
        self.name = name
        self.url = url
        if object_type is not None:
            self.object_type = object_type.upper()
        else:
            self.object_type=None
        self.summary = summary
        self.image = image
        self.imageWidth=imageWidth
        self.imageHeight=imageHeight
        self.content=content
        self.content_type=content_type
        self.published=published
        self.updated=updated
        self.isTarget=isTarget
        self.attending=attending
        self.maybeAttending=maybeAttending
        self.notAttending=notAttending
        self.longitude=longitude
        self.latitude=latitude
        self.startTime=startTime
        self.endTime=endTime
        self.displayName=displayName
        self.fullImage=fullImage
        self.fullImageWidth=fullImageWidth 
        self.fullImageHeight=fullImageHeight 
        self.fullImageFormat=fullImageFormat
        self.imageFormat=imageFormat
        self.addressFormatted=addressFormatted
        self.addressStreetAddress=addressStreetAddress
        self.addressLocality=addressLocality 
        self.addressRegion=addressRegion 
        self.addressPostalCode=addressRegion 
        self.addressCountry=addressCountry
        self.embedCode=embedCode
        self.mediaType=mediaType
        self.mediaDuration=mediaDuration
        self.mediaWidth=mediaWidth
        self.mediaHeight=mediaHeight
        self.mediaRel=mediaRel
        self.options=QuestionOption()
        if options is not None:
            self.options = options
        else:
            self.options = {}
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
        
        self.generateObjects()
        
    def setInReplyTo(self, id=None, name=None, url=None, object_type=None, summary=None, image=None, reaction_activities=None, action_links=None, upstream_duplicate_ids=None, downstream_duplicate_ids=None, links=None, content=None, published=None, updated=None):
        #try:
            self.reply_objects.append(ActivityStreamsObject(id=id, name=name, url=url, object_type=object_type, summary=summary, image=image, reaction_activities=reaction_activities, action_links=action_links, upstream_duplicate_ids=upstream_duplicate_ids, downstream_duplicate_ids=downstream_duplicate_ids, links=links, content=content, published=published, updated=updated))   
        #except:
        #   return 0
        #return 1
        
    """def setInReplyToObject(self,inReplyObject=None):
        if inReplyObject is not None:
            self.reply_objects.append(inReplyObject)"""
            
    """def getProperty( self, property_name ):
        for entry in self.properties:
            if property_name in entry:
                return entry[property_name]
        
    def setProperty(self, property_name, property_value):
        
        self.properties.append({ property_name: property_value})


    def addObjectType(self, object_type):
        for entry in self.properties:
            if 'object-type' in entry:
                return 1
        self.properties.append({'object-type':[object_type]})"""
        
    def generateObjects(self):
        if self.object_type is not None:
            if ("ARTICLE" in self.object_type):
                if self.content is not None:
                    self.generatedObject=Article(content=self.content, displayName=self.name,id=self.id, published=self.published, summary=self.summary, updated=self.updated, url = self.url)
                else:
                    self.generatedObject=Article(content="", displayName=self.name,id=self.id, published=self.published, summary=self.summary, updated=self.updated, url = self.url)
            elif ("COMMENT" in self.object_type):
                if self.content is not None:
                    self.generatedObject=Comment(content=self.content, displayName=self.name,id=self.id, published=self.published, updated=self.updated)
                else:
                    self.generatedObject=Comment(content="", displayName=self.name,id=self.id, published=self.published, updated=self.updated)
            elif ("STATUS" in self.object_type):
                if self.content is not None:
                    self.generatedObject=Status(content=self.content, id=self.id, published=self.published, updated=self.updated, url=self.url)
                else:
                    self.generatedObject=Status(content=" ", id=self.id, published=self.published, updated=self.updated, url=self.url)
            elif ("BOOKMARK" in self.object_type):
                if (self.url is not None):
                    self.generatedObject=Bookmark(displayName=self.displayName, id=self.id, published=self.published, updated=self.updated, targetUrl=self.url, image=self.image, imageWidth=self.imageWidth, imageHeight=self.imageHeight)
                else:
                    self.generatedObject=Bookmark(displayName=self.displayName, id=self.id, published=self.published, updated=self.updated, targetUrl="", image=self.image, imageWidth=self.imageWidth, imageHeight=self.imageHeight)
            elif ("EVENT" in self.object_type):
                self.generatedObject=Event(attending=self.attending, displayName=self.displayName, endTime=self.endTime, id=self.id, longitude=self.longitude, latitude=self.latitude, maybeAttending=self.maybeAttending, notAttending=self.notAttending, published=self.published, startTime=self.startTime, summary=self.summary, updated=self.updated, url=self.url)
            elif ("IMAGE" in self.object_type):
                if ((self.image is not None)or(self.fullImage is not None)or (self.url is not None)):
                    self.generatedObject=Image(displayName=self.displayName, image=self.image, imageWidth=self.imageWidth, imageHeight=self.imageHeight, imageFormat=self.imageFormat, fullImage=self.fullImage, fullImageWidth=self.fullImageWidth, fullImageHeight=self.fullImageHeight, fullImageFormat=self.imageFormat, published=self.published, summary=self.summary, url=self.url, updated=self.updated, id=self.id)
            elif ("PERSON" in self.object_type):
                if (self.url is not None):
                    self.generatedObject=Person(displayName=self.displayName, image=self.image, imageWidth=self.imageWidth, imageHeight=self.imageHeight, imageFormat=self.imageFormat, published=self.published, summary=self.summary, url=self.url, updated=self.updated, id=self.id)
            elif ("PLACE" in self.object_type):
                self.generatedObject=Place(displayName=self.displayName, id=self.id, longitude=self.longitude, latitude=self.latitude, addressFormatted=self.addressFormatted, addressStreetAddress=self.addressStreetAddress, addressLocality=self.addressLocality, addressRegion=self.addressRegion, addressPostalCode=self.addressPostalCode, addressCountry=self.addressCountry, url=self.url)
            elif ("VIDEO" in self.object_type):
                self.generatedObject=Video(displayName=self.displayName, embedCode=self.embedCode, published=self.published, id=self.id, updated=self.updated, mediaUrl=self.url, mediaType=self.mediaType, mediaWidth=self.mediaWidth, mediaHeight=self.mediaHeight, mediaDuration=self.mediaDuration, mediaRel=self.mediaRel)      
            elif ("QUESTION" in self.object_type):
                if (self.options=={}):
                    self.generatedObject=Question(displayName=self.displayName, content=self.content, published=self.published, options=self.options, url=self.url, updated=self.updated, id=self.id)
                    print ("""If you wanted to call the Question object with options, use first the QuestionOption() class to generate an option and QuestionOption.insertOption(id, content) method to insert choices.
                    Then call the ActivityStreamsObject() init with the options as parameters """)
                else: 
                    self.generatedObject=Question(displayName=self.displayName, content=self.content, published=self.published, options=self.options, url=self.url, updated=self.updated, id=self.id)
    
    def getString( self ):
        string="None"   
        if not self.isTarget:
            if self.generatedObject is not None:
                string="<activity:object>"
                string+=self.generatedObject.getString()
                if self.reply_objects != []:
                    for replies in self.reply_objects:
                        # namespace xmlns:thr="http://purl.org/syndication/thread/1.0"
                        string+="<thr:in-reply-to>%s</thr:in-reply-to>"%replies.getString()  
                string+="</activity:object>"
        else:
            print self.generatedObject
            string="<activity:target>%s</activity:target>"%self.generatedObject.getString()
        return string


    def set_name(self, value):
        self.name = value


    def set_url(self, value):
        self.url = value


    def set_object_type(self, value):
        self.object_type = value.upper()
        self.generateObjects()


    def set_summary(self, value):
        self.summary = value


    def set_image(self, value):
        self.image = value


    def set_image_width(self, value):
        self.imageWidth = value


    def set_image_height(self, value):
        self.imageHeight = value


    def set_image_format(self, value):
        self.imageFormat = value


    def set_in_reply_to_object(self, value):
        self.in_reply_to_object = value


    def set_attached_objects(self, value):
        self.attached_objects = value


    def set_reply_objects(self, value):
        self.reply_objects = value


    def set_reaction_activities(self, value):
        self.reaction_activities = value


    def set_action_links(self, value):
        self.action_links = value


    def set_upstream_duplicate_ids(self, value):
        self.upstream_duplicate_ids = value


    def set_downstream_duplicate_ids(self, value):
        self.downstream_duplicate_ids = value


    def set_links(self, value):
        self.links = value


    def set_content(self, value):
        self.content = value
    
    def set_content_type(self,value):
        self.content_type=value

    def set_published(self, value):
        self.published = value


    def set_updated(self, value):
        self.updated = value


    def set_is_target(self, value):
        self.isTarget = value


    def set_generated_object(self, value):
        self.generatedObject = value


    def set_display_name(self, value):
        self.displayName = value


    def set_attending(self, value):
        self.attending = value


    def set_maybe_attending(self, value):
        self.maybeAttending = value


    def set_not_attending(self, value):
        self.notAttending = value


    def set_longitude(self, value):
        self.longitude = value


    def set_latitude(self, value):
        self.latitude = value


    def set_start_time(self, value):
        self.startTime = value


    def set_end_time(self, value):
        self.endTime = value


    def set_full_image(self, value):
        self.fullImage = value


    def set_full_image_width(self, value):
        self.fullImageWidth = value


    def set_full_image_height(self, value):
        self.fullImageHeight = value


    def set_full_image_format(self, value):
        self.fullImageFormat = value


    def set_address_formatted(self, value):
        self.addressFormatted = value


    def set_address_street_address(self, value):
        self.addressStreetAddress = value


    def set_address_locality(self, value):
        self.addressLocality = value


    def set_address_region(self, value):
        self.addressRegion = value


    def set_address_postal_code(self, value):
        self.addressPostalCode = value


    def set_address_country(self, value):
        self.addressCountry = value


    def set_embed_code(self, value):
        self.embedCode = value


    def set_media_type(self, value):
        self.mediaType = value


    def set_media_duration(self, value):
        self.mediaDuration = value


    def set_media_width(self, value):
        self.mediaWidth = value


    def set_media_height(self, value):
        self.mediaHeight = value


    def set_media_rel(self, value):
        self.mediaRel = value


    def set_options(self, value):
        self.options = value


    def set_id(self, value):
        self.id = value


    def set_target_url(self, value):
        self.targetURL = value
   

class MediaLink(object):
    url = None
    media_type = None
    width = None
    height = None
    duration = None
    rel=None
    def __init__(self, url=None, media_type=None, width=None, height=None, duration=None, rel=None):
        self.rel=rel
        self.url = url
        self.media_type = media_type
        self.width = width
        self.height = height
        self.duration = duration
    def getString(self):
        if self.rel is not None:
            string="<link rel=\"%s\" "%self.rel
        else:
            string="<link rel=\"alternate\" "
        if self.media_type is not None:
            string+= "type=\"%s\" "%self.media_type
        string+= "href=\"%s\" "%self.url
        if ((self.width is not None) and (self.height is not None)):
            string= "media:width=\"%s\" media:height=\"%s\" "%(self.width,self.height)
        if self.duration is not None:   
            "media:duration=\"%s\" "%self.duration
        string+= "/>\""
        return string  


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
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.summary is not None:
            string+="<summary>%s</summary>"%self.summary
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"Article\"/>"%self.url
            #string+="<link %s/>"%self.url
        if self.id is not None:
            string+="<id>%s</id>"%self.id
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
            string+="<content type=\"text/html\"><div>%s</div></content>"%self.content
        if self.displayName is not None:
            string+="<title>%s</title>"%self.displayName
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
            string+="<content type=\"text/html\"><div>%s</div></content>"%self.content
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"Status\"/>"%self.url
        return string
    
class Bookmark(object):
    displayName=None
    id=None
    image=None
    imageWidth=None
    imageHeight=None
    published=None
    targetUrl=None
    updated=None

    def __init__(self, displayName=None, id=None, published=None, updated=None, targetUrl=None, image=None, imageWidth=None, imageHeight=None):
        self.displayName = displayName
        self.id = id
        self.published = published
        self.updated = updated
        self.targetUrl = targetUrl
        self.image = image
        self.imageWidth=imageWidth
        self.imageHeight=imageHeight
        
    def getString(self):
        string="<activity:object-type>bookmark</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.targetUrl is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"Bookmark\"/>"%self.targetUrl
        if self.image is not None:
            if ((self.imageHeight is not None) and (self.imageWidth is not None)):
                string+="<link rel=\"enclosure\" type=\"image/jpeg\" href=\"%s\" media:width=\"%s\" media:height=\"%s\" tile=\"Thumbnail\"/>"%(self.image.getString(), self.imageWidth.getString(),self.imageHeight.getString() )
            else:
                string+="<link rel=\"enclosure\" type=\"image/jpeg\" href=\"%s\" media:width=\"45\" media:height=\"45\" tile=\"Thumbnail\"/>"%self.image.getString()
        return string
        
class Event(object):
    attending=None #An optional link to a collection providing information about actors who have responded to the event object using the rsvp-yes verb.
    displayName=None #The natural-language, human-readable and plain-text title of the event.
    endTime=None #The date and time that the event ends represented as a JSON String conforming to the "date-time" production in [RFC3339]
    id=None #The unique identifier for the event object.
    longitude=None
    latitude=None #The optional, location or venue for the event expressed as a place object as described in Section 5.1.
    maybeAttending=None #An optional link to a collection providing information about actors who have responded to the event object using the rsvp-maybe verb.
    notAttending=None #An optional link to a collection providing information about actors who have responded to the event object using the rsvp-no verb.
    published=None #The optional time the event object was created in the form of an [RFC3339] "date-time".
    startTime=None #The date and time that the event begins represented as a JSON String conforming to the "date-time" production in [RFC3339]
    summary=None #Optional short, natural-language, human-readable description of the event represented as a fragment of HTML.
    updated=None #The optional time the event object was last updated in the form of an [RFC3339] "date-time".
    url=None #The permanent IRI of the event's HTML representation.
    
    def __init__(self, attending=None, displayName=None, endTime=None, id=None, longitude=None, latitude=None,maybeAttending=None,notAttending=None, published=None, startTime=None, summary=None, updated=None, url=None):
        self.attending=attending
        self.displayName=displayName
        self.endTime=endTime
        self.id=id
        self.longitude=longitude
        self.latitude=latitude
        self.maybeAttending=maybeAttending
        self.notAttending=notAttending
        self.published=published
        self.startTime=startTime
        self.summary=summary
        self.updated=updated
        self.url=url
    
    def getString(self):
        string="<activity:object-type>event</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.startTime is not None:
            string+="<activity:start_time>%s</activity:start_time>"%self.startTime
        if self.endTime is not None:
            string+="<activity:end_time>%s</activity:end_time>"%self.endTime
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if ((self.latitude is not None)and(self.longitude is not None)):
            string+="<v:geo><v:latitude>%s</v:latitude><v:longitude>%s</v:longitude></v:geo>"%(self.latitude,self.longitude)    
        #A Title property is used to discriminate all the following links to external resources   
        if self.attending is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"attending\"/>"%self.attending
        if self.maybeAttending is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"maybeAttending\"/>"%self.maybeAttending
        if self.notAttending is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"notAttending\"/>"%self.notAttending
        if self.summary is not None:
            string+="<summary/>%s</summary>"%self.url      
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\" tile=\"url\"/>"%self.url
        
        return string
    
class Image(object):
    displayName=None
    id=None
    image=None
    imageWidth=None
    imageHeight=None
    imageFormat="image/"
    fullImage=None
    fullImageWidth=None
    fullImageHeight=None
    fullImageFormat="image/"
    published=None
    summary=None
    url=None
    updated=None

    def __init__(self, displayName=None, image=None, imageWidth=None, imageHeight=None, imageFormat=None, fullImage=None, fullImageWidth=None, fullImageHeight=None, fullImageFormat=None, published=None, summary=None, url=None, updated=None, id=None):
        self.displayName = displayName
        self.image = image
        self.imageWidth = imageWidth
        self.imageHeight = imageHeight
        if imageFormat is not None:
            self.imageFormat+=imageFormat
        else:
            self.imageFormat+="jpeg"
        self.fullImage = fullImage
        self.fullImageWidth = fullImageWidth
        self.fullImageHeight = fullImageHeight
        if fullImageFormat is not None:
            self.fullImageFormat+=fullImageFormat
        else:
            self.fullImageFormat+="jpeg"
        self.published = published
        self.summary = summary
        self.url = url
        self.updated = updated
        self.id = id
 
    def getString(self):
        string="<activity:object-type>image</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.summary is not None:
            string+="<summary>%s</summary>"%self.summary
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
        if self.image is not None:
            if ((self.imageHeight is not None) and (self.imageWidth is not None)):
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" media:width=\"%s\" media:height=\"%s\" tile=\"thumbnail\"/>"%(self.imageFormat.getString(),self.image.getString(), self.imageWidth.getString(),self.imageHeight.getString() )
            else:
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" media:width=\"45\" media:height=\"45\" tile=\"thumbnail\"/>"%(self.imageFormat.getString(),self.image.getString())
        if self.fullImage is not None:
            if ((self.fullImageHeight is not None) and (self.fullImageWidth is not None)):
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" media:width=\"%s\" media:height=\"%s\" tile=\"fullImage\"/>"%(self.fullImageFormat.getString(),self.fullImage.getString(), self.fullImageWidth.getString(),self.fullImageHeight.getString() )
            else:
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" tile=\"fullImage\"/>"%(self.fullImageFormat.getString(),self.fullImage.getString())
        return string

class Person(object):
    displayName=None
    id=None
    image=None
    imageWidth=None
    imageHeight=None
    imageFormat="image/"
    published=None
    url=None
    updated=None
    

    def __init__(self, displayName=None, image=None, imageWidth=None, imageHeight=None, imageFormat=None, published=None, summary=None, url=None, updated=None, id=None):
        self.displayName = displayName
        self.image = image
        self.imageWidth = imageWidth
        self.imageHeight = imageHeight
        if imageFormat is not None:
            self.imageFormat+=imageFormat
        else:
            self.imageFormat+="jpeg"
        self.published = published
        self.summary = summary
        self.url = url
        self.updated = updated
        self.id = id

        
    def getString(self):
        string="<activity:object-type>person</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
        if self.image is not None:
            if ((self.imageHeight is not None) and (self.imageWidth is not None)):
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" media:width=\"%s\" media:height=\"%s\" tile=\"thumbnail\"/>"%(self.imageFormat.getString(),self.image.getString(), self.imageWidth.getString(),self.imageHeight.getString() )
            else:
                string+="<link rel=\"enclosure\" type=\"%s\" href=\"%s\" media:width=\"45\" media:height=\"45\" tile=\"thumbnail\"/>"%(self.imageFormat.getString(),self.image.getString())
        return string
#Address is supportive to the Place object
class Address(object):
    formatted=None
    streetAddress=None
    locality=None
    region=None
    postalCode=None
    country=None

    def __init__(self,formatted=None,streetAddress=None, locality=None, region=None, postalCode=None, country=None):
        self.formatted=formatted #The full mailing address formatted for display or use with a mailing label.
        self.streetAddress=streetAddress
        self.locality=locality
        self.region=region
        self.postalCode=postalCode
        self.country=country
        
    def getString(self):
        string="<v:adr>"
        if self.formatted is not None:
            string+="<v:formatted>%s</v:formatted>"%self.formatted
        if self.streetAddress is not None:
            string+="<v:street-address>%s</v:street-address>"%self.streetAddress
        if self.locality is not None:
            string+="<v:locality>%s</v:locality>"%self.locality
        if self.region is not None:
            string+="<v:region>%s</v:region>"%self.region
        if self.postalCode is not None:
            string+="<v:postal-code>%s</v:postal-code>"%self.postalCode 
        if self.country is not None:
            string+="<v:country-name>%s</v:country-name>"%self.country   
        string+="</v:adr>"
        return string

class Place(object):
    displayName=None
    id=None
    longitude=None
    latitude=None
    address=None
    url=None

    def __init__(self, displayName=None, id=None, longitude=None, latitude=None, addressFormatted=None, addressStreetAddress=None, addressLocality=None, addressRegion=None,addressPostalCode=None, addressCountry=None, url=None):
        self.displayName = displayName
        self.id = id
        self.longitude= longitude
        self.latitude=latitude
        if ((addressFormatted is not None) or (addressStreetAddress is not None) or (addressLocality is not None) or (addressRegion is not None) or (addressPostalCode is not None) or (addressCountry is not None)):
            self.address= Address(addressFormatted, addressStreetAddress, addressLocality, addressRegion,addressPostalCode, addressCountry)
        self.url=url
        
    def getString(self):
        string="<activity:object-type>place</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.address is not None:
            string+= self.address.getString() 
        if ((self.latitude is not None)and(self.longitude is not None)):
            string+="<v:geo><v:latitude>%s</v:latitude><v:longitude>%s</v:longitude></v:geo>"%(self.latitude,self.longitude)
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
        return string
    
#Supportive to the Question
class QuestionOption(object):
    dictionary={}
    def __init__(self):
        dictionary={}
    def insertOption(self,id, content):
        self.dictionary[id.__str__]=content.__str__
    def getDictionary(self):
        return self.dictionary
    def getString(self):
        string=""
        for id, content in self.dictionary.iteritems():
            string+="<option><id>%s</id><content type=\"text/html\"><div>%s</div></content></option>"%(id,content)
        return string
    
class Question(object):
    displayName=None
    content=None
    id=None
    options=None
    published=None
    updated=None
    url=None

    def __init__(self, displayName=None, content=None, published=None, options=None, url=None, updated=None, id=None):
        self.displayName = displayName
        self.content=content
        self.published = published
        self.url = url
        self.updated = updated
        self.id = id
        if options is not None:
            self.options = options
        else:
            self.options = {}
        
    def getString(self):
        string="<activity:object-type>question</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.content is not None:
            string+="<content type=\"text/html\"><div>%s</div></content>"%self.content
        if self.url is not None:
            string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.url
        if self.options is not None:
            string+="<select>%s</select>"%self.updated
        return string   
    
class Video(object):
    displayName=None
    embedCode=None
    id=None
    published=None
    updated=None
    stream=None

    def __init__(self, displayName=None, embedCode=None, published=None, id=None, updated=None, mediaUrl=None, mediaType=None, mediaWidth=None, mediaHeight=None, mediaDuration=None, mediaRel=None):
        self.displayName=displayName
        self.embedCode=embedCode
        self.id=id
        self.published=published
        self.updated=updated
        if mediaUrl is not None:
            self.stream= MediaLink(url=mediaUrl, media_type=mediaType, width=mediaWidth, height=mediaHeight, duration=mediaDuration, rel=mediaRel )
        
    def getString(self):
        string="<activity:object-type>video</activity:object-type>"
        if self.displayName is not None:
            string+="<title type=\"text/html\">%s</title>"%self.displayName
        if self.id is not None:
            string+="<id>%s</id>"%self.id
        if self.published is not None:
            string+="<published>%s</published>"%self.published
        if self.updated is not None:
            string+="<updated>%s</updated>"%self.updated
        if self.stream is not None:
            string+=self.stream.getString()
        if self.embedCode is not None:
            string+="<content type=\"html\"><div>%s</div></content>"%self.embedCode
        return string      