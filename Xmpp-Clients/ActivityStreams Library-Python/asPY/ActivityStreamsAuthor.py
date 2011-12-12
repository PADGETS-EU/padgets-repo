'''
Created on Mar 16, 2011

@author: michaelpetuxakis
'''

#import ActiviyStreamsObject

#import Encoder

#from ActiviyStreamsObject import ActiviyStreamsObject

class ActivityStreamsAuthor: 
    name=None
    uri=None
    id=None
    link=None
    object_type="person"
    def __init__(self, name=None, uri=None, id=None, link=None):
        self.name=name
        self.uri=uri
        self.id=id
        self.link=link
        self.properties = []
                
    def getString(self):
        string="""<author><name>%s</name><uri>%s</uri><id>%s</id><activity:object-type>person</activity:object-type><link rel="alternate" type="text/html" href="%s"/></author>"""%(self.name,self.uri,self.id, self.link)
        return string;
    
    def setProperty(self, property_name, property_value):
        
        if 'uri' in property_name:
            self.uri = property_value
        if 'name' in property_name:
            self.uri = property_value
        if 'id' in property_name:
            self.uri = property_value
        if 'link' in property_name:
            self.uri = property_value
        self.properties.append({ property_name: property_value})
        
    def get_name(self):
        if self.name is not None:
            return self.name
        else:
            return ""

    def get_uri(self):
        if self.uri is not None:
            return self.uri
        else:
            return ""


    def get_link(self):
        if self.link is not None:
            return self.link
        else:
            return ""

    def get_object_type(self):
        if self.object_type is not None:
            return self.object_type
        else:
            return ""


    def get_id(self):
        if self.id is not None:
            return self.id
        else:
            return ""
    
    def printAll(self):
        print(self.get_name()+", "+self.get_uri()+","+self.get_link()+", "+self.get_object_type()+", "+self.get_id())
    
            
