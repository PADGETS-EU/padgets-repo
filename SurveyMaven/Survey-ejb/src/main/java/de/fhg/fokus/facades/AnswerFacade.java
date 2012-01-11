/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Answer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TheIgel69
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> {
    @PersistenceContext(unitName = "PadgetsSurveyWS-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }
    
    @Override
    public void create(Answer a) {
        a.setIdAnswer(-1);
        getEntityManager().persist(a);
        getEntityManager().flush();
    }
}
