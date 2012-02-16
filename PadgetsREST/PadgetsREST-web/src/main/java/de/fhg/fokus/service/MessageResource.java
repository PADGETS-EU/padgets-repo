/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.CampaignFacade;
import de.fhg.fokus.facades.MessageFacade;
import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.*;
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
@Path("message")
public class MessageResource {

    @EJB
    private CampaignFacade campaignFacade;
    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private SampleSessionBean sampleSessionBean;
    @EJB
    private MessageFacade messageFacade;


    /**
     * Returns the message with the given id. If sid = test_user, it returns a
     * sample message. Only paticipants of this campaign can see this object.<br
     * />
     *
     * Address: GET [server]/resources/message/[messageId]?sid=test_user
     *
     * @param sid session id
     * @param messageId id of message
     * @return searched message
     */
    @GET
    @Path("{id}")
    public Message getMessage(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer messageId) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleMessage();
        }

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Message m = new Message();
            m.setTitle("The session id is not valid!");
            return m;
        }
        Userdata ud = udList.get(0);

        Message dbMessage = messageFacade.find(messageId);
        if (dbMessage == null) {
            Message m = new Message();
            m.setTitle("It exists no Message with this id !");
            return m;
        }
        
        Campaign relatedCampaign = dbMessage.getIdCampaign();
      
        if (relatedCampaign.getIdUser().equals(ud) || relatedCampaign.getUserdataList().contains(ud)) { //only paticipants of this campaign can see this object.
            return dbMessage;
        } else {
            Message m = new Message();
            m.setTitle("This user have no rights to get information about the messages of this campaign.");      
            return m;
        }

    }

    /**
     * DELETES the message with the given id. Only paticipants of this campaign can delete this object.<br
     * />
     *
     * Address: DELETE [server]/resources/message/[messageId]?sid=test_user
     *
     * @param sid session id
     * @param messageId id of message
     */
        @DELETE
    @Path("{id}")
    public void deleteMessage(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer messageId) {
            

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            return;
        }
        Userdata ud = udList.get(0);

        Message dbMessage = messageFacade.find(messageId);
        if (dbMessage == null) {
            return;
        }
        
        Campaign relatedCampaign = dbMessage.getIdCampaign();
      
        if (relatedCampaign.getIdUser().equals(ud) || relatedCampaign.getUserdataList().contains(ud)) { //only paticipants of this campaign can see this object.
            //update related objects
            ud.removeMessage(dbMessage);
            relatedCampaign.removeMessage(dbMessage);
            
            messageFacade.remove(dbMessage);
        }
    }
}
