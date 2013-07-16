'''
Created on 30 may 2011

@author: PanosSp
'''


import sqlite3


class MyClass(object):
    '''
    classdocs
    '''


    def __init__(self):
        self.file = 'pubsub.db'
        b = self.conn = sqlite3.connect(self.file)        
        i = b.cursor()
        i.execute('CREATE TABLE affiliation(id INTEGER PRIMARY KEY, node_id INTEGER, jid TEXT, type VARCHAR(10));')
        i.execute('CREATE TABLE item(id INTEGER PRIMARY KEY, node_id INTEGER, name VARCHAR(255), payload BLOB, time DATETIME, who TEXT);')
        i.execute('CREATE TABLE node (id INTEGER PRIMARY KEY, name VARCHAR(255), type VARCHAR(100), config BLOB);')
        i.execute('CREATE TABLE roster(id INTEGER PRIMARY KEY, jid TEXT UNIQUE, subto INTEGER, subfrom INTEGER, jidto TEXT);')
        i.execute('CREATE TABLE subscription(id INTEGER PRIMARY KEY, node_id INTEGER, jid TEXT, type VARCHAR(10), config BLOB, subid VARCHAR(255), jidto TEXT);')
        i.execute('CREATE TABLE permissions(id INTEGER PRIMARY KEY, jid TEXT, auth varchar(10));')
        b.commit()
        #i.close()
        b.close()
        
        
MyClass()