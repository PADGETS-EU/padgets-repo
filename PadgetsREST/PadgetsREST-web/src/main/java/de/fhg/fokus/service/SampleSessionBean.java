/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.joda.time.DateMidnight;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
public class SampleSessionBean {

    public Campaign makeSampleCampaign() {
        Campaign c = new Campaign();
        c.setIdCampaign(99999999);
        c.setTitle("Campaign Title");
        c.setActive(false);

        DateMidnight cDate = new DateMidnight();
        c.setCreationdate(cDate.toDate());
        c.setStartdate(cDate.plusDays(2).toDate());
        c.setEnddate(cDate.plusMonths(2).toDate());


        c.setNotes("These are Notes");
        c.setUrl("http://campaignURI.com");
        c.setHashTag("#HashTag");


        Campaigntopics ct1 = new Campaigntopics();
        ct1.setTopic("Campaigntopic 1");
        Campaigntopics ct2 = new Campaigntopics();
        ct2.setTopic("Campaigntopic 2");

        List<Campaigntopics> ct_list = new ArrayList<>();
        ct_list.add(ct1);
        ct_list.add(ct2);
        c.setCampaigntopicsList(ct_list);
        
        c.setPublishchannelList(makeSamplePublishChannelList());

        Location l = new Location(9999);
        l.setName("Berlin");
        c.setIdLocation(l);
        return c;
    }

    public List<Campaign> makeSampleCampaignList() {
        List<Campaign> cList = new ArrayList<>();
        cList.add(makeSampleCampaign());
        cList.add(makeSampleCampaign());
        return cList;
    }

    public Message makeSampleMessage() {
        Message m = new Message();
        m.setContent("That is content!");
        m.setCreateTime(new Date());
        m.setLat(12.887263);
        m.setLng(15.9933);
        //TODO Luki fragen, was da unten hin soll
        m.setMediaPath("if this would be a media, here stands an URI");
        m.setPermalink("social link");
        m.setTitle("Message Title");
        m.setType("Type of Message");

        m.setPublisheditemList(makeSamplePublisheditemList());
        
        return m;
    }
    
    public List<Publisheditem> makeSamplePublisheditemList() {
                Publisheditem pi = new Publisheditem();
        pi.setIdPublishedItem(99999);
        pi.setIsPublished(Boolean.TRUE);
        pi.setNetworkPostId("network Post Id");
        pi.setPermalink("http://permalink/1234567890");
        
                Publisheditem pi2 = new Publisheditem();
        pi2.setIdPublishedItem(88888);
        pi2.setIsPublished(Boolean.FALSE);
        pi2.setNetworkPostId("network Post Id");
        pi2.setPermalink("http://permalink/ABCDEFGHIJ");
        
        List<Publisheditem> pList = new ArrayList<>();
        pList.add(pi);
        pList.add(pi2);
        
        return pList;
    }
    

    public List<Message> makeSampleMessageList() {
        List<Message> mList = new ArrayList<>();
        mList.add(makeSampleMessage());
        mList.add(makeSampleMessage());
        return mList;
    }
    
    public List<Publishchannel> makeSamplePublishChannelList(){
        List<Publishchannel> pcList = new ArrayList<>();
        Publishchannel pc = new Publishchannel();
        pc.setIdPublishChannel(10101010);
        pc.setName("Facebook");
        pc.setNetwork("Facebook");
        pc.setNetworkPageId("1234567890");
        pc.setOAuth2Token("oAuth2Token");
        
        Publishchannel pc2 = new Publishchannel();
        pc2.setIdPublishChannel(20202020);
        pc2.setName("Twitter");
        pc2.setNetwork("Twitter");
        pc2.setNetworkPageId("123456");
        pc2.setOAuth2Token("oAuth2Token");
        
        pcList.add(pc);
        pcList.add(pc2);
        
        return pcList;
    }

    public Userdata makeSampleUser() {
        Userdata ud = new Userdata();
        
        ud.setIdUserData(123456);
        ud.setAge(new Date());
        ud.setEmail("user@usermail.com");
        ud.setFirstname("Max");
        ud.setGender("male");
        ud.setOrganization("My Organization");
        ud.setSurname("Schmidt");
        ud.setUserRole("My Role {Manager / Helper}");
        ud.setMiddlename("Midd");
        ud.setUsername("Maxi1993");
        ud.setViewLanguage("African");
        
        
        return ud;
    }

    public Comment makeSampleComment() {
        Comment c = new Comment();
        c.setAnnotation(Boolean.TRUE);
        c.setContent("I am a Comment");
        c.setCreateTime(new Date());
        c.setIdComment(1234567);
        c.setNetwork("network:twitter, facebook, bloger");
        c.setNetworkCommentId("NetworkCommentId");
        c.setNetworkCommentUrl("NetworkCommentUrl");
        c.setUserProfileUrl("http://facebook.de/user/hannes");
        
        return c;
    }

    public List<Comment> makeSampleCommentList() {
         List<Comment> mList = new ArrayList<>();
         mList.add(makeSampleComment());
         mList.add(makeSampleComment());
         
         return mList;
    }

}
