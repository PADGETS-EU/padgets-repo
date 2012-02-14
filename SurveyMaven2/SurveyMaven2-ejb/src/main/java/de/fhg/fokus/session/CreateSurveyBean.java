/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.session;

import de.fhg.fokus.facades.AnswerFacade;
import de.fhg.fokus.facades.QuestionFacade;
import de.fhg.fokus.facades.SurveyFacade;
import de.fhg.fokus.persistence.Answer;
import de.fhg.fokus.persistence.Creator;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.persistence.Survey;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.joda.time.DateMidnight;
import org.joda.time.ReadablePeriod;

/**
 *
 * @author hgo
 */
@Stateless
public class CreateSurveyBean {

    @EJB
    private AnswerFacade answerFacade;
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private SurveyFacade surveyFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * Creates a new Survey and returns the key which identify this survey.
     * @return String key
     */
    public String createSurveyKey() {
        String key = makeKey();
        //create new Survey object
        Survey s = new Survey();
        s.setSurveyKey(key);
        s.setName("Survey without name");        
        s.setStartDate(new Date());
        
        DateMidnight endDate = new DateMidnight();  
        endDate = endDate.plusWeeks(2);        
        s.setEndDate(endDate.toDate());
        surveyFacade.create(s);
        
        return key;
    }

    private String makeKey() {
        String key = "PS";
        key += System.currentTimeMillis();
        String hashword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(key.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hashword = hash.toString(16);

        } catch (NoSuchAlgorithmException nsae) {
        }
        return hashword;
    }
  
    
        public Question createQuestion(Survey s, String type){
                try {
            Question q = new Question();
            int orderNumber = s.getQuestionList().size() + 1;
            q.setOrderNumber(orderNumber);
            q.setLabel("What is the question?");
            q.setQuestionType(type);
            q.setAnswerLimit(1);
            q.setSurveyidSurvey(s);
            questionFacade.create(q);

            s.addQuestion(q);
            surveyFacade.edit(s);
            
            Answer a1 = new Answer();
            a1.setOrderNumber(1);
            a1.setLabel("Yes!");
            a1.setQuestionidQuestion(q);
            answerFacade.create(a1);
            
            Answer a2 = new Answer();
            a2.setOrderNumber(2);
            a2.setLabel("No!");
            a2.setQuestionidQuestion(q);
            answerFacade.create(a2);
            
            q.addAnswer(a1);
            q.addAnswer(a2);
            
            return q;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createAnswer(Question q) {
        try {
            Answer a = new Answer();
            System.out.println("");
            int orderNumber = q.getAnswerList().size() + 1;
            a.setOrderNumber(orderNumber);
            a.setLabel("The answer is a mindbogingly 42!");
            a.setQuestionidQuestion(q);
            answerFacade.create(a);
            q.addAnswer(a);
            questionFacade.edit(q);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeAnswer(Answer a) {
        try {
            Question q = a.getQuestionidQuestion();
            q.removeAnswer(a);
            questionFacade.edit(q);
            
            answerFacade.remove(a);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
        public boolean removeQuestion(Question q) {
        try {
            Survey survey = q.getSurveyidSurvey();
            survey.removeQuestion(q);
            surveyFacade.edit(survey);
            
            questionFacade.remove(q);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
}
