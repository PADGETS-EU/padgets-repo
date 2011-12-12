'''
Created on May 2, 2011

@author: michaelpetuxakis
'''

class HandleDictionary:
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
    
    def find(self, temp, what):
        
        if type( temp )== str:
            return 0
        else:
            for i in temp:
                if (what == i) or (what in i):
                    return temp[i]
                else:
                    self.find(temp[i], what)
            
    def findall(self, original, lista):
        
        temp = original
        for i in lista:
            temp = self.find( temp, i)
        
        print temp
        return temp
    
"""    
from Decoder import *

yo = Decoder("ASEntryExample.xml").dictionary
hey = HandleDictionary(yo)
#hey.rixto()
#i=4
for i in hey.lista:
    print i
    

#print "tha psaksw to :"+hey.lista[i]
#mac = hey.find(hey.original, "author")#hey.lista[i])
#print mac
#print hey.find(mac,"name")
#print len( mac)
hey.findall(hey.original, ["verb"])
hey.findall(hey.original, ["object","object-type"])
"""