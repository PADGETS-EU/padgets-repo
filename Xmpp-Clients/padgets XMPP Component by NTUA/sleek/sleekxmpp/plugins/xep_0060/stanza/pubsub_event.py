"""
    SleekXMPP: The Sleek XMPP Library
    Copyright (C) 2011  Nathanael C. Fritz
    This file is part of SleekXMPP.

    See the file LICENSE for copying permission.
"""

from sleekxmpp import Message
from sleekxmpp.xmlstream import register_stanza_plugin, ElementBase, ET, JID
from sleekxmpp.plugins.xep_0004 import Form


class Event(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'event'
    plugin_attrib = 'pubsub_event'
    interfaces = set()


class EventItem(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'item'
    plugin_attrib = name
    interfaces = set(('id', 'payload', 'node', 'publisher'))

    def set_payload(self, value):
        self.xml.append(value)

    def get_payload(self):
        childs = self.xml.getchildren()
        if len(childs) > 0:
            return childs[0]

    def del_payload(self):
        for child in self.xml.getchildren():
            self.xml.remove(child)


class EventRetract(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'retract'
    plugin_attrib = name
    interfaces = set(('id',))


class EventItems(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'items'
    plugin_attrib = name
    interfaces = set(('node',))


class EventCollection(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'collection'
    plugin_attrib = name
    interfaces = set(('node',))


class EventAssociate(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'associate'
    plugin_attrib = name
    interfaces = set(('node',))


class EventDisassociate(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'disassociate'
    plugin_attrib = name
    interfaces = set(('node',))


class EventConfiguration(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'configuration'
    plugin_attrib = name
    interfaces = set(('node',))


class EventPurge(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'purge'
    plugin_attrib = name
    interfaces = set(('node',))


class EventDelete(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'delete'
    plugin_attrib = name
    interfaces = set(('node', 'redirect'))

    def set_redirect(self, uri):
        del self['redirect']
        redirect = ET.Element('{%s}redirect' % self.namespace)
        redirect.attrib['uri'] = uri
        self.xml.append(redirect)

    def get_redirect(self):
        redirect = self.xml.find('{%s}redirect' % self.namespace)
        if redirect is not None:
            return redirect.attrib.get('uri', '')
        return ''

    def del_redirect(self):
        redirect = self.xml.find('{%s}redirect' % self.namespace)
        if redirect is not None:
            self.xml.remove(redirect)


class EventSubscription(ElementBase):
    namespace = 'http://jabber.org/protocol/pubsub#event'
    name = 'subscription'
    plugin_attrib = name
    interfaces = set(('node', 'expiry', 'jid', 'subid', 'subscription'))

    def set_jid(self, value):
        self._set_attr('jid', str(value))

    def get_jid(self):
        return JID(self._get_attr('jid'))


register_stanza_plugin(Message, Event)
register_stanza_plugin(Event, EventCollection)
register_stanza_plugin(Event, EventConfiguration)
register_stanza_plugin(Event, EventPurge)
register_stanza_plugin(Event, EventDelete)
register_stanza_plugin(Event, EventItems)
register_stanza_plugin(Event, EventSubscription)
register_stanza_plugin(EventCollection, EventAssociate)
register_stanza_plugin(EventCollection, EventDisassociate)
register_stanza_plugin(EventConfiguration, Form)
register_stanza_plugin(EventItems, EventItem, iterable=True)
register_stanza_plugin(EventItems, EventRetract, iterable=True)
