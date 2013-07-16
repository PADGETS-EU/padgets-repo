'''
Created on 19 May 2011

@author: GIC
'''

class facebookDecode(Decode):
    
    def __init__(self,entry=None):
        
        self.entry = entry
        
    def fbFields(self):
        
        fields = ["author", "status"]
        
        author = ["name","nickname","fullname"]
        
        status = [] 
        
    def fbCheck(self):
        
        from Decoder import *
        dcd = Decoder("ASEntryExample.xml").dictionary
        from HandleDict import HandleDictionary
        astr = HandleDictionary(dcd)        
        v = astr.findall("platforms")
        