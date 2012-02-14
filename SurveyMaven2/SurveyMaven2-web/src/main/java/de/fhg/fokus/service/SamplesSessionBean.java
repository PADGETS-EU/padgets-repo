/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.service;

import de.fhg.fokus.persistence.Answer;
import de.fhg.fokus.persistence.Answerer;
import de.fhg.fokus.persistence.AnswererAnsweredAnswer;
import de.fhg.fokus.persistence.Creator;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.persistence.Survey;
import de.fhg.fokus.converter.Results;
import de.fhg.fokus.converter.Voting;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import org.joda.time.DateMidnight;

/**
 *
 * @author hgo
 */
@Stateless
public class SamplesSessionBean {
    
    public Results createSampleResults() {
        Answer a = new Answer(666);
        
                AnswererAnsweredAnswer aaa1 = new AnswererAnsweredAnswer(12345);
        aaa1.setAnswerDate(new Date());
 aaa1.setAnsweridAnswer(a);
        
         AnswererAnsweredAnswer aaa2 = new AnswererAnsweredAnswer(6789);
        aaa2.setAnswerDate(new Date());
 aaa2.setAnsweridAnswer(a);
 
         List<AnswererAnsweredAnswer> aaaList = new ArrayList<AnswererAnsweredAnswer>();
        aaaList.add(aaa1);
        aaaList.add(aaa2);
        
        Answerer voter = new Answerer(101010);
        voter.setAge("20-24");
        voter.setExternalId("tesuser.googlemail.com");
        voter.setName("Max");
        voter.setRegion("Italy");
        voter.setGender(Boolean.TRUE);
        voter.setSocialNetwork("Facebook");     
        voter.setAnswererAnsweredAnswerList(aaaList);
        
                Answerer voter2 = new Answerer(101010);
        voter2.setAge("40-44");
        voter2.setExternalId("tesuser2.googlemail.com");
        voter2.setName("MFranc");
        voter2.setRegion("Italy");
        voter2.setGender(Boolean.TRUE);
        voter2.setSocialNetwork("Facebook");
        voter2.setAnswererAnsweredAnswerList(aaaList);

        Results r = new Results();
        
        r.getVoters().add(voter);
                r.getVoters().add(voter2);
        return r;
    }
    
    public Survey createSampleSurvey() {
        Survey s = new Survey(99999);
        s.setSurveyKey("sample_key");
        s.setName("Test Survey");
        s.setStartDate(new Date());
        
        DateMidnight endDate = new DateMidnight();
        endDate = endDate.plusWeeks(2);
        s.setEndDate(endDate.toDate());
        
        s.setQuestionList(new ArrayList());
        
        Question q1 = createQuestion(s, "norm");
        q1.setIdQuestion(12345);
        Question q2 = createQuestion(s, "acc");
        q2.setIdQuestion(23456);
        
        s.addQuestion(q1);
        s.addQuestion(q2);
        
        Creator c = new Creator(99999);
        c.setName("Test User");
        s.setCreatoridCreator(c);
        
        return s;
    }
    
    private Question createQuestion(Survey s, String type) {
        Question q = new Question();
        int orderNumber = s.getQuestionList().size() + 1;
        q.setOrderNumber(orderNumber);
        q.setLabel("What is the question?");
        q.setQuestionType(type);
        q.setAnswerLimit(1);
        q.setSurveyidSurvey(s);
        q.setAnswerList(new ArrayList());
        
        s.addQuestion(q);
        
        Answer a1 = new Answer();
        a1.setOrderNumber(1);
        a1.setLabel("Yes!");
        a1.setQuestionidQuestion(q);
        
        Answer a2 = new Answer();
        a2.setOrderNumber(2);
        a2.setLabel("No!");
        a2.setQuestionidQuestion(q);
        
        q.addAnswer(a1);
        q.addAnswer(a2);
        
        return q;
    }
    
    public Voting createSampleVoting(){
        Voting v = new Voting();
        
        Answerer voter = new Answerer();
        voter.setAge("20-24");
        voter.setExternalId("tesuser.googlemail.com");
        voter.setName("Max");
        voter.setRegion("Italy");
        voter.setGender(Boolean.TRUE);
        voter.setSocialNetwork("Facebook");
        
        v.setVoter(voter);
        
        List<String> votes = new ArrayList<String>();
        votes.add("1");
        votes.add("2");
        votes.add("3");
        votes.add("4");
        votes.add("5");
        
        v.setAnswers(votes);
        
        return v;
    }
}
