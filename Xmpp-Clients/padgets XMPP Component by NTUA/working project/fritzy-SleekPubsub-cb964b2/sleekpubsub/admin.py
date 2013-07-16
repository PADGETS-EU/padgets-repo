'''
Created on 02 june 2011

@author: PanosSp
'''
from .nodehandler import NodeAccess



class Whitelist(object):
    '''
    classdocs
    '''

    def __init__(self):
        self.whitelist = ['panos@gic/lab','mpetyx@gic','panos@gic/spark']
           
    def add(self,jid):  
        self.whitelist.append(jid)
        

    def remove(self,jid):
        self.whitelist.remove(jid)
        

class Admin(object):



    def __init__(self):
        
        self.publishers = Whitelist()
        self.subscribers = Whitelist()
        
       
    def create_node(self,pubsub,node):
        NodeAccess().createnode(pubsub,node)
        
         
               
    def delete_node(self,pubsub,node):
        NodeAccess().removenode(pubsub,node)
        
        
        
    def subscribe(self,pubsub,node,subscriber):
        NodeAccess().subscribenode(pubsub,node,subscriber)
    
    def unsubscribe(self,pubsub,node,subscriber):
        NodeAccess().unsubscribenode(pubsub,node,subscriber)   
        
        
    def register_publisher(self):
        
        '''
        Constructor
        '''    
        

    def subscribe_to_all_nodes(self,pubsub,subscriber): 
        for each_node in pubsub.nodes.allnodes :
            self.subscribe(pubsub,each_node,subscriber)
             
        
        
        
        
        
        
        
        
        
        
        
        
        