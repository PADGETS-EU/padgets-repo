'''
Created on 13 May 2011

@author: Jo
'''

class Campaign(object):
    id=None
    name=None
    uri=None
    def __init__(self,id=None, name=None, uri=None):
        if id is not None:
            self.id=id
        if name is not None:
            self.name=name
        if uri is not None:
            self.uri=uri
    def getString(self):
        string="<padgets:campaign id=\"%s\" name=\"%s\"/>"%(self.id,self.name)
        return string

#generate one target platform
#pages (facebook page, blog) should pass a lexicon list of type [{id:valueid,  name:nameid}]  
class Destination(object):
    platform_name=None
    account_email=None
    account_username=None
    account_uri=None
    pages=None
    platform_list=[]
    account=None
    target=None
    def __init__(self,platform_name=None,account_email=None, account_username=None, account_uri=None,pages=None ):
        if platform_name is not None:
            self.platform_name = platform_name.upper()
        if account_email is not None:
            self.account_email = account_email
        if account_username is not None:
            self.account_username = account_username
        if account_uri is not None:
            self.account_uri = account_uri
        if pages is not None:
            self.pages = pages
        else:
            self.pages = []  
        
    def initAccounts(self):    
        if self.account_email is not None:
            self.account=Account(email=self.account_email, username=self.account_username, uri=self.account_uri)
            if("FACEBOOK" in self.platform_name):
                list=[]
                for page in self.pages:
                    list.append(Facebook_Pages(id=page['id'], name=page['name']))
                    self.target=Facebook(account=self.account, pages_list=list)
            elif("GOOGLE" in self.platform_name):
                list=[]
                for page in self.pages:
                    list.append(Blogger(id=page['id'], name=page['name']))
                    self.target=Google(account=self.account, pages_list=list)
            elif("YAHOO" in self.platform_name):
                self.target=Yahoo(account=self.account)
            elif("DIGG" in self.platform_name):
                self.target=Digg(account=self.account)
            elif("LINKEDIN" in self.platform_name):
                self.target=LinkedIn(account=self.account)
            elif("TWITTER" in self.platform_name):
                self.target=Twitter(account=self.account)
            else:
                self.target=None
    def addPages(self, pageID, pageName):
        self.pages.append({'id':pageID,'name':pageName})
        
    def getTarget(self):
        self.initAccounts() 
        return self.target 

#Given a list of platform (target) objects, generates the string for the XML
class Platforms(object):
    platforms_list=None
    def __init__(self,platforms_list=None):
        if platforms_list is not None:
            self.platforms_list = platforms_list
        else:
            self.platforms_list = []
            
    def getString(self):
        string="<padgets:platforms>"
        for platform in self.platforms_list:
            try:
                temp=platform.getTarget().getString()
                string+=temp
            except:
                continue     
        string+="</padgets:platforms>"
        return string

#This is a typical SM account    
class Account(object):
    email=None
    username=None
    uri=None
    def __init__(self,email=None, username=None, uri=None):
        if email is not None:
            self.email = email
        if username is not None:
            self.username=username
        if uri is not None:
            self.uri=uri
    def getString(self):
        string="<padgets:account"
        if self.email is not None:
            string+=" email=\"%s\""%self.email
        if self.username is not None:
            string+=" username=\"%s\""%self.username
        if self.uri is not None:
            string+=" uri=\"%s\""%self.uri
        string+="/>"
        return string    
     
#Below there are pages that an account can manage
class Page:
    id=None
    name=None
    def __init__(self,id=None, name=None):
        if id is not None:
            self.id = id
        if name is not None:
            self.name=name
    def getString(self):     
        return 1      

class Facebook_Pages(Page):
    def getString(self):
        string="<padgets:facebookPage id=\"%s\" "%self.id
        if self.name is not None:
            string+="name=\"%s\""%self.name
        string+="/>"
        return string
    
class Blogger(Page):
    def getString(self):
        string="<padgets:blogger id=\"%s\" "%self.id
        if self.name is not None:
            string+="name=\"%s\""%self.name
        string+="/>"
        return string
      
#Below, if assembly accounts and pages, you get a platform
class Platform(object):
    account=None
    pages_list=None
    def __init__(self,account=None, pages_list=None):
        if pages_list is not None:
            self.pages_list = pages_list
        else:
            self.pages_list = []
        if account is not None:
            self.account=account
    def getString(self):
        return 1  
    
class Facebook(Platform):
    def getString(self):
        string="<padgets:facebook>"
        if self.account is not None:
            string+=self.account.getString()
        for page in self.pages_list:
            string+=page.getString()
        string+="</padgets:facebook>"
        return string  

class Google(Platform):
    def getString(self):
        string="<padgets:google>"
        if self.account is not None:
            string+=self.account.getString()
        for page in self.pages_list:
            string+=page.getString()
        string+="</padgets:google>"
        return string 
    
class Yahoo(Platform):
    def getString(self):
        string="<padgets:yahoo>"
        if self.account is not None:
            string+=self.account.getString()
        string+="</padgets:yahoo>"
        return string   
    
class Digg(Platform):
    def getString(self):
        string="<padgets:digg>"
        if self.account is not None:
            string+=self.account.getString()
        string+="</padgets:digg>"
        return string  

class Twitter(Platform):
    def getString(self):
        string="<padgets:twitter>"
        if self.account is not None:
            string+=self.account.getString()
        string+="</padgets:twitter>"
        return string  
    
class LinkedIn(Platform):
    def getString(self):
        string="<padgets:linkedin>"
        if self.account is not None:
            string+=self.account.getString()
        string+="</padgets:linkedin>"
        return string  
