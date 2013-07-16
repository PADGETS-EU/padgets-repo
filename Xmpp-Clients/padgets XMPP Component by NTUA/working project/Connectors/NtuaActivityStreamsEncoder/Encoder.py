'''
Created on Mar 16, 2011

@author: michaelpetuxakis

'''

class Encoder:
    
    def __init__(self ,id=None, title="", description="" , link=None, author=None):
        
        self.author = author
        self.title = title
        self.link  = link
        self.id = id
        self.title = title
        self.description = description
        self.entries = []
        self.string = ""
        
        
        
    def  addEntry(self, entry):
        """
        Here the entry field must be an instance of the ActivityStreamsEntry class
        """
        self.entries.append( entry )
        
        
    def toString(self):
        
        string = ""
        import time
        localtime = time.localtime()
        updated_time = "%s/%s/%s"%(localtime[2],localtime[1],localtime[0])
    
        temp_string = """
<?xml version="1.0" encoding="UTF-8"?>
<feed
  xmlns="http://www.w3.org/2005/Atom"
  xmlns:thr="http://purl.org/syndication/thread/1.0"
  xmlns:activity="http://activitystrea.ms/spec/1.0/"
  xml:lang="en">
    <title type="text">%s</title>
    <updated>%s</updated>
    
    <link rel="alternate" type="text/html" href="%s" />
    <id>%s</id>
    <link rel="self" type="application/atom+xml" href="%s" />
    """%( self.title, updated_time, self.link, self.id, self.id )
    
        string = string + temp_string
    
        temp_string = ""
   
        from ActivityStreamsEntry import ActivityStreamsEntry
        for entry in self.entries:
            #if type(entry) is ActivityStreamsEntry:
                string = string + entry
            
        string = string + "</feed>"        
            
        self.string = string
        return string