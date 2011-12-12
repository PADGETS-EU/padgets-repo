'''
Created on 15 June 2011

@author: mpetyx@epu.ntua.gr
@author: alvertisjo@epu.ntua.gr
'''
import Prefixes
import ActivityStreamsEntry
import ActivityStreamsAuthor
import ActivityStreamsObject
from Decoder import *

class Parse:
   def __init__(self, original):  
       self.lista = []
       self.original = original
       self.unfold(self.original)
       
   def printList(self):
       print self.lista
   
   def unfold( self, temp ):
       
       if type(temp) == str:
           self.lista.append(temp)
       else: 
           for i in temp:
               self.unfold( i )
               
   #Searches for an element inside the XML tree
   def find(self, temp, what, ignore =None):
       
       if type( temp )== str:
           return 0
       else:
           if temp == None:
               return 0
       if type( temp )==list:
            temp = temp[0]
           
           
       for i in temp:
           if ( what == i ) or  (what in i ):
               if ( temp[i] != ignore):
                   return temp[i]
           else:
                self.find(temp[i],what)
       return 0
                 
       """          
           for i in temp:
               if (what == i) or (what in i):
                   result.append( temp[i])
                   print result
                   return temp[i]
               elif (i != temp[i]):
                     self.find(temp[i], what)
       return result
       """
                   
                   
                   
                   
                   
 
   #Searches for an element with a specific path in an XML tree        
   def findall(self, original, lista):
       
       temp = original
       resultaki = []
       k=0
       while k<len(lista):
           i = lista[k]
           if type(temp)==list:
               temp = temp[0]
           try:
               res = 1
               while res!=0:
                   res = self.find( temp, i,res)
                   resultaki.append(res)
           except:
               continue
           k = k + 1
       
       print resultaki
       return resultaki
   
   
class ASParser():
    id=None
    title=None
    published=None
    author=None
    activity_verb=None
    activity_object=None
    def __init__(self, author=None, activity_verb=None, activity_object=None):
        self.author=author
        self.activity_verb=activity_verb
        self.activity_object=activity_object
    def readItem (self,xmlName):
       #to Do
       return 0
    #Parses an Activity Streams entry
    def readEntry(self,listName):
       document = Decoder(listName).dictionary
       """
       Testing of Michael
       """
       print "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
       print document
       print "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"

       """
       End of testing
       """
       parsedList =Parse(document)
       #entry identification
       self.id= parsedList.find(parsedList.original,"id")
       self.title= parsedList.find(parsedList.original,"title")
       self.published=parsedList.find(parsedList.original,"published")
       #author: embeded on the XMPP id management of the user who published on the hub
       #self.author=ActivityStreamsAuthor.ActivityStreamsAuthor(name=parsedList.findall(parsedList.original, ["author","name"]), uri=parsedList.findall(parsedList.original, ["author","uri"]), id=parsedList.findall(parsedList.original, ["author","id"]), link=parsedList.findall(parsedList.original, ["author","link","href"]))
       #verb
       self.activity_verb= parsedList.find(parsedList.original, Prefixes.ACTIVITY+"verb")
       activity_object= parsedList.find(parsedList.original, Prefixes.ACTIVITY+"object")
       self.readASObject(activity_object)
           
    def readASObject(self,list):
        link_list=[]
        if (list is not None):
           #Test print
           print "printarw ta list objects"
           print (list)
           self.activity_object=ActivityStreamsObject.ActivityStreamsObject()
           #get content 
           #Modified to find the content under <div> tag only because of parser issues
           content_list=list.get(Prefixes.ATOM+'content')
           self.activity_object.set_content(content_list.get(Prefixes.ATOM+'div'))
           #print (list.get('content'))
           #get id
           self.activity_object.set_id(list.get(Prefixes.ATOM+'id'))
           #get displayName
           self.activity_object.set_display_name(list.get(Prefixes.ATOM+'title'))
           #get published
           self.activity_object.set_published(list.get(Prefixes.ATOM+'published'))
           #get summary
           self.activity_object.set_summary(list.get(Prefixes.ATOM+'summary'))
           #get updated
           self.activity_object.set_updated(list.get(Prefixes.ATOM+'updated'))
           #get links
           link=list.get(Prefixes.ATOM+'link')
           print "printarw ta prefixes tou link"
           print(list.get(Prefixes.ATOM+'link'))
           #for links in list.get(Prefixes.ATOM+'link'):
               #link_list.append(links)
           #print(link_list)
           #self.activity_object.set_url(link_list.get(Prefixes.ATOM+'href'))
           #get media
           
           
           
           #MUST BE ON THE END OF THE METHODS, to incorporate the other properties on the object
           #get object-type
           self.activity_object.set_object_type(list.get(Prefixes.ACTIVITY+'object-type'))
           #print (list.get('content'))
           
#===============================================================================
#           activity_object = parsedList.find(parsedList.original, Prefixes.ACTIVITY+"object")
#           print activity_object
# 
#           activity_inReplyto=None
#           activity_target= parsedList.find(parsedList.original, Prefixes.ACTIVITY+"target")
#           print activity_target
# 
#           padgets_campaign= parsedList.find(parsedList.original, Prefixes.PADGETS+"campaign")
#           print padgets_campaign
# 
#           padgets_platforms= parsedList.find(parsedList.original, Prefixes.PADGETS+"platforms")
#           print padgets_platforms
# 
# 
#           #print len( author)
#           parsedList.findall(parsedList.original, ["author","name"])
#===============================================================================

    def print_all(self):
        #self.author.printAll
        print (self.activity_verb)
        print (self.activity_object.getString())
           
       #to Do
   
document = Decoder("FirstPadgetsStreams.xml").dictionary
#print document
temp = Parse(document)
temp.printList()
print "lol"
print temp.original
#print "testaki"
print temp.findall(temp.original, ["facebookPage"])

import ListSearching
lolen = ListSearching.ListSearching(lista = document)
print lolen.searchingFunction(document, "facebookPage")
print lolen.apotelesma
"""
temp= ASParser()
temp.readEntry("FirstPadgetsStreams.xml")
print "psit"
temp.print_all()
"""

#yo = Decoder("iosif").dictionary
#hey = stupid(yo)
#hey.findall(hey.original, ["entry","author","name"])
