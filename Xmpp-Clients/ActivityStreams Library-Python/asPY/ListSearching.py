class ListSearching:
    
    def __init__(self, lista = None ,find = None):
        
        self.lista = []
        self.original = lista
        print self.unfold(self.original)
        self.finder = find
        
        if ( self.lista == None ) or ( self.find == None ):
            print "nope"
        
        #self.finding()
        self.search(self.lista, "campaign1")
        self.apotelesma = []
        
        
    def unfold( self, temp ):
   
       if type(temp) == str:
           self.lista.append(temp)
       else: 
           for i in temp:
               self.unfold( i )
    
               
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
   

    def search(self, temp, key):
        
        apotelesma = []
        found = 0
        if type( temp )== str:
           return 0
        else:
           if temp == None:
               return 0
        if type( temp )==list:
            temp = temp[0]
        
        for keys in temp:
            if key in keys:
                apotelesma.append(temp.get(keys))
                found = 1
        if found==0:
            self.search(temp, key)
        return apotelesma
        
        
    def searchingFunction(self,lista, missing):
        
        apotelesma = []
        flag = 0
    
        if type( lista )== str:
           return lista
        else:
           if lista == None:
               return 2
        #if type( temp )==list:
        #    temp = temp[0]
        
        if missing in lista:
            self.apotelesma.append( lista[missing] )
        
        for i in lista:
            if missing in i:
                self.apotelesma.append(lista[i])
                flag = 1 
            
        if flag ==1:
            return self.apotelesma
        else:
            for i in lista:
                self.searchingFunction(lista[i],missing)
        
example = {'{http://padgets.eu/spec/1.0/}campaign1': {'id': 'idCampaign', 'name': 'campaignName'}, '{http://activitystrea.ms/spec/1.0/}verb': 'post', '{http://activitystrea.ms/spec/1.0/}target': {'{http://www.w3.org/2005/Atom}title2': {'type': 'text/html'}, '{http://activitystrea.ms/spec/1.0/}object-type': 'article', '{http://www.w3.org/2005/Atom}content1': {'type': 'text/html'}, '{http://www.w3.org/2005/Atom}summary': 'this is a test target article'}, '{http://www.w3.org/2005/Atom}id': 'idofEntry', '{http://padgets.eu/spec/1.0/}platforms': {'{http://padgets.eu/spec/1.0/}facebook': {'{http://padgets.eu/spec/1.0/}account1': {'username': 'iosif.alvertis', 'email': 'alvertisjo@hotmail.com'}, '{http://padgets.eu/spec/1.0/}facebookPage2': {'id': 'page1ID', 'name': 'My Page name'}, '{http://padgets.eu/spec/1.0/}facebookPage3': {'id': 'page2ID', 'name': 'My Page name no2'}}, '{http://padgets.eu/spec/1.0/}yahoo': {'{http://padgets.eu/spec/1.0/}account1': {'username': 'alvertisjo', 'email': 'alvertisjo@yahoo.com'}}}, '{http://www.w3.org/2005/Atom}title': 'NameofEntry', '{http://www.w3.org/2005/Atom}published': '17/6/2011', '{http://activitystrea.ms/spec/1.0/}object': {'{http://www.w3.org/2005/Atom}link2': {'href': 'http://www.image.gr', 'type': 'image/jpeg', 'rel': 'enclosure'}, '{http://www.w3.org/2005/Atom}link1': {'href': 'http://www.in.gr', 'type': 'text/html', 'rel': 'alternate'}, '{http://activitystrea.ms/spec/1.0/}object-type': 'bookmark', '{http://www.w3.org/2005/Atom}id': 'statusID:221212', '{http://www.w3.org/2005/Atom}content': {'{http://www.w3.org/2005/Atom}div': 'This is the content of the message', 'type': 'text/html'}, '{http://purl.org/syndication/thread/1.0}in-reply-to': {'{http://activitystrea.ms/spec/1.0/}object': {'{http://www.w3.org/2005/Atom}title': 'this is the reply name', '{http://www.w3.org/2005/Atom}content': {'{http://www.w3.org/2005/Atom}div': None, 'type': 'text/html'}, '{http://activitystrea.ms/spec/1.0/}object-type': 'comment', '{http://www.w3.org/2005/Atom}id': 'replyID'}}}}
lolen = ListSearching(lista = example)
print lolen.searchingFunction(example, "link")
print lolen.apotelesma
