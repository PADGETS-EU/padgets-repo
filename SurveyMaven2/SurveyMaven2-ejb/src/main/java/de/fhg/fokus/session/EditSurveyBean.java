/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.session;

import de.fhg.fokus.facades.AnswerFacade;
import de.fhg.fokus.facades.AnswererAnsweredAnswerFacade;
import de.fhg.fokus.facades.AnswererFacade;
import de.fhg.fokus.facades.QuestionFacade;
import de.fhg.fokus.facades.SurveyFacade;
import de.fhg.fokus.persistence.Answer;
import de.fhg.fokus.persistence.Answerer;
import de.fhg.fokus.persistence.AnswererAnsweredAnswer;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.persistence.Survey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author hgo
 */
@Stateless
public class EditSurveyBean {

    @EJB
    private AnswererFacade answererFacade;
    @EJB
    private AnswererAnsweredAnswerFacade answererAnsweredAnswerFacade;
    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private SurveyFacade surveyFacade;

    public Survey getSurveyWithKey(String key) {
        try {
            List<Survey> list = surveyFacade.executeNamedQuery("Survey.findBySurveyKey", "surveyKey", key);
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Survey getSurveyWithId(Integer key) {
        try {
            Survey s = surveyFacade.find(key);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveSurveyProfile(Survey s) {
        try {
            surveyFacade.edit(s);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method saves all changes from every question and their answers.
     * @param survey
     * @return 
     */
    public boolean saveCompleteSurvey(Survey survey) {
        try {
            for (Question q : survey.getQuestionList()) {
                questionFacade.edit(q);
                for (Answer a : q.getAnswerList()) {
                    answerFacade.edit(a);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Stores the voting results in the databes
     * @param answerList identifiers of the answers
     * @param answerer information about the voter
     * @return 
     */
    public boolean saveVote(List<String> answerList, Answerer answerer) {
        try {
            if (answerList.isEmpty()) {
                return false;
            }

            if (answerer.getIdAnswerer() == null) { //only new users have no id
                answererFacade.create(answerer);
            }

            for (String id : answerList) {
                Answer a = answerFacade.find(Integer.decode(id));

                AnswererAnsweredAnswer aaa = new AnswererAnsweredAnswer();
                aaa.setAnswerDate(new Date());
                aaa.setAnsweridAnswer(a);
                aaa.setAnswereridAnswerer(answerer);
                answererAnsweredAnswerFacade.create(aaa);

                a.addAnswererAnsweredAnswer(aaa);
                answerer.addAnswererAnsweredAnswer(aaa);
                answerFacade.edit(a);
                answererFacade.edit(answerer);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Answerer getAnswererByExternalId(String id) {
        try {
            List<Answerer> list = answererFacade.executeNamedQuery("Answerer.findByExternalId", "externalId", id);
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
