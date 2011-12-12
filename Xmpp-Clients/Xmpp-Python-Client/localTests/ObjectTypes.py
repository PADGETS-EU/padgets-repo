class ASObjects:
    
    def __init__(self):
        #This is a dict that contains all the info that we want to have in AS
        
        self.variables = {}
        self.type = ""
        
    def getString(self):
        string="<activity:object-type>%s</activity:object-type>"%self.type
        
        for entry in self.variables:
            
            if self.variables[entry]==None:
                continue
            
            
            if "id" in entry:
                string+="<id>%s</id>"%self.variables[entry]
            elif 'content' in entry:
                string+="<content type=\"text/html\">%s</content>"%self.variables[entry]
            elif 'url' in entry.lower():
                string+="<link rel=\"alternate\" type=\"text/html\" href=\"%s\"/>"%self.variables[entry]
            else:
                string+="<%s>%s</%s>"%(entry,self.variables[entry],entry)
                
        return string
                

class Comment:
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
    
class Link:
    
    def __init__(self, media_type, rel, url):
        self.media_type = media_type
        self.rel = rel
        self.url = url
        
    
        
class Bookmark(ASObjects):
    
    def __init__(self,displayName=None,id=None,image=None,imageFormat=None,imageHeight=None,imageWidth=None,published=None,updated=None,targetUrl=None):
        #super(Person,self).__init__()
        ASObjects.__init__(self)
        self.displayName = displayName
        self.id = id
        self.image = image
        self.imageFormat = imageFormat
        self.imageHeight = imageHeight
        self.imageWidth = imageWidth
        self.published = published
        self.updated = updated
        self.targetUrl = targetUrl
        
        self.type = "bookmark"
        self.variables['image'] = self.image
        self.variables['imageFormat'] = self.imageFormat
        self.variables['imageHeight'] = self.imageHeight
        self.variables['imageWidth'] = self.imageWidth
        self.variables['published'] = self.published
        self.variables['updated'] = self.updated
        self.variables['targetUrl'] = self.targetUrl
        self.variables['id'] = self.id
    
class Person(ASObjects):
    
    def __init__(self,displayName=None,id=None,image=None,imageFormat=None,imageHeight=None,imageWidth=None,published=None,updated=None,url=None):
        #super(Person,self).__init__()
        ASObjects.__init__(self)
        self.displayName = displayName
        self.id = id
        self.image = image
        self.imageFormat = imageFormat
        self.imageHeight = imageHeight
        self.imageWidth = imageWidth
        self.published = published
        self.updated = updated
        self.url = url
        
        self.type = "person"
        self.variables['displayName'] = self.displayName
        self.variables['image'] = self.image
        self.variables['imageFormat'] = self.imageFormat
        self.variables['imageHeight'] = self.imageHeight
        self.variables['imageWidth'] = self.imageWidth
        self.variables['published'] = self.published
        self.variables['updated'] = self.updated
        self.variables['url'] = self.url
        self.variables['id'] = self.id
        
class Question(ASObjects):
    
    def __init__(self,displayName=None,id=None,options=None,published=None,updated=None,url=None,content=None):
        #super(Person,self).__init__()
        ASObjects.__init__(self)
        
        self.type = "question"
        self.variables['content'] = content
        self.variables['options'] = options
        self.variables['displayName'] = displayName
        self.variables['published'] = published
        self.variables['updated'] = updated
        self.variables['url'] = url
        self.variables['id'] = id
    
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