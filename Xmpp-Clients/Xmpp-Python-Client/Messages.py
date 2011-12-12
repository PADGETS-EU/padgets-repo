'''
Created on 8 Dec 2011

@author: mpetyx
'''

from asPY import ActivityStreamsEntry

class Messages:
    
    def __init__(self):
        
        """
            Simple test messages
        """
        
    def message1(self):
        
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
        
        return entry.getAtom()
    
    def message2(self):
        
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
        
        return entry.getAtom()
    
    def message3(self):
        
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
        
        return entry.getAtom()
    
    def message4(self):
        
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
        
        return entry.getAtom()
    
    def message5(self):
        
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
        
        return entry.getAtom()
    
    def message6(self):
        
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
        
        return entry.getAtom()
    
    
    def choose_message(self):
        
        import random
        
        choose = random.randint(1,6)
        
        if choose==1:
            return self.message1()
        if choose==2:
            return self.message2()
        if choose==3:
            return self.message3()
        if choose==4:
            return self.message4()
        if choose==5:
            return self.message5()
        if choose==6:
            return self.message6()
        
print Messages().choose_message()
