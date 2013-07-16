'''
Created on Apr 28, 2011

@author: michaelpetuxakis
'''
from NtuaActivityStreamsEncoder import Encoder,ActivityStreamsObject,ActivityStreamsEntry

stream = Encoder.Encoder('http://samplecompany.com/tasks/activity/', 'Task activities at Sample Company')
    
object = ActivityStreamsObject.ActivityStreamsObject()
object.setProperty('id','http://samplecompany.com/tasks/23432/')
object.setProperty('title','Sample task.')
object.setProperty('content','...')
object.addObjectType('http://samplecompany.com/activity/schema/1.0/task')
object.addObjectType('http://activitystrea.ms/schema/1.0/note')
object.setProperty('link','http://samplecompany.com/tasks/23432/')
"""    
author = ActivityStreamsAuthor.ActivityStreamsAuthor()
author.setProperty('name','Roger Taylor')
author.setProperty('uri','http://samplecompany.com/people/Roger+Taylor/')
""" 
entry = ActivityStreamsEntry.ActivityStreamsEntry()
entry.addVerb("http://samplecompany.com/activity/schema/1.0/complete")
entry.addVerb("http://activitystrea.ms/schema/1.0/update")
entry.title = "Roger Taylor completed a task."
entry.id = 'http://samplecompany.com/tasks/activity/23432/3242345/'
entry.addObject(object)
#entry.setAuthor(author)
    
stream.addEntry(entry.toString())
    
#    header('Content type: text/xml')
#    echo $stream
print stream.toString()