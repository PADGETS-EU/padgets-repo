/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.managed;

import com.icesoft.faces.component.panelpositioned.PanelPositionedEvent;
import de.fhg.fokus.models.EditModel;
import de.fhg.fokus.persistence.Answer;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.session.CreateSurveyBean;
import de.fhg.fokus.session.EditSurveyBean;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hgo
 */
@ManagedBean
@CustomScoped(value = "#{window}")
public class EditSurveyManagedBean {

    @EJB
    private CreateSurveyBean cManager;
    @EJB
    private EditSurveyBean eManager;
    private EditModel em = new EditModel();

    /** Creates a new instance of editSurveyManagedBean */
    public EditSurveyManagedBean() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String key = request.getParameter("key");
        System.out.println("");

        em.setSurvey(eManager.getSurveyWithKey(key));
        System.out.println("");
    }

    public String reset() {
        em.setSurvey(eManager.getSurveyWithId(em.getSurvey().getIdSurvey()));

        if (em.getSurvey() != null) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String save() {
        System.out.println("");
        boolean success = eManager.saveSurveyProfile(em.getSurvey());

        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String addQuestion() {
        Question q = null;
        if (em.getQuestionType().equals("norm")) {
            q = cManager.createQuestion(em.getSurvey(),"norm");
        } else {
            q = cManager.createQuestion(em.getSurvey(),"acc");
        }
        
        if (q != null) {
            em.addCollapse(q.getIdQuestion());
            Collections.sort(q.getAnswerList());
            return "success";
        } else {
            return "failure";
        }
    }

    public String addAnswer(Question q) {
        boolean success = cManager.createAnswer(q);

        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String removeAnswer(Question q, Answer a) {
        boolean success = cManager.removeAnswer(a);
        q.removeAnswer(a);
        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String removeQuestion(Question q) {
        boolean success = cManager.removeQuestion(q);
        em.getSurvey().removeQuestion(q);
        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    public String saveAllQuestions() {
        boolean success = eManager.saveCompleteSurvey(em.getSurvey());

        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * Method is called at position change in the question list.
     * @param evt 
     */
    public void changedQuestionOrder(PanelPositionedEvent evt) {
        for (int i = 0; i < em.getSurvey().getQuestionList().size(); i++) {
            em.getSurvey().getQuestionList().get(i).setOrderNumber(i + 1);
        }
//        if (evt.getOldIndex() >= 0) {
//            em.getSurvey().getQuestionList().get(
//                    evt.getIndex())).getEffect().setFired(false);
//        }
    }

    public EditModel getEm() {
        return em;
    }

    public void setEm(EditModel em) {
        this.em = em;
    }
}
