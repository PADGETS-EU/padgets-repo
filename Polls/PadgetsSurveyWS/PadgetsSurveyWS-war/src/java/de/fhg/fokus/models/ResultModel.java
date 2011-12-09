/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.models;

import de.fhg.fokus.persistence.Survey;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hgo
 */
public class ResultModel {
       private String key;
       
       private Survey survey;
       private List<String> xaxisLabels = new ArrayList<String>();
       private List<double[]> chartData = new ArrayList<double[]>();

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

    public List<String> getXaxisLabels() {
        return xaxisLabels;
    }

    public void setXaxisLabels(List<String> xaxisLabels) {
        this.xaxisLabels = xaxisLabels;
    }

    public List<double[]> getChartData() {
        return chartData;
    }

    public void setChartData(List<double[]> chartData) {
        this.chartData = chartData;
    }
       
       
    
}
