/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.facades.AnswerFacade;
import de.fhg.fokus.facades.AnswererFacade;
import de.fhg.fokus.persistence.Answerer;
import de.fhg.fokus.persistence.Survey;
import de.fhg.fokus.converter.Results;
import de.fhg.fokus.session.CreateSurveyBean;
import de.fhg.fokus.session.EditSurveyBean;
import java.util.List;
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
    private AnswererFacade answererFacade;

    @EJB
    private SamplesSessionBean samplesSessionBean;
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
     * Get all results for a survey with the given key. Is the key wrong a sample result will be returned.
     * @return results
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public Results get(@DefaultValue("nothing") @QueryParam("key") String key) {

        Survey s = editSurveyBean.getSurveyWithKey(key);
        if (s == null) {
            return samplesSessionBean.createSampleResults();
        }
        List<Answerer> allAnswerersFromSurvey = answererFacade.getAllAnswererFromSurvey(s.getIdSurvey());
        
        Results res = new Results();
        res.setVoters(allAnswerersFromSurvey);
        return res;
    }
}
