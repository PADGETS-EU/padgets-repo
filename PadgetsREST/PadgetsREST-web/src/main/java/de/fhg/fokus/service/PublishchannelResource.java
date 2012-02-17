/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.Publishchannel;
import de.fhg.fokus.persistence.Userdata;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
@Path("publishchannel")
public class PublishchannelResource {
    
    @EJB
    private UserdataFacade userdataFacade;

    @EJB
    private SampleSessionBean sampleSessionBean;
    

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Publishchannel> getPublishChannel(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer userId) {
        if (sid.equals("test_user")) {//return test data
            return sampleSessionBean.makeSamplePublishChannelList();
        }
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Publishchannel pc = new Publishchannel();
            pc.setName("The session id is not valid!");
            List<Publishchannel> pcList = new ArrayList<>();
            pcList.add(pc);
            return pcList;
        }
        Userdata ud = udList.get(0);
        userdataFacade.refresh(ud);
        
        return ud.getPublishchannelList();

    }
}
