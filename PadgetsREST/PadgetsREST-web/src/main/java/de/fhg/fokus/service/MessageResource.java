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
     * Method creates a new message which belongs to the given user (identifies
     * by sid). Every message needs a campaign object, so the campaign id is
     * necessary. This message is only available in the padgets database and not
     * published to any Networks. The method returns a sample message, if the
     * sid is "test_user".<br />
     *
     * Address: POST
     * [server]/resources/message?sid=test_user&campaignId=[campId]
     *
     * @param message Message object with campaign object inside
     * @param sid valid session id
     * @param campaignId Id of the campaign, which belongs to the message
     * @return newly created Message object
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Message createMessage(Message message, @DefaultValue("test_user") @QueryParam("sid") String sid, @DefaultValue("-1") @QueryParam("campaignId") Integer campaignId) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleMessage();
        }

        if (campaignId == -1) {
            return null;
        }

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            Message m = new Message();
            m.setTitle("The session id is not valid!");
            return m;
        }
        Userdata ud = udList.get(0);

        Campaign c = campaignFacade.find(campaignId);
        if (c == null) {
            Message m = new Message();
            m.setTitle("It exists no campaign with this id!");
            return m;
        }

        message.setIdUserData(ud);
        message.setIdCampaign(c);
        message.setCreateTime(new Date()); //now

        messageFacade.create(message);

        //update user entity and campaign
        ud.addMessage(message);
        c.addMessage(message);

        //TODO check if campaign id is valid
        return message;
    }

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
