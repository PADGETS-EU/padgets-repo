/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.Userdata;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 *
 * @author hgo
 */
@Stateless
@Path("user")
public class UserResource {
    
    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private SampleSessionBean sampleSessionBean;
    
    /**
     * Returns the user object for the given session id.<br />
     *
     * Address: GET [server]/resources/user?sid=test_user
     * 
     * @param sid session id
     * @return 
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public Userdata getUser(@DefaultValue("test_user") @QueryParam("sid") String sid) {
        if (sid.equals("test_user")) {//return test data
            return sampleSessionBean.makeSampleUser();
        }
        
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList == null) {
            return null;
        }
        return udList.get(0);
    }
    
    /**
     * Updates the user object.<br />
     *
     * Address: PUT [server]/resources/user?sid=test_user
     * 
     * @param user user object with new data
     * @param sid session id
     * @return 
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Userdata updateUser(Userdata user, @DefaultValue("test_user") @QueryParam("sid") String sid) {
        if (sid.equals("test_user")) {//return test data
            return sampleSessionBean.makeSampleUser();
        }
        
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList == null) {
            return null;
        }
        
        Userdata ud = udList.get(0);
   
        ud.setAge(new Date());
        ud.setEmail(user.getEmail());
        ud.setFirstname(user.getFirstname());
        ud.setGender(user.getGender());
        ud.setOrganization(user.getOrganization());
        ud.setSurname(user.getSurname());
        ud.setUserRole(user.getUserRole());
        ud.setMiddlename(user.getMiddlename());
        ud.setUsername(user.getUsername());
        ud.setViewLanguage(user.getViewLanguage());
        
        userdataFacade.edit(ud);
        
        return ud;
    }
    
    /**
     * Deletes the user with the given id if the session id is from the same user.<br />
     *
     * Address: DELETE [server]/resources/user/[userId]?sid=test_user
     * 
     * @param sid
     * @param userId 
     */
    @DELETE
    @Path("{id}")
    public void deleteUser(@DefaultValue("test_user") @QueryParam("sid") String sid, @PathParam("id") Integer userId) {
        if (sid.equals("test_user")) {//return test data
            return;
        }
       
        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList != null && udList.get(0).getIdUserData() == userId) {
            userdataFacade.remove(udList.get(0));
        }        
    }
}
