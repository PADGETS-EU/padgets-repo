/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Campaigntopics;
import de.fhg.fokus.persistence.Location;
import de.fhg.fokus.persistence.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.joda.time.DateMidnight;

/**
 *
 * @author hgo
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


        Campaigntopics ct1 = new Campaigntopics();
        ct1.setTopic("Campaigntopic 1");
        Campaigntopics ct2 = new Campaigntopics();
        ct2.setTopic("Campaigntopic 2");

        List<Campaigntopics> ct_list = new ArrayList<>();
        ct_list.add(ct1);
        ct_list.add(ct2);
        c.setCampaigntopicsList(ct_list);

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

        return m;
    }

    public List<Message> makeSampleMessageList() {
        List<Message> mList = new ArrayList<>();
        mList.add(makeSampleMessage());
        mList.add(makeSampleMessage());
        return mList;
    }

}
