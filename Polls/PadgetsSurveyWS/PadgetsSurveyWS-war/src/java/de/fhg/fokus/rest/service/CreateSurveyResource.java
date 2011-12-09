/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.rest.service;

import de.fhg.fokus.rest.converter.SurveyKeyReturn;
import de.fhg.fokus.session.CreateSurveyBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author hgo
 */
@Path("createSurvey")
@Stateless
public class CreateSurveyResource {
    
    @EJB
    private CreateSurveyBean createSurveyBean;

    @Context
    private UriInfo context;

    /** Creates a new instance of CreateSurveyResource */
    public CreateSurveyResource() {
    }

    /**
     * Retrieves representation of an instance of de.fhg.fokus.rest.service.CreateSurveyResource
     * @return an instance of de.fhg.fokus.rest.converter.SurveyKeyReturn
     */
    @GET
    @Produces({"application/json"})
    public SurveyKeyReturn getJson() {
        String key = createSurveyBean.createSurveyKey();
        SurveyKeyReturn skr = new SurveyKeyReturn(key);
        return skr;
    }

    /**
     * PUT method for updating or creating an instance of CreateSurveyResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void putJson(SurveyKeyReturn content) {
    }
}
