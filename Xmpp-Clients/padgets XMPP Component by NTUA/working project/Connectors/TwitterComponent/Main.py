'''
Created on Apr 27, 2011

@author: michaelpetuxakis
'''

class Main():
    
    
    
    def __init__(self):
        """
            
        """
        
    def publishAtTwitter(self):
        #This method listens at activity streams format
        
        #if post was successfull
        return 1
        
    def workflow(self):
        
        import NtuaActivityStreamsEncoder
        
        from Connector import Connector
        message = Connector().ReceiveMessage()
        message = NtuaActivityStreamsEncoder.Decoder( message ).dictionary
        from UltraStupidTest import stupid
        message  = stupid(message)
        message = NtuaActivityStreamsEncoder.Encoder(author=message.findall(message.original,['author']),title=message['title'],
                                                     status=message['status'])
        
        from TwitterConnect import publishAtTwitter
        #This method listens at activity streams format
        publishAtTwitter(message)
        
        
        return 1

if __name__ == '__main__':
    pass