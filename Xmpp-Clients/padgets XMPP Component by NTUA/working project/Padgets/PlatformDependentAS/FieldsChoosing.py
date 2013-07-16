'''
Created on 19 May 2011

@author: GIC
'''

class Decode:
    
    from  NtuaActivityStreamsEncoder import Decoder
    
    def __init__(self, entry=None):
            
        if entry != None:
            self.message = Decoder.Decoder( entry )
    
    def manualDecode(self, entry):
        self.message = Decoder.Decoder( entry )
        
    

        