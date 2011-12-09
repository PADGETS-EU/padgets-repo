/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hgo
 * 
 * This container stores the answers from the vote page. Radio Buttons need a String for storing and checkboxes a List.
 * We store both in a list because of a easier way to process the given answers.
 */
public class AnswerModel {
    private List<String> choosenAnswers = new ArrayList<String>();
    

    public List<String> getChoosenAnswers() {
        return choosenAnswers;
    }

    public void setChoosenAnswers(List<String> choosenAnswers) {
        this.choosenAnswers = choosenAnswers;
    }    
    
    public String getChoosenRadioAnswer(){
        if (choosenAnswers.isEmpty()){
            return null;
        } else {
            return choosenAnswers.get(0);
        }
    }
    
    public void setChoosenRadioAnswer(String s){
        choosenAnswers.add(s);
    }
    
}

