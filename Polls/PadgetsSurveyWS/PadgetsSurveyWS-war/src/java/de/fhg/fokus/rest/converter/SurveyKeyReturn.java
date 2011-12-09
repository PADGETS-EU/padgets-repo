/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.rest.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */

@XmlRootElement
public class SurveyKeyReturn {
       
    private String surveyKey;

    public SurveyKeyReturn() {
    }

    public SurveyKeyReturn(String surveyKey) {
        this.surveyKey = surveyKey;
    }
    
    @XmlElement
    public String getSurveyKey() {
        return surveyKey;
    }

    public void setSurveyKey(String surveyKey) {
        this.surveyKey = surveyKey;
    }
    
}
