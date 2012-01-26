/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.persistence.Userdata;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

/**
 *
 * @author hgo
 */
@Stateless
@Path("userdate")
public class UserResource {

    public Userdata getUser() {
        //TODO
        return null;
    }

    public Userdata createUser() {
        //TODO
        return null;
    }

    public Userdata updateUser() {
        //TODO
        return null;
    }

    public void deleteUser() {
        //TODO
    }
}
