/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.AnswererAnsweredAnswer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TheIgel69
 */
@Stateless
public class AnswererAnsweredAnswerFacade extends AbstractFacade<AnswererAnsweredAnswer> {
    @PersistenceContext(unitName = "PadgetsSurveyWS-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswererAnsweredAnswerFacade() {
        super(AnswererAnsweredAnswer.class);
    }
    
            @Override
    public void create(AnswererAnsweredAnswer a) {
        a.setIdAnswereransweredAnswer(-1);
        getEntityManager().persist(a);
        getEntityManager().flush();
    }
    
}
