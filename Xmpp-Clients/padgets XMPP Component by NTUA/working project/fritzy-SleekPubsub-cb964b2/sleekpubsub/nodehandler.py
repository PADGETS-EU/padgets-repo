'''
Created on 26 May 2011

@author: panos
'''

class NodeAccess(object):
    '''
    classdocs
    '''


    def __init__(self):
        #self.pubsub=selfparams
        '''do this'''
        

    def createnode(self,pbobj,thenode):        
        pbobj.createNode(thenode,use_db=True)
        print ('just created a new node named: '+thenode)
    
    def removenode(self,pbobj,thenode):
        pbobj.deleteNode(thenode)
        print ('just deleted node: '+thenode)  
        
    def subscribenode(self,pbobj,thenode,subscriber):
        pbobj.subscribeNode(thenode,subscriber)
        print ('just subscribed :'+subscriber+' to node: '+thenode)     
    
    def unsubscribenode(self,pbobj,thenode,subscriber):
        pbobj.unsubscribeNode(thenode,subscriber)
        print ('just unsubscribed :'+subscriber+' from node: '+thenode)     