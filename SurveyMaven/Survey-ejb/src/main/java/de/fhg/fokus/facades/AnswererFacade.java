/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;

import de.fhg.fokus.persistence.Answerer;
import java.util.List;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TheIgel69
 */
@Stateless
public class AnswererFacade extends AbstractFacade<Answerer> {

    @PersistenceContext(unitName = "PadgetsSurveyWS-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswererFacade() {
        super(Answerer.class);
    }

    @Override
    public void create(Answerer a) {
        a.setIdAnswerer(-1);
        getEntityManager().persist(a);
        getEntityManager().flush();
    }

    /**
     * Returns all voters from a survey with the given id
     * @param surveyId
     * @return List of Anwerers
     */
    public List<Answerer> getAllAnswererFromSurvey(Integer surveyId) {
        String query = "SELECT voter.* FROM question q"
                + " JOIN Answer a ON (q.idQuestion = a.Question_idQuestion)"
                + " JOIN Answerer_answered_Answer aaa ON (a.idAnswer = aaa.Answer_idAnswer)"
                + " JOIN Answerer voter ON (voter.idAnswerer = aaa.Answerer_idAnswerer)"
                + " WHERE Survey_idSurvey = '" + surveyId.toString() + "'"
                + " GROUP BY voter.idAnswerer;";
        return this.executeNativeQueryForSelect(query);
    }
}
