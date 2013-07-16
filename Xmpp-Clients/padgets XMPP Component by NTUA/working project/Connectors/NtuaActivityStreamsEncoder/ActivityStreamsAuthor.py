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
    def __init__(self, name, uri=None, id=None, link=None):
        self.name=name
        self.uri=uri
        self.id=id
        self.link=link
                
    def getString(self):
        string="""<author><name>%s</name><uri>%s</uri><id>%s</id><activity:object-type>person</activity:object-type><link rel="alternate" type="text/html" href="%s"/></author>"""%(self.name,self.uri,self.id, self.link)
        return string;
            
