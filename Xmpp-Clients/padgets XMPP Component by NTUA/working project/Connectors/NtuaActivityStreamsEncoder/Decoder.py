'''
Created on Mar 16, 2011

@author: michaelpetuxakis
'''

import xml.etree.cElementTree as ElementTree
from XmltoDict import XmlDictConfig

"""
    Helpful link
    http://www.learningpython.com/2008/05/07/elegant-xml-parsing-using-the-elementtree-module/
    http://www.faqs.org/docs/diveintopython/kgp_parse.html
"""
class Decoder:
    
    def __init__(self, file=None):
        if file==None:
            return 0
        else:    
            self.dictionary = None
            self.decoding(file)
            #self.dictionary = self.transform(self.dictionary)
            #print self.dictionary
    
    def decoding(self,file=None): 
        if file==None:
            return 0
        #from xml.dom import minidom
        tree = ElementTree.parse(file)
        root = tree.getroot()
        self.dictionary = XmlDictConfig(root)
        
    def transform(self):
            
            
            
        """
http://developer.yahoo.com/python/python-xml.html
import urllib
from xml.dom import minidom

WEATHER_URL = 'http://xml.weather.yahoo.com/forecastrss?p=%s'
WEATHER_NS = 'http://xml.weather.yahoo.com/ns/rss/1.0'

def weather_for_zip(zip_code):
    url = WEATHER_URL % zip_code
    dom = minidom.parse(urllib.urlopen(url))
    forecasts = []
    for node in dom.getElementsByTagNameNS(WEATHER_NS, 'forecast'):
        forecasts.append({
            'date': node.getAttribute('date'),
            'low': node.getAttribute('low'),
            'high': node.getAttribute('high'),
            'condition': node.getAttribute('text')
        })
    ycondition = dom.getElementsByTagNameNS(WEATHER_NS, 'condition')[0]
    return {
        'current_condition': ycondition.getAttribute('text'),
        'current_temp': ycondition.getAttribute('temp'),
        'forecasts': forecasts,
        'title': dom.getElementsByTagName('title')[0].firstChild.data
        
>>> from pprint import pprint
>>> pprint(weather_for_zip(66044)

OR

import urllib
from elementtree.ElementTree import parse

WEATHER_URL = 'http://xml.weather.yahoo.com/forecastrss?p=%s'
WEATHER_NS = 'http://xml.weather.yahoo.com/ns/rss/1.0'

def weather_for_zip(zip_code):
    url = WEATHER_URL % zip_code
    rss = parse(urllib.urlopen(url)).getroot()
    forecasts = []
    for element in rss.findall('channel/item/{%s}forecast' % WEATHER_NS):
        forecasts.append({
            'date': element.get('date'),
            'low': element.get('low'),
            'high': element.get('high'),
            'condition': element.get('text')
        })
    ycondition = rss.find('channel/item/{%s}condition' % WEATHER_NS)
    return {
        'current_condition': ycondition.get('text'),
        'current_temp': ycondition.get('temp'),
        'forecasts': forecasts,
        'title': rss.findtext('channel/title')
    }

    """
