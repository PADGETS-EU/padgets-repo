/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.managed;

import de.fhg.fokus.models.ResultModel;
import de.fhg.fokus.persistence.Answer;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.session.EditSurveyBean;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hgo
 */
@ManagedBean
@RequestScoped
public class ResultManagedBean {

    @EJB
    private EditSurveyBean eManager;
    private ResultModel rm = new ResultModel();

    /** Creates a new instance of ResultManagedBean */
    public ResultManagedBean() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String key = request.getParameter("key");
        System.out.println("");
        rm.setSurvey(eManager.getSurveyWithKey(key));

        int arrayLength = 1;
        Collections.sort(rm.getSurvey().getQuestionList());
        for (Question q : rm.getSurvey().getQuestionList()) {
            rm.getXaxisLabels().add(q.getOrderNumber().toString());
            if (q.getAnswerList().size() > arrayLength) {
                arrayLength = q.getAnswerList().size();
            }
        }
        for (Question q : rm.getSurvey().getQuestionList()) {
            
            double[] list = new double[arrayLength];
            for (int i = 0; i<arrayLength; i++){
                list[i] = 0;
            }
            
            int i = 0;
            for (Answer a : q.getAnswerList()){
                list[i]=a.getAnswererAnsweredAnswerList().size();
                i++;
            }
            rm.getChartData().add(list);
        }
    }

    public ResultModel getRm() {
        return rm;
    }

    public void setRm(ResultModel rm) {
        this.rm = rm;
    }
}
