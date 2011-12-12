'''
Created on 13 May 2011

@author: Jo
'''
from asPY import ActivityStreamsObject

import time

localtime = time.localtime()
timepublished = "%s/%s/%s"%(localtime[2],localtime[1],localtime[0])


obj1=ActivityStreamsObject.ActivityStreamsObject(name="this is the name of the target reply article", url="http://www.blogger.com/testURL", object_type="Article", summary="This is the summary of the article",  links=["http://blogger.com/123124"], content="Test Article content", published=timepublished, updated=timepublished)
#init=ActivityStreamsObject.ActivityStreamsObject(name="this is the name of the article", url="http://www.blogger.com/testURL", object_type="Article", summary="This is the summary of the article",  links=["http://blogger.com/123124"], content="Test Article content", published=timepublished, updated=timepublished)
init=ActivityStreamsObject.ActivityStreamsObject(id="122313113",name="this is the name of the status", url="http://www.blogger.com/testURL", object_type="status", summary="This is the summary of the status",  links=["http://facebook.com/123124", "http://good weather"], content="Test Article content", published=timepublished, updated=timepublished, reply_objects=[obj1])
print init.getString()

from asPY import PadgetsExtension

list=[]
target_pages=[{'id':'112324','name':'MyPage'},{'id':'123111','name':'YourPage'}]
list.append(PadgetsExtension.Destination(platform_name="facebook",account_email="alvertisjo@hotmail.com", account_username="iosif.alvertis", account_uri="http://www.facebook.com/iosif.alvertis",pages=target_pages ))
list.append(PadgetsExtension.Destination(platform_name="facebook",account_email="meptych@gmail.com", account_username="mpetych", account_uri="http://www.facebook.com/mpetych",pages=target_pages ))
list.append(PadgetsExtension.Destination(platform_name="twitter",account_email="alvertisjo@hotmail.com", account_username="alvertisjo", account_uri="http://www.twitter.com/alvertisjo"))
list.append(PadgetsExtension.Destination(platform_name="yahoo",account_email="alvertisjo@yahoo.com", account_username="alvertisjo", account_uri="http://www.yahoo.com/profiles/alvertisjo",pages=target_pages )) 
temp=PadgetsExtension.Platforms(platforms_list=list)
print temp.getString()