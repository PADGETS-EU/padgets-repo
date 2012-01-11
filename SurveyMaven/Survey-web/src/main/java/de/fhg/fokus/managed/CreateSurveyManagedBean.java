/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.managed;

import de.fhg.fokus.session.CreateSurveyBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author hgo
 */
@ManagedBean
@RequestScoped
public class CreateSurveyManagedBean {
    
    @EJB
    private CreateSurveyBean csBean;
    private String key; 
    
    
    /** Creates a new instance of createSurveyManagedBean */
    public CreateSurveyManagedBean() {
    }
    
    
    public void createSurvey(){
        key=csBean.createSurveyKey();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    
    
}
