/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.*;
import de.fhg.fokus.misc.Counter;
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
@Path("message")
public class MessageResource {

    @EJB
    private UserRoleFacade userRoleFacade;
    @EJB
    private CommentFacade commentFacade;
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

        if (userRoleFacade.isCampaignPaticipant(ud, relatedCampaign)) { //only paticipants of this campaign can see this object.
            return dbMessage;
        } else {
            Message m = new Message();
            m.setTitle("This user have no rights to get information about the messages of this campaign.");
            return m;
        }

    }

    /**
     *
     * @param sid
     * @param messageId
     * @param from
     * @return
     */
    @GET
    @Path("{id}/comment")
    @Produces({"application/xml", "application/json"})
    public List<Comment> getComments(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer messageId, @DefaultValue("0") @QueryParam("from") Integer from) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleCommentList();
        }

        List<Comment> mList = new ArrayList<>();
        Comment m = new Comment();
        mList.add(m);

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            m.setContent("The session id is not valid!");
            return mList;
        }
        Userdata ud = udList.get(0);

        Message dbMessage = messageFacade.find(messageId);
        messageFacade.refresh(dbMessage);

        if (dbMessage == null) {
            m.setContent("It exists no Message with this id !");
            return mList;
        }

        Campaign relatedCampaign = dbMessage.getIdCampaign();

        if (userRoleFacade.isCampaignPaticipant(ud, relatedCampaign)) { //only paticipants of this campaign can see this object.
            return commentFacade.getComments(dbMessage.getIdMessage(), from);
        } else {
            m.setContent("This user have no rights to get information about the comments of this campaign.");
            return mList;
        }

    }

    /**
     * DELETES the message with the given id. Only paticipants of this campaign
     * can delete this object.<br />
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

        if (userRoleFacade.isCampaignPaticipant(ud, relatedCampaign)) { //only paticipants of this campaign can see this object.
            //update related objects
            ud.removeMessage(dbMessage);
            relatedCampaign.removeMessage(dbMessage);

            messageFacade.remove(dbMessage);
        } 
    }

    /**
     * How many messages has a campaign?<br /> Failure codes: <br /> -1 - "It
     * exists no message with this id!"<br /> -2 - "The session id is not
     * valid!"<br /> -3 - "You are not the campaign manager. You have no rights
     * to edit this campaign."<br />
     *
     * @param sid session id
     * @param messageId id of message
     * @return
     */
    @GET
    @Path("{id}/commentcount")
    @Produces({"application/xml", "application/json"})
    public Counter getCommentCount(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer messageId) {

        if (sid.equals("test_user")) {//return test data                
            return sampleSessionBean.makeSampleCounter();
        }

        Message m = messageFacade.find(messageId); //get requested campaign

        Counter co = new Counter();
        if (m == null) {
            co.setCount(-1);
            return co;
        }

        //check sid
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            co.setCount(-2);
            return co;
        }
        Userdata ud = udList.get(0);

        Campaign relatedCampaign = m.getIdCampaign();

        if (userRoleFacade.isCampaignPaticipant(ud, relatedCampaign)) { //only paticipants of this campaign can see this object.
            Long counter = commentFacade.countComments(messageId);
            co.setCount(counter);
            return co;
        } else {
            co.setCount(-3);
            return co;
        }

    }
}
