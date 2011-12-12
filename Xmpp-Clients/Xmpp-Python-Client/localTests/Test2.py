'''
Created on 13 May 2011

@author: Jo
'''

    
from asPY import ActivityStreamsEntry

entry=ActivityStreamsEntry("idofEntry","NameofEntry")
entry.addAuthor(author_name="Iosif Alvertis", author_uri="padgets:http://www.ppadgets.eu/alvertisjo", author_id="342342342", author_link="http://www.ppadgets.eu/alvertisjo")
entry.addCampaign(id="idCampaign", name="campaignName", uri="padgets:idcampaignURI")
entry.addVerb(activity_verb="post")
entry.addObject(object_name="My Status", object_type="status", object_summary="this is a test entry summary", object_content="This is the content of the message")
entry.addInReplyToObject(id="replyID", name="this is the reply name", url="http://thisistherplyURL.com", object_type="Comment", summary="This is a summary of the reply")
entry.addTarget(object_name="My article", object_type="article", object_summary="this is a test target article", object_content="This is the content of the article")
entry.addTargetPlatform(platform_name="Facebook", account_email="alvertisjo@hotmail.com", account_username="iosif.alvertis", is_final_platform=False)
entry.addTargetPlatformPages(pageID="page1ID", pageName="My Page name")
entry.addTargetPlatformPages(pageID="page2ID", pageName="My Page name no2")
entry.addTargetPlatform(platform_name="Yahoo", account_email="alvertisjo@yahoo.com", account_username="alvertisjo", is_final_platform=True)

print entry.getAtom()