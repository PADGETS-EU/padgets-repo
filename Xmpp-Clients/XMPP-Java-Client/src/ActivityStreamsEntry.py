'''
Created on Mar 16, 2011

@author: michaelpetuxakis
'''
THR_NAMESPACE="xmlns:thr=\"http://purl.org/syndication/thread/1.0\""
ATOM_NAMESPACE="xmlns=\"http://www.w3.org/2005/Atom\"" 
ACTIVITY_NAMESPACE="xmlns:activity=\"http://activitystrea.ms/spec/1.0/\"" 
PADGETS_NAMESPACE="xmlns:padgets=\"http://padgets.eu/spec/1.0/\""
import time
import PadgetsExtension
import ActivityStreamsObject
import ActivityStreamsAuthor

from main import ActivityStreamsEntryType

class ActivityStreamsEntry(ActivityStreamsEntryType):
    
    id=None
    title=None
    published=None
    author=None
    object=None
    verb=None
    target_object=None
    in_reply_to_objects=None
    campaign=None
    target_platforms=None
    target_platforms_list=None
    target_platforms_end=False
    
    entry=None
     
    def __init__(self):# , id=None, title=None):
        self.entry=  "<entry %s %s %s %s>"%(THR_NAMESPACE,ATOM_NAMESPACE,ACTIVITY_NAMESPACE,PADGETS_NAMESPACE) 
        id = None
        title = None
        if id is not None:
            self.id =  id
            self.entry+="<id>%s</id>"%self.id
        if title is not None:
            self.title = title
            self.entry+="<title>%s</title>"%self.title
        self.target_platforms=[]
        self.target_platforms_list=[]
        localtime = time.localtime()
        self.published = "%s/%s/%s"%(localtime[2],localtime[1],localtime[0])  
        
    def addObject(self, object_id=None, object_name=None, object_url=None, object_type=None, object_summary=None, object_image=None, object_reply_objects=None, object_reaction_activities=None, object_action_links=None, object_upstream_duplicate_ids=None, object_downstream_duplicate_ids=None, object_links=None, object_content=None, object_published=None, object_updated=None, object_isTarget=None ):# ActivityStreamsObject object):
        self.object=ActivityStreamsObject.ActivityStreamsObject(id=object_id, name=object_name, url=object_url, object_type=object_type, summary=object_summary,reply_objects=object_reply_objects, image=object_image, reaction_activities=object_reaction_activities, action_links=object_action_links, upstream_duplicate_ids=object_upstream_duplicate_ids, downstream_duplicate_ids=object_downstream_duplicate_ids, links=object_links, content=object_content, published=object_published, updated=object_updated, isTarget=object_isTarget)
    
    def addReadyObject(self,object):
        self.object=object
    
    def addInReplyToObject(self, id=None, name=None, url=None, object_type=None, summary=None, image=None, reaction_activities=None, action_links=None, upstream_duplicate_ids=None, downstream_duplicate_ids=None, links=None, content=None, published=None, updated=None):
        if self.object is not None:
            self.object.setInReplyTo(id=id, name=name, url=url, object_type=object_type, summary=summary, image=image, reaction_activities=reaction_activities, action_links=action_links, upstream_duplicate_ids=upstream_duplicate_ids, downstream_duplicate_ids=downstream_duplicate_ids, links=links, content=content, published=published, updated=updated)
            
    def addVerb( self, activity_verb ):
        if activity_verb is not None:
            self.verb = activity_verb.lower()
        else:
            self.verb="post"
            
    def addAuthor( self, author_name=None,author_uri=None, author_id=None, author_link=None):#ActivityStreamsAuthor author):
        if ((author_name is not None) and (author_id is not None)):
            if ((author_link is not None)and (author_uri is not None)):
                self.author =ActivityStreamsAuthor.ActivityStreamsAuthor(name=author_name, uri=author_uri, id=author_id, link=author_link)
            elif (author_link is not None):
                self.author =ActivityStreamsAuthor.ActivityStreamsAuthor(name=author_name, id=author_id, link=author_link)
            elif (author_uri is not None):
                self.author =ActivityStreamsAuthor.ActivityStreamsAuthor(name=author_name, uri=author_uri, id=author_id)
            else:
                self.author =ActivityStreamsAuthor.ActivityStreamsAuthor(name=author_name, id=author_id)
    
    def addReadyAuthor(self,author):
        self.author = author
    
    def addTarget( self, object_id=None, object_name=None, object_url=None, object_type=None, object_summary=None, object_image=None, object_reply_objects=None, object_reaction_activities=None, object_action_links=None, object_upstream_duplicate_ids=None, object_downstream_duplicate_ids=None, object_links=None, object_content=None, object_published=None, object_updated=None, object_isTarget=None ):# ActivityStreamsObject object):
        self.target_object=ActivityStreamsObject.ActivityStreamsObject(id=object_id, name=object_name, url=object_url, object_type=object_type, summary=object_summary,reply_objects=object_reply_objects, image=object_image, reaction_activities=object_reaction_activities, action_links=object_action_links, upstream_duplicate_ids=object_upstream_duplicate_ids, downstream_duplicate_ids=object_downstream_duplicate_ids, links=object_links, content=object_content, published=object_published, updated=object_updated, isTarget=object_isTarget)
    
    def addTargetPlatform( self, platform_name=None,account_email=None, account_username=None, account_uri=None,pages=None, is_final_platform=None):
        if (self.target_platforms_end!=True):
            self.target_platforms_list.append(PadgetsExtension.Destination(platform_name=platform_name,account_email=account_email, account_username=account_username, account_uri=account_uri,pages=pages))
            if is_final_platform:
                self.target_platforms=PadgetsExtension.Platforms(self.target_platforms_list)
                self.target_platforms_end=True
    #for existing platforms, this method adds pages for the last platform on the list      
    def addTargetPlatformPages( self, pageID=None, pageName=None):
        if self.target_platforms_list[-1] is not None:
            self.target_platforms_list[-1].addPages(pageID,pageName)
        
    def addCampaign(self,id=None, name=None, uri=None):
        if id is not None:
            self.campaign=PadgetsExtension.Campaign(id, name, uri)    
        
    def getAtom(self):
        if self.published is not None:
            self.entry+="<published>%s</published>"%self.published
        if self.author is not None:
            self.entry+=self.author.getString()    
        if self.object is not None:
            self.entry+=self.object.getString()
        if self.verb is not None:
            self.entry+="<activity:verb>%s</activity:verb>"%self.verb
        if self.target_object is not None:
            self.entry+=self.target_object.getString()
        if self.campaign is not None:
            self.entry+=self.campaign.getString()
        if self.target_platforms is not None:
            if self.target_platforms_end!=True:
                self.target_platforms=PadgetsExtension.Platforms(self.target_platforms_list)
                self.target_platforms_end=True
            self.entry+=self.target_platforms.getString()        
        self.entry +="</entry>"    
        return self.entry;
 