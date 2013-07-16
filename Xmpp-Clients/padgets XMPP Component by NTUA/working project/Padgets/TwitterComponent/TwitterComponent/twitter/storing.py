'''
Created on Apr 7, 2011

@author: michaelpetuxakis
'''

class store:
        

        
    def save(self, what=None):
        
        if what==None:
            print "There is nothing to save"
            return 0
        
        users = open("users.txt","r")
        temp = users.readlines()
        users.close()
        temp.append(what)
        users = open("users.txt","w")
        for line in temp:
            users.write(line)
        users.close()
        
        
    def load(self, Person=None):
        
        if Person==None:
            print "There is nothing to load"
            return 0
        
        users = open("users.txt","r")
        lines = users.readlines()
        users.close()
        i = 0
        while i<len(lines):
            if (Person.lower() in lines[i]) or (Person in lines[i]):
                key = lines[i+1]
                secret = lines[i+2]
                break
            i = i + 1
        key = key.replace("\n","")
        secret = secret.replace("\n","")
        return "key=%sPAUSEsecret=%s"%(key,secret)
        
    
    def search_for_item(self, Person=None):
        
        if Person==None:
            print "There is nothing to search"
            return 0
        
        users = open("users.txt","r")
        temp = users.readlines()
        users.close()
        
        for line in temp:
            if (Person in line) or (Person.lower() in line):
                return 1
        return 0
        
        
        