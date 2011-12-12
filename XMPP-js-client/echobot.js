var BOSH_SERVICE = '/http-bind';
var connection = null;
var activityStreamsNS='http://activitystrea.ms/spec/1.0/';
var padgetsNS='http://padgets.eu/spec/1.0/';
//var published=null;
//var author_name=null;

function log(msg) 
{
    $('#log').append('<div></div>').append(document.createTextNode(msg));
}

function logHTML(msg)
{
    $('#log').append('<div></div>').append(msg);
}

function onConnect(status)
{
    if (status == Strophe.Status.CONNECTING) {
	log('Strophe is connecting.');
    } else if (status == Strophe.Status.CONNFAIL) {
	log('Strophe failed to connect.');
	$('#connect').get(0).value = 'connect';
    } else if (status == Strophe.Status.DISCONNECTING) {
	log('Strophe is disconnecting.');
    } else if (status == Strophe.Status.DISCONNECTED) {
	log('Strophe is disconnected.');
	$('#connect').get(0).value = 'connect';
    } else if (status == Strophe.Status.CONNECTED) {
	log('Strophe is connected.');
	/*log('ECHOBOT: Send a message to ' + connection.jid + 
	    ' to talk to me.');*/

	connection.addHandler(onMessage, null, 'message', null, null,  null); 
	connection.send($pres().tree());
    }
}

/** Function: getText
     *  Get the concatenation of all text children of an element.
     *
     *  Parameters:
     *    (XMLElement) elem - A DOM element.
     *
     *  Returns:
     *    A String with the concatenated text of all text element children.
     */
    function getText(elem)
    {
        if (!elem) { return null; }

        var str = "";
        if (elem.childNodes.length === 0 && elem.nodeType ==
            Strophe.ElementType.TEXT) {
            str += elem.nodeValue;
        }

        for (var i = 0; i < elem.childNodes.length; i++) {
            if (elem.childNodes[i].nodeType == Strophe.ElementType.TEXT) {
                str += elem.childNodes[i].nodeValue;
            }
			else if (elem.nodeType == Strophe.ElementType.NORMAL) {
					str = str+elem.childNodes[i].nodeName+": ";
					str+=getText(elem.childNodes[i]);
			}
		}

        return str;
    }
	
	function createHTML(elem)
    {
        if (!elem) { return null; }

        var str = "";
        if (elem.childNodes.length === 0 && elem.nodeType ==
            Strophe.ElementType.TEXT) {
            str += elem.nodeValue;
        }
		/*for (var i = 0; i < 3; i++) {
		str+=elem.childNodes[i].nodeValue + ' ..... ';
		str+=elem.childNodes[i].nodeName + ' ..... ';
		}*/
		
		var auth_name="";
		var auth_uri="";
		var auth_link="";
		var object_type="";
		var object_content="";
		var object_id="";
		var object_link="";
		var targ_object_type="";
		var targ_object_link="";
		var campaign_name="";
		var campaign_id="";
		var platform_name="";
		
		//var xml = "<music><album>Beethoven</album></music>";
		//var result = $(xml).find("album").text();
		
		//read general info
		var id=getText(elem.getElementsByTagName("id")[0]);
		var title=getText(elem.getElementsByTagName("title")[0]);
		var published=getText(elem.getElementsByTagName("published")[0]);
		
		//read author
		var author=elem.getElementsByTagName("author")[0];
		if (author!=null){
			auth_name=getText(author.getElementsByTagName("name")[0]);
			auth_uri= getText(author.getElementsByTagName("uri")[0]);
			auth_link= author.getElementsByTagName("link")[0].getAttribute('href');
		}
		
		//read verb
		var verb= getText(elem.getElementsByTagName("activity:verb")[0]);
		if(!verb || verb == null){
			verb = getText(elem.getElementsByTagName('verb')[0]);
		}
		if(!verb || verb == null){
			verb = getText(elem.getElementsByTagNameNS(activityStreamsNS, 'verb')[0]);
		}
		
		
		//read object
		var object=elem.getElementsByTagName("activity:object")[0];
		if(!object || object == null){
			object = elem.getElementsByTagName('object')[0];
		}
		if(!object || object == null){
			object = elem.getElementsByTagNameNS(activityStreamsNS, 'object')[0];
		}
		if (object!=null){
			object_type=getText(object.getElementsByTagName("activity:object-type")[0]);
			if(!object_type || object_type == null){
				object_type = getText(object.getElementsByTagName('object-type')[0]);
			}
			if(!object_type || object_type == null){
				object_type = getText(object.getElementsByTagNameNS(activityStreamsNS, 'object-type')[0]);
			}
			
			object_content= getText(object.getElementsByTagName("content")[0]);
			object_id=  getText(object.getElementsByTagName("id")[0]);
			object_link= object.getElementsByTagName("link")[0].getAttribute('href');
		}
		
		
		
		//read target object
		var targ_object=elem.getElementsByTagName("activity:target")[0];
		if(!targ_object || targ_object == null){
			targ_object = elem.getElementsByTagName('target')[0];
		}
		if(!targ_object || targ_object == null){
			targ_object = elem.getElementsByTagNameNS(activityStreamsNS, 'target')[0];
		}
		if (targ_object!=null){
			targ_object_type=getText(targ_object.getElementsByTagName("activity:object-type")[0]);
			if(!targ_object_type || targ_object_type == null){
				targ_object_type = getText(targ_object.getElementsByTagName('object-type')[0]);
			}
			if(!targ_object_type || targ_object_type == null){
				targ_object_type = getText(targ_object.getElementsByTagNameNS(activityStreamsNS, 'object-type')[0]);
			}
			//targ_object_content= getText(object.getElementsByTagName("content")[0]);
			//targ_object_id=  getText(object.getElementsByTagName("id")[0]);
			targ_object_link= targ_object.getElementsByTagName("link")[0].getAttribute('href');
		}
		
		//read campaign
		var campaign=elem.getElementsByTagName("padgets:campaign")[0];
		if(!campaign || campaign == null){
			campaign = elem.getElementsByTagName('campaign')[0];
		}
		if(!campaign || campaign == null){
			campaign = elem.getElementsByTagNameNS(padgetsNS, 'campaign')[0];
		}
		if (campaign!=null){
			campaign_name=campaign.getAttribute('name');
			campaign_id=campaign.getAttribute('id');
		}
		
		//read platform
		var platforms=elem.getElementsByTagName("padgets:platforms")[0];
		if(!platforms || platforms == null){
			platforms = elem.getElementsByTagName('platforms')[0];
		}
		if(!platforms || platforms == null){
			platforms = elem.getElementsByTagNameNS(padgetsNS, 'platforms')[0];
		}
		if (platforms!=null) {
			var platform=platforms.getElementsByTagName("padgets:platform")[0];
			if(!platforms || platforms == null){
				platform = platforms.getElementsByTagName('platform')[0];
			}
			if(!platforms || platforms == null){
				platform = platforms.getElementsByTagNameNS(padgetsNS, 'platform')[0];
			}
		}
		if (platform!=null){
			platform_name=getText(platform.getElementsByTagName("name")[0]);
		}
		
		/*create html output */
		
		str+="<table border=\"0\"><tr><td rowspan=\"2\">Image</td><td rowspan=\"2\">"+platform_name+"</td><td>";
		str+=" <a href=\""+auth_link+"\">"+auth_name+"</a> "+ verb +"ed "+ "<a href=\""+object_link+"\">"+object_type+"</a>";
		if (targ_object!=null){str+= " at your <a href=\""+targ_object_link+"\">"+targ_object_type+"</a>";}
		str+="</td></tr><tr><td>";
		if (object_content!=null){str+= object_content;}
		str+="</td></tr><tr><td>"+published+"</td>";
		str+="<td></td><td>"+campaign_name+"</td></tr></table>";
		str+="<hr color=#990066 size=1 width=100%>";
        /*for (var i = 0; i < elem.childNodes.length; i++) {
			if (elem.childNodes[i].nodeType == Strophe.ElementType.NORMAL) {
                //if (elem.childNodes[i].nodeName=='published'){
            }
            /*else if (elem.childNodes[i].nodeType == Strophe.ElementType.TEXT) {
				if (elem.childNodes[i].nodeName=='published'){
					str +='   PUBLISHEDDDDDDD    '}
                //str += elem.childNodes[i].nodeValue;
            }
		}*/

        return str;
    }
		

