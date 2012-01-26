/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.CampaignFacade;
import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Userdata;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 *
 * @author hgo
 */
@Stateless
@Path("campaign")
public class CampaignResource {

    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private SampleSessionBean sampleSessionBean;
    @EJB
    private CampaignFacade campaignFacade;

    /**
     * Returns all campaigns for the given user
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
        //TODO: user id ermitteln
        Userdata ud = userdataFacade.find(1);
        if (ud.getUserRole().equals("manager")) {
            return ud.getCampaignListManager();
        } else { //it's a helper
            return ud.getCampaignListHelpers();
        }
    }

    /**
     * Create new campaign and returns the persisted object (with valid id).
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
        //TODO: user id ermitteln
        Userdata ud = userdataFacade.find(1);
        campaign.setIdUser(ud);
        campaignFacade.create(campaign);

        ud.addCampaignManager(campaign);

        //TODO check if campaign id is valid
        return campaign;
    }

    /**
     * Change the values of the campaign object of the database with the given one. It don't change foreign keys (relations)
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
        //TODO: user id ermitteln
        Userdata ud = userdataFacade.find(1);
        Campaign dbCampaign = campaignFacade.find(campaignId); //get requested campaign
        //TODO kann man ein entity updaten welches alle relationen auf null hat?
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
        }
        return dbCampaign;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteCampaign( @DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer campaignId){
        //TODO
    }
    
      //TODO create, update delete topics
}
