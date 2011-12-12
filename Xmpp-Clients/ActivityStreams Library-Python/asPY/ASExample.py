   
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