function onMessage(msg) {
    var to = msg.getAttribute('to');
    var from = msg.getAttribute('from');
    var type = msg.getAttribute('type');
    var elems = msg.getElementsByTagName('body');

    if (type == "chat" && elems.length > 0) {
	var body = elems[0];

	//log('ECHOBOT: I got a message from ' + from + ': ' + getText(body));
		
	logHTML(createHTML(body));

    
	/*var reply = $msg({to: from, from: to, type: 'chat'}).cnode(Strophe.copyElement(body));
	connection.send(reply.tree());*/

	//log('ECHOBOT: I sent ' + from + ': ' + getText(body));
	
    }

    // we must return true to keep the handler alive.  
    // returning false would remove it after it finishes.
    return true;
}

function doUnload()
{
	//if (window.event.clientX < 0 && window.event.clientY < 0)
	//{
		connection.disconnect();
	//}
}

//function to format message time
function relativeTime(pastTime)
{	
	var origStamp = Date.parse(pastTime);
	var curDate = new Date();
	var currentStamp = curDate.getTime();
	
	var difference = parseInt((currentStamp - origStamp)/1000);

	if(difference < 0) return false;

	if(difference <= 5)				return "Just now";
	if(difference <= 20)			return "Seconds ago";
	if(difference <= 60)			return "A minute ago";
	if(difference < 3600)			return parseInt(difference/60)+" minutes ago";
	if(difference <= 1.5*3600) 		return "One hour ago";
	if(difference < 23.5*3600)		return Math.round(difference/3600)+" hours ago";
	if(difference < 1.5*24*3600)	return "One day ago";
	
	var dateArr = pastTime.split(' ');
	return dateArr[4].replace(/\:\d+$/,'')+' '+dateArr[2]+' '+dateArr[1]+(dateArr[3]!=curDate.getFullYear()?' '+dateArr[3]:'');
}

$(document).ready(function () {
    connection = new Strophe.Connection(BOSH_SERVICE);

    // Uncomment the following lines to spy on the wire traffic.
    //connection.rawInput = function (data) { log('RECV: ' + data); };
    //connection.rawOutput = function (data) { log('SEND: ' + data); };

    // Uncomment the following line to see all the debug output.
    //Strophe.log = function (level, msg) { log('LOG: ' + msg); };


    $('#connect').bind('click', function () {
	var button = $('#connect').get(0);
	if (button.value == 'connect') {
	    button.value = 'disconnect';

	    connection.connect($('#jid').get(0).value,
			       $('#pass').get(0).value,
			       onConnect);
	} else {
	    button.value = 'connect';
	    connection.disconnect();
	}
    });
	//onbeforeunload=connection.disconnect();
});



