<?php

    class ActivityStreamsEncoder {

        private $id = "";
        private $title = "";
        private $link = "";
        private $entries = array();
        
        function __construct($id, $title = '', $description = '') {
            $this->id = $id;
            $this->title = $title;
            $this->description = $description;
        }
        
        function addEntry(ActivityStreamsEntry $entry) {
            $this->entries[] = $entry;
        }
        
        function __toString() {
            // Display header
            $string = '';
            $updated_time = date('c',time());
            $string .=  <<<END
<?xml version="1.0" encoding="UTF-8"?>
<feed
  xmlns="http://www.w3.org/2005/Atom"
  xmlns:thr="http://purl.org/syndication/thread/1.0"
  xmlns:activity="http://activitystrea.ms/spec/1.0/"
  xml:lang="en"
   >
   
    <title type="text">{$this->title}</title>
    <updated>{$updated_time}</updated>
    
    <link rel="alternate" type="text/html" href="{$this->link}" />
    <id>{$this->id}</id>
    <link rel="self" type="application/atom+xml" href="{$this->id}" />
   
END;
            if (sizeof($this->entries))
                foreach($this->entries as $entry)
                    if ($entry instanceof ActivityStreamsEntry) $string .= (string) $entry;
                    
            $string .=  <<<END
</feed>
END;
            return $string;
        }
        
    }
    
    class ActivityStreamsEntry {
        
        public $id = "";
        public $title = "";
        public $author;
        public $objects = array();
        public $verbs = array();
        public $published;
        
        function __construct() {
            $this->published = time();
        }
        
        function addObject(ActivityStreamsObject $object) {
            $this->objects[] = $object;
        }
        function addVerb($verb) {
            $this->verbs[] = $verb;
        }
        function setAuthor(ActivityStreamsAuthor $author) {
            $this->author = $author;
        }
        
        function __toString() {
            
            $string = '';
            $string .=  "\n<entry>";
            $published = date('c',$this->published);
            $string .=  <<< END
            <id>{$this->id}</id>
            <title type="text"><![CDATA[{$this->title}]]></title>
            <published>{$published}</published>
END;
            if ($this->author instanceof ActivityStreamsAuthor) $string .=  $this->author;
            if (sizeof($this->verbs)) foreach($this->verbs as $verb)
                $string .=  "\n<activity:verb>{$verb}</activity:verb>";
            if (sizeof($this->objects)) foreach($this->objects as $object)
                if ($object instanceof ActivityStreamsObject) $string .=  $object;
            $string .=  "\n</entry>";
            
            return $string;
            
        }
        
    }
    
    class ActivityStreamsObject {
        public $properties = array();
        
        function getProperty($property_name) {
            if (isset($this->properties[$property_name])) return $this->properties[$property_name];
        }
        function setProperty($property_name, $property_value) {
            $this->properties[$property_name] = $property_value;
        }
        function __construct() {
            $this->published = time();
        }

        function addObjectType($object_type) {
            if (!isset($this->properties['object-type'])) $this->properties['object-type'] = array();
            $this->properties['object-type'][] = (string) $object_type;
        }
        
        function __toString() {
            $string = '';
            $string .= "\n<activity:object>";
            foreach($this->properties as $property => $value) {
                if (!is_array($value)) $value = array($value);
                switch($property) {
                    case 'title':
                                $attr = 'type="html"';
                                break;
                    case 'link':
                                $attr = 'rel="alternate" type="text/html" href="'.$value[0].'"';
                                $value = array(''); 
                                break;
                    default:    $attr = '';
                                break;
                }
                if (sizeof($value))
                    foreach($value as $val) {
                        if (empty($val))
                            $string .=  "\n\t<{$property} {$attr} />";
                        else if ($property == 'content' || $property == 'title')
                            $string .= "\n\t<{$property} {$attr}><![CDATA[{$val}]]><{$property}>";
                        else
                            $string .=  "\n\t<{$property} {$attr}>{$val}<{$property}>";
                    }
            }
            $string .=  "\n</activity:object>";
            
            return $string;
        }
        
    }
    
    class ActivityStreamsAuthor extends ActivityStreamsObject {
        function __construct() {
            $this->addObjectType("http://activitystrea.ms/schema/1.0/person");
        }
        function __toString() {
            $string = parent::__toString();
            $string = str_replace('activity:object>','author>',$string);
            return $string;
        }
    }
    
    
    
    $stream = new ActivityStreamsEncoder('http://samplecompany.com/tasks/activity/', 'Task activities at Sample Company');
    
    $object = new ActivityStreamsObject();
    $object->setProperty('id','http://samplecompany.com/tasks/23432/');
    $object->setProperty('title','Sample task.');
    $object->setProperty('content','...');
    $object->addObjectType('http://samplecompany.com/activity/schema/1.0/task');
    $object->addObjectType('http://activitystrea.ms/schema/1.0/note');
    $object->setProperty('link','http://samplecompany.com/tasks/23432/');
    
    $author = new ActivityStreamsAuthor();
    $author->setProperty('name','Roger Taylor');
    $author->setProperty('uri','http://samplecompany.com/people/Roger+Taylor/');
    
    $entry = new ActivityStreamsEntry();
    $entry->addVerb("http://samplecompany.com/activity/schema/1.0/complete");
    $entry->addVerb("http://activitystrea.ms/schema/1.0/update");
    $entry->title = "Roger Taylor completed a task.";
    $entry->id = 'http://samplecompany.com/tasks/activity/23432/3242345/';
    $entry->addObject($object);
    $entry->setAuthor($author);
    
    $stream->addEntry($entry);
    
    header('Content type: text/xml');
    echo $stream;

    