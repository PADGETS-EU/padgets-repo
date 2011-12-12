'''
Created on Apr 28, 2011

@author: michaelpetuxakis
'''
from asPY import Encoder,ActivityStreamsObject,ActivityStreamsEntry,ActivityStreamsAuthor

stream = Encoder.Encoder('http://samplecompany.com/tasks/activity/', 'Task activities at Sample Company')
    
object = ActivityStreamsObject.ActivityStreamsObject(object_type='ARTICLE')
object.setProperty('id','http://samplecompany.com/tasks/23432/')
object.setProperty('title','Sample task.')
object.setProperty('content','...')
object.addObjectType('task')
object.addObjectType('note')
object.setProperty('link','http://samplecompany.com/tasks/23432/')
  
author = ActivityStreamsAuthor.ActivityStreamsAuthor(name="kouklaki")
author.setProperty('name','Roger Taylor')
author.setProperty('uri','http://samplecompany.com/people/Roger+Taylor/')

entry = ActivityStreamsEntry.ActivityStreamsEntry()
entry.addVerb("update")
entry.title = "Roger Taylor completed a task."
entry.id = 'http://samplecompany.com/tasks/activity/23432/'
entry.addReadyObject(object)
#entry.addObject(object)
entry.addReadyAuthor(author)
    
stream.addEntry(entry)
    
#    header('Content type: text/xml')
#    echo $stream
print stream.toString()