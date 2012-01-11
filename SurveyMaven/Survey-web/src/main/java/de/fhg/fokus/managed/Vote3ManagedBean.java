/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.managed;

import de.fhg.fokus.models.AnswerModel;
import de.fhg.fokus.models.VoteModel;
import de.fhg.fokus.persistence.Answerer;
import de.fhg.fokus.persistence.Question;
import de.fhg.fokus.session.EditSurveyBean;
import de.fhg.fokus.session.VoteSessionBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;

/**
 *
 * @author hgo
 */
@ManagedBean
@CustomScoped(value = "#{window}")
public class Vote3ManagedBean {

    @EJB
    private EditSurveyBean eManager;
    private VoteModel vm = new VoteModel();

    /** Creates a new instance of Vote3ManagedBean */
    public Vote3ManagedBean() {
    }

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();

        //
        if (parameterMap.containsKey("key")) {
            String key = parameterMap.get("key")[0];
            vm.setSurvey(eManager.getSurveyWithKey(key));
        }
        //check if vote is running
        DateTime start = new DateTime(vm.getSurvey().getStartDate());
        DateTime end = new DateTime(vm.getSurvey().getEndDate());

        if (start.isAfterNow()) {//vote doesn't start already
            initPreVote();
        }
        if (end.isBeforeNow()) {//vote already closed
            initAfterVote();
        }
        if (start.isBeforeNow() && end.isAfterNow()) {//we can vote
            initVote(parameterMap);
        }
    }

    /**
     * Saves the votes from the user and forwards to the success page or failure page.
     * @return next page
     */
    public String saveVote() {
        List<String> answerList = new ArrayList<String>();
        Map<Integer, AnswerModel> map = vm.getCompleteAnswerMap();
        Collection<AnswerModel> values = map.values();
        for (AnswerModel am : values) {
            answerList.addAll(am.getChoosenAnswers());
        }
        boolean success = eManager.saveVote(answerList, vm.getAnswerer());

        if (success) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void validateAnswerLimit(FacesContext fc, UIComponent component, Object value) {

        Integer answerLimit = (Integer) component.getAttributes().get("answerLimit");


        List<String> selectedItemscheckbox = (List<String>) value;

        if (selectedItemscheckbox.size() > answerLimit.intValue()) {
            throw new ValidatorException(new FacesMessage("Max " + answerLimit.intValue() + " answers allowed."));
        }
    }

    private void initVote(Map<String, String[]> parameterMap) {

        vm.setVoteNow(true);

        //vm.setAnswerer(new Answerer());
        vm.setShowAnswererBox(false);

        if (parameterMap.containsKey("showAnswererBox")) {
            String showAnswererBox = parameterMap.get("showAnswererBox")[0];
            if (showAnswererBox.equals("true") || showAnswererBox.equals("TRUE")) {
                vm.setShowAnswererBox(true);
            }
        }

        if (parameterMap.containsKey("externalId")) {
            String externalId = parameterMap.get("externalId")[0];
            Answerer answ = eManager.getAnswererByExternalId(externalId);
            if (answ != null) {
                vm.setAnswerer(answ);
            }
        }

        if (parameterMap.containsKey("name")) {
            String name = parameterMap.get("name")[0];
            vm.getAnswerer().setName(name);
        }

        if (parameterMap.containsKey("socialNetwork")) {
            String socialNetwork = parameterMap.get("socialNetwork")[0];
            vm.getAnswerer().setSocialNetwork(socialNetwork);
        }

        if (parameterMap.containsKey("sex")) {
            String sex = parameterMap.get("sex")[0];
            if (sex.equals("m") || sex.equals("M") || sex.equals("man") || sex.equals("true") || sex.equals("TRUE")) {
                vm.getAnswerer().setSex(Boolean.TRUE);
            } else {
                vm.getAnswerer().setSex(Boolean.FALSE);
            }
        }

        if (parameterMap.containsKey("age")) {
            String age = parameterMap.get("age")[0];
            vm.getAnswerer().setAge(age);
        }

        if (parameterMap.containsKey("region")) {
            String region = parameterMap.get("region")[0];
            vm.getAnswerer().setRegion(region);
        }

        //sort Questions
        if (vm.getSurvey().getRandomQuestionOrder()) {
            Collections.shuffle(vm.getSurvey().getQuestionList());
        } else {
            Collections.sort(vm.getSurvey().getQuestionList());
        }

        //sort Answers and put questions to questionMap
        for (Question q : vm.getSurvey().getQuestionList()) {

            vm.addToQuestionMap(q);

            if (q.getRandomAnswerOrder()) {
                Collections.shuffle(q.getAnswerList());
            } else {
                Collections.sort(q.getAnswerList());
            }
        }

    }

    private void initPreVote() {
        vm.setVoteLater(true);
    }

    private void initAfterVote() {
        vm.setVotePrevious(true);
    }

    public VoteModel getVm() {
        return vm;
    }

    public void setVm(VoteModel vm) {
        this.vm = vm;
    }
}
