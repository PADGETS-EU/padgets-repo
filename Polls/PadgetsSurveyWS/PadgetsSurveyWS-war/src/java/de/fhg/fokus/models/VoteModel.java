/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.models;

import de.fhg.fokus.persistence.Answerer;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.persistence.Survey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TheIgel69
 */
public class VoteModel {
    private String key;
    private Survey survey;
    private Answerer answerer = new Answerer();
    //checked Answers
    private Map<Integer, AnswerModel> choosenAnswersMap = new HashMap<Integer, AnswerModel>();
    //map of all questions with id as map-key
    private Map<Integer,Question> questionMap = new HashMap<Integer, Question>();
    //the input-box for the voter
    private boolean showAnswererBox;
    private boolean voteNow =false;
    private boolean voteLater = false;
    private boolean votePrevious = false;
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Answerer getAnswerer() {
        return answerer;
    }

    public void setAnswerer(Answerer answerer) {
        this.answerer = answerer;
    }

    public boolean isShowAnswererBox() {
        return showAnswererBox;
    }

    public void setShowAnswererBox(boolean showAnswererBox) {
        this.showAnswererBox = showAnswererBox;
    }

    public AnswerModel getChoosenAnswersMap(Integer key) {
        if (!choosenAnswersMap.containsKey(key)){
            AnswerModel am = new AnswerModel();
            choosenAnswersMap.put(key, am);
        }
       return choosenAnswersMap.get(key); 
    } 
    
    public Question getQuestionMap(Integer key) {
       return questionMap.get(key); 
    } 
    
    public void addToQuestionMap(Question q){
        questionMap.put(q.getIdQuestion(), q);
    }
    
    public Map<Integer, AnswerModel> getCompleteAnswerMap(){
        return choosenAnswersMap;
    }

    public boolean isVoteLater() {
        return voteLater;
    }

    public void setVoteLater(boolean voteLater) {
        this.voteLater = voteLater;
    }

    public boolean isVoteNow() {
        return voteNow;
    }

    public void setVoteNow(boolean voteNow) {
        this.voteNow = voteNow;
    }

    public boolean isVotePrevious() {
        return votePrevious;
    }

    public void setVotePrevious(boolean votePrevious) {
        this.votePrevious = votePrevious;
    }
    
    
}
