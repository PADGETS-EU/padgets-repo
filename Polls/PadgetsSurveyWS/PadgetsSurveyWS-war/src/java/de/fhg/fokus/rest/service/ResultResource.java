/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.rest.service;

import de.fhg.fokus.persistence.Survey;
import de.fhg.fokus.session.CreateSurveyBean;
import de.fhg.fokus.session.EditSurveyBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author hgo
 */
@Path("result")
@Stateless
public class ResultResource {
    @EJB
    private CreateSurveyBean createSurveyBean;
    @EJB
    private EditSurveyBean editSurveyBean;
    
    
    
    @Context
    private UriInfo context;

    /** Creates a new instance of ResultResource */
    public ResultResource() {
    }

    /**
     * Retrieves representation of an instance of de.fhg.fokus.rest.service.ResultResource
     * @return an instance of de.fhg.fokus.persistence.Survey
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public Survey get(@DefaultValue("nothing") @QueryParam("key") String key) {
       Survey s = editSurveyBean.getSurveyWithKey(key);
       if (s== null){
            return new Survey();
        }
       return s;
    }

    /**
     * PUT method for updating or creating an instance of ResultResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void put(Survey content) {
    }
}
