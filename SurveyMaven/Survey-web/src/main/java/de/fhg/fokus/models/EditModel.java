/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.models;

import de.fhg.fokus.persistence.Survey;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hgo
 */
public class EditModel {
    private String key;
    private Survey survey;
    private String QuestionType;
    private Map<Integer,CollapseModel> collapseList = new HashMap<Integer,CollapseModel>();

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

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String QuestionType) {
        this.QuestionType = QuestionType;
    }


    public void addCollapse(Integer questId){
        collapseList.put(questId, new CollapseModel());
    }
    
    public CollapseModel getCollapseList(Integer questId){
        if(!collapseList.containsKey(questId)){
            addCollapse(questId);
        }
        return collapseList.get(questId);
    }
   
    
}
