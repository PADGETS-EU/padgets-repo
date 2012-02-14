/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.*;
import de.fhg.fokus.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
@Path("campaign")
public class CampaignResource {

    @EJB
    private PublishchannelFacade publishchannelFacade;
    @EJB
    private LocationFacade locationFacade;
    @EJB
    private CampaigntopicsFacade campaigntopicsFacade;
    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private SampleSessionBean sampleSessionBean;
    @EJB
    private CampaignFacade campaignFacade;

    /**
     * Returns all campaigns for the given user. It also returns the campaign
     * location object and the campaign topics. <br /> Address: GET
     * [server]/resources/campaign?sid=test_user
     *
     * @param sid valid session id
     * @return list of campaigns
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Campaign> getCampaigns(@DefaultValue("test_user") @QueryParam("sid") String sid) {
        if (sid.equals("test_user")) {//return test data
            return sampleSessionBean.makeSampleCampaignList();
        }
        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Campaign c = new Campaign();
            c.setTitle("The session id is not valid!");
            List<Campaign> cList = new ArrayList<>();
            cList.add(c);
            return cList;
        }
        Userdata ud = udList.get(0);

        if (ud.getUserRole().equals("manager")) {
            return ud.getCampaignListManager();
        } else { //it's a helper
            return ud.getCampaignListHelpers();
        }
    }

    /**
     * Create new campaign and returns the persisted object (with valid id).
     * Every campaign needs a location. Therefore a location object inside the
     * campaign is needed (id is enough). It is also possible to sends a list of
     * initial campaign topics (id is not needed) inside the campaign object. A
     * list of related publishChannels can also be inside the campaign object<br
     * />
     *
     * Address: POST [server]/resources/campaign?sid=test_user
     *
     * @param campaign Campaign object
     * @param sid valid session id
     * @return
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Campaign createCampaign(Campaign campaign, @DefaultValue("test_user") @QueryParam("sid") String sid) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleCampaign();
        }

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Campaign c = new Campaign();
            c.setTitle("The session id is not valid!");
            return c;
        }
        Userdata ud = udList.get(0);

        campaign.setIdUser(ud);
        Location l = locationFacade.find(campaign.getIdLocation().getIdLocation());
        campaign.setIdLocation(l);


        List<Campaigntopics> topicList = null;
        if (campaign.getCampaigntopicsList() != null) {
            topicList = campaign.getCampaigntopicsList();
            campaign.setCampaigntopicsList(null);
        }


        if (campaign.getPublishchannelList() != null) {
            List<Publishchannel> pcList = campaign.getPublishchannelList();
            campaign.setPublishchannelList(new ArrayList());
            for (Publishchannel pc : pcList) {
                Publishchannel pcInDb = publishchannelFacade.find(pc.getIdPublishChannel());
                campaign.addPublishchannel(pcInDb);
            }
        }
        campaign.setCreationdate(new Date());//today

        campaignFacade.create(campaign);

        if (topicList != null) {
            for (Campaigntopics ct : topicList) {
                ct.setCampaignidCampaign(campaign);
                campaigntopicsFacade.create(ct);
                campaign.addCampaigntopic(ct);
            }
        }

        //update user entity
        ud.addCampaignManager(campaign);

        //TODO check if campaign id is valid
        return campaign;
    }

    /**
     * Change the values of the campaign object of the database with the given
     * one. It don't change foreign keys (relations). Only the campaign manager
     * can change the campaign object.<br />
     *
     * Address: PUT [server]/resources/campaign/[campId]?sid=test_user
     *
     * @param campaign Campaign Object
     * @param sid session id
     * @param campaignId id from the campaign
     * @return the campaign object from database (changed or unchanged)
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Campaign updateCampaign(Campaign campaign, @DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer campaignId) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleCampaign();
        }

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Campaign c = new Campaign();
            c.setTitle("The session id is not valid!");
            return c;
        }
        Userdata ud = udList.get(0);

        Campaign dbCampaign = campaignFacade.find(campaignId); //get requested campaign

        if (dbCampaign == null) {
            Campaign c = new Campaign();
            c.setTitle("It exists no campaign with this id!");
            return c;
        }

        if (dbCampaign.getIdUser().equals(ud)) { //is the user the campaign manager?
            dbCampaign.setActive(campaign.getActive());
            dbCampaign.setCreationdate(campaign.getCreationdate());
            dbCampaign.setEnddate(campaign.getEnddate());
            dbCampaign.setHashTag(campaign.getHashTag());
            dbCampaign.setNotes(campaign.getNotes());
            dbCampaign.setStartdate(campaign.getStartdate());
            dbCampaign.setTitle(campaign.getTitle());
            dbCampaign.setUrl(campaign.getUrl());
            campaignFacade.edit(dbCampaign);
        } else {
            Campaign c = new Campaign();
            c.setTitle("You are not the campaign manager. You have no rights to edit this campaign.");
            return c;
        }

        return dbCampaign;
    }

    /**
     * Deletes the campaign and some related objects if the user is the campaign
     * manager.<br />
     *
     * Address: DELETE [server]/resources/campaign/[campId]?sid=test_user
     *
     * @param sid
     * @param campaignId
     */
    @DELETE
    @Path("{id}")
    public void deleteCampaign(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer campaignId) {

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList == null) {
            return;
        }
        Userdata ud = udList.get(0);

        Campaign dbCampaign = campaignFacade.find(campaignId); //get requested campaign

        if (dbCampaign != null && dbCampaign.getIdUser().equals(ud)) {//campaign manager
            ud.removeCampaignManager(dbCampaign); //update user data
            campaignFacade.remove(dbCampaign);
        }
    }

    /**
     * Gives you all messages from a campaign. Only paticipants of this campaign
     * can see this object.<br />
     *
     * Address: GET [server]/resources/campaign/[campId]/message?sid=test_user
     *
     * @param sid valid session id
     * @param campaignId id of the campaign
     *
     * @return list of messages
     */
    @GET
    @Path("{id}/message")
    @Produces({"application/xml", "application/json"})
    public List<Message> getCampaignMessages(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer campaignId) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleMessageList();
        }

        List<Message> mList = new ArrayList<>();

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Message m = new Message();
            m.setTitle("The session id is not valid!");
            mList.add(m);
            return mList;
        }
        Userdata ud = udList.get(0);

        Campaign dbCampaign = campaignFacade.find(campaignId); //get requested campaign

        if (dbCampaign == null) {
            Message m = new Message();
            m.setTitle("It exists no campaign with this id!");
            mList.add(m);
            return mList;
        }

        if (dbCampaign.getIdUser().equals(ud) || dbCampaign.getUserdataList().contains(ud)) { //only paticipants of this campaign can see this object.
            return dbCampaign.getMessageList();
        } else {
            Message m = new Message();
            m.setTitle("The user have no rights to get information about the messages of this campaign.");
            mList.add(m);
            return mList;
        }


    }
}
