#!/usr/bin/python2.6

import os.path
import json
import urllib2
import urllib
import urlparse
import BaseHTTPServer
import webbrowser
import facebook
import argparse
import sys

APP_ID = '121051994628297'
APP_SECRET = '15eba27bb4b8e2ac6056190bb5eb5a89'
ENDPOINT = 'graph.facebook.com'
REDIRECT_URI = 'http://147.102.6.34:8080/'
ACCESS_TOKEN = None
LOCAL_FILE = '.fb_access_tokennew'
STATUS_TEMPLATE = "{name}\033[0m: {message}"


#parser = argparse.ArgumentParser()
#parser.add_argument("testeddddddd!!!!!")
#PREDEF_STRING = str(parser.parse_args())


def get_url(path, args=None):
    args = args or {}
    if ACCESS_TOKEN:
        args['access_token'] = ACCESS_TOKEN
    if 'access_token' in args or 'client_secret' in args:
        endpoint = "https://"+ENDPOINT
    else:
        endpoint = "http://"+ENDPOINT
    return endpoint+path+'?'+urllib.urlencode(args)

def get(path, args=None):
    return urllib2.urlopen(get_url(path,args=args)).read()

class RequestHandler(BaseHTTPServer.BaseHTTPRequestHandler):

    def do_GET(self):
        global ACCESS_TOKEN
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()

        code = urlparse.parse_qs(urlparse.urlparse(self.path).query).get('code')
        code = code[0] if code else None
        if code is None:
            self.wfile.write("Sorry, authentication failed.")
            sys.exit(1)
        response = get('/oauth/access_token', {'client_id':APP_ID,
                                               'redirect_uri':REDIRECT_URI,
                                               'client_secret':APP_SECRET,
                                               'code':code})
        ACCESS_TOKEN = urlparse.parse_qs(response)['access_token'][0]
        open(LOCAL_FILE,'w').write(ACCESS_TOKEN)
        self.wfile.write("You have successfully logged in to facebook. "
                         "You can close this window now.")

def print_status(item, color=u'\033[1;35m'):
    print color+STATUS_TEMPLATE.format(name=item['from']['name'],
                                       message=item['message'].strip())

if __name__ == '__main__':
    if not os.path.exists(LOCAL_FILE):
        print "Logging you in to facebook..."
        webbrowser.open(get_url('/oauth/authorize',
                                 {'client_id':APP_ID,
                                 'redirect_uri':REDIRECT_URI,
                                 'scope':'publish_stream'}))

        httpd = BaseHTTPServer.HTTPServer(('147.102.6.34',8080), RequestHandler)
        while ACCESS_TOKEN is None:
            httpd.handle_request()
    else:
        print "using the local access_token file"
        ACCESS_TOKEN = open(LOCAL_FILE).read()
        #ACCESS_TOKEN = "121051994628297%7C2.DWMmsvzwBc_fGauavzOWkA__.3600.1292936400-100001678978385%7CLBR_djXruJcU8SPyU8Y2Z3GqbCc"
    graph = facebook.GraphAPI(ACCESS_TOKEN)
    profile = graph.get_object("me")
    #thefeed = graph.get_connections(profile["id"],"feed")
    graph.put_object("me", "feed", message="hooo")
    #print thefeed




        