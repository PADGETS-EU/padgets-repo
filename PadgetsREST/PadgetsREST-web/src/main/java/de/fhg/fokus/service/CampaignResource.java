/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import com.sun.jersey.api.core.ResourceContext;
import de.fhg.fokus.converter.Test;
import de.fhg.fokus.facades.CampaignFacade;
import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.Campaign;
import de.fhg.fokus.persistence.Userdata;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBElement;

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

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Campaign> getCampaign(@DefaultValue("test_user") @QueryParam("sid") String sid) {
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

    
//    @POST
//    @Consumes({"application/xml", "application/json"})
//    @Produces({"application/xml", "application/json"})
//    public Campaign vote(Campaign c, @QueryParam("sid") String sid) {
//
////        if (sid.equals("test_user")) {//return test data                
////            return sampleSessionBean.makeSampleCampaign();
////        }
//        //TODO: user id ermitteln
//        Userdata ud = userdataFacade.find(1);
//        c.setIdUser(ud);
//        campaignFacade.create(c);
//
//        ud.addCampaignManager(c);
//
//        return c;
//    }
}
