/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.QuestionMatrix;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TheIgel69
 */
@Stateless
public class QuestionMatrixFacade extends AbstractFacade<QuestionMatrix> {
    @PersistenceContext(unitName = "PadgetsSurveyWS-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionMatrixFacade() {
        super(QuestionMatrix.class);
    }
    
}
