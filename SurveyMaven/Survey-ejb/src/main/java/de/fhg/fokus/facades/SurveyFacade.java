/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Survey;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TheIgel69
 */
@Stateless
public class SurveyFacade extends AbstractFacade<Survey> {

    @PersistenceContext(unitName = "PadgetsSurveyWS-ejbPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public SurveyFacade() {
        super(Survey.class);
    }
    
    @Override
    public void create(Survey s) {
        s.setIdSurvey(-1);
        getEntityManager().persist(s);
        getEntityManager().flush();
    }
}
