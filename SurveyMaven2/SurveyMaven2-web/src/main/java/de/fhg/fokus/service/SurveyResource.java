/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.converter.Success;
import de.fhg.fokus.converter.SurveyKeyReturn;
import de.fhg.fokus.converter.Voting;
import de.fhg.fokus.facades.AnswererAnsweredAnswerFacade;
import de.fhg.fokus.facades.SurveyFacade;
import de.fhg.fokus.persistence.Survey;
import de.fhg.fokus.session.CreateSurveyBean;
import de.fhg.fokus.session.EditSurveyBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author TheIgel69
 */
@Path("survey")
@Stateless
public class SurveyResource {

    @EJB
    private AnswererAnsweredAnswerFacade answererAnsweredAnswerFacade;
    @EJB
    private SurveyFacade surveyFacade;
    @EJB
    private SamplesSessionBean samplesSessionBean;
    @EJB
    private EditSurveyBean editSurveyBean;
    @EJB
    private CreateSurveyBean createSurveyBean;
    @Context
    private UriInfo context;

    /** Creates a new instance of CreateSurveyResource */
    public SurveyResource() {
    }

    /**
     * Create a new survey and returns the key for this survey.
     * @return survey key
     */
    @GET
    @Path("create")
    @Produces({"application/xml", "application/json"})
    public SurveyKeyReturn createSurvey() {
        String key = createSurveyBean.createSurveyKey();
        SurveyKeyReturn skr = new SurveyKeyReturn(key);
        return skr;
    }

    /**
     * Returns the complete survey object with all questions, answers and the creator. If the key is wrong, the system returns a sample survey.
     * @param key
     * @return Survey with all questions, answers and creator
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public Survey getSurvey(@DefaultValue("test") @QueryParam("key") String key) {
        
                if (key.equals("test")){
             return samplesSessionBean.createSampleSurvey();
        }
        
        Survey survey = editSurveyBean.getSurveyWithKey(key);
        if (survey == null) { //key not exist
            Survey s = new Survey();
            s.setName("The key is not valid.");
            return s;
        } else {
            return survey;
        }
    }

    /**
     * This method returns and example Object for a vote.
     * @return 
     */
    @GET
    @Path("vote")
    @Produces({"application/xml", "application/json"})
    public Voting getSampleVote() {
        return samplesSessionBean.createSampleVoting();
    }

    /**
     * With this method you can vote!
     * @param v
     * @return 
     */
    @POST
    @Path("vote")
    @Consumes({"application/xml", "application/json"})
    public Success vote(Voting v) {

        if (editSurveyBean.saveVote(v.getAnswers(), v.getVoter())) {
            return new Success("true", "Vote Results are saved.");
        } else {
            return new Success("false", "There was a failure!");
        }
    }
}
