/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.session;

import de.fhg.fokus.facades.SurveyFacade;
import de.fhg.fokus.persistence.Survey;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author hgo
 */
@Stateful
public class VoteSessionBean {

    @EJB
    private SurveyFacade surveyFacade;
    private Map<String, Survey> surveyMap = new HashMap<String, Survey>();

    public Survey getSurveyWithKey(String key) {
        try {
            if (surveyMap.containsKey(key)) {
                return surveyMap.get(key);
            } else {
                Survey s = surveyFacade.executeNamedQuery("Survey.findBySurveyKey", "surveyKey", key).get(0);
                
                surveyMap.put(key, s);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
