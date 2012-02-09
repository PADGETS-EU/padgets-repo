/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.LocationFacade;
import de.fhg.fokus.facades.UserdataFacade;
import de.fhg.fokus.persistence.Location;
import de.fhg.fokus.persistence.Userdata;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 *
 * @author Hannes Gorges
 */
@Stateless
@Path("location")
public class LocationResource {

    @EJB
    private UserdataFacade userdataFacade;
    @EJB
    private LocationFacade locationFacade;

    /**
     * Gives all locations, which are stored in the database.<br />
     *
     * Address: POST [server]/resources/location
     *
     * @return List of Locations
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Location> getLocations() {
        return locationFacade.findAll();
    }

    /**
     * Gives the location, which belongs to the given id.<br />
     *
     * Address: POST [server]/resources/location[location_id]
     * 
     * @param locationId id of the location
     * @return Location with the given id
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Location getLocation(@PathParam("id") Integer locationId) {
        return locationFacade.find(locationId);
    }

    /**
     * Create a new location object. It only stores locations which have minimum
     * 3 letters.<br />
     *
     * Address: POST
     * [server]/resources/location?sid=test_user&location=[location_name]
     *
     * @param sid valid session id
     * @param location name of location
     * @return new location object
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public Location createLocation(@DefaultValue("test_user") @QueryParam("sid") String sid, @DefaultValue("no") @QueryParam("location") String location) {

        Location l = new Location();

        List<Userdata> udList = userdataFacade.executeNamedQuery("Userdata.findByUserSIGN", "userSIGN", sid);
        if (udList.isEmpty()) {
            l.setName("sid is not valid");
            return l;
        }

        if (location.length() > 2) {
            l.setName(location);
            locationFacade.create(l);
        } else {
            l.setName("The name of the location contains fewer then 2 letters. It seems not a valid location.");
        }
        if (location.equals("no")) { // no location as Query Param
            l.setName("You need a query param named location");
        }

        return l;
    }
}
