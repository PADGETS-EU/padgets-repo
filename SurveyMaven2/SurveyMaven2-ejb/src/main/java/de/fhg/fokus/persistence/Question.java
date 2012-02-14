/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByIdQuestion", query = "SELECT q FROM Question q WHERE q.idQuestion = :idQuestion"),
    @NamedQuery(name = "Question.findByAnswerLimit", query = "SELECT q FROM Question q WHERE q.answerLimit = :answerLimit"),
    @NamedQuery(name = "Question.findByOrderNumber", query = "SELECT q FROM Question q WHERE q.orderNumber = :orderNumber"),
    @NamedQuery(name = "Question.findByRandomAnswerOrder", query = "SELECT q FROM Question q WHERE q.randomAnswerOrder = :randomAnswerOrder"),
    @NamedQuery(name = "Question.findByQuestionType", query = "SELECT q FROM Question q WHERE q.questionType = :questionType")})
public class Question implements Serializable, Comparable<Question> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idQuestion")
    private Integer idQuestion;
    @Size(max = 255)
    @Column(name = "Label")
    private String label;
    @Column(name = "Answer_Limit")
    private Integer answerLimit;
    @Column(name = "Order_Number")
    private Integer orderNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Random_Answer_Order")
    private boolean randomAnswerOrder;
    @Size(max = 45)
    @Column(name = "Question_Type")
    private String questionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionidQuestion")
    private List<Answer> answerList;
    @JoinColumn(name = "Question_Matrix_idQuestion_Matrix", referencedColumnName = "idQuestion_Matrix")
    @ManyToOne
    private QuestionMatrix questionMatrixidQuestionMatrix;
    @JoinColumn(name = "Survey_idSurvey", referencedColumnName = "idSurvey")
    @ManyToOne(optional = false)
    private Survey surveyidSurvey;

    public Question() {
    }

    public Question(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Question(Integer idQuestion, boolean randomAnswerOrder) {
        this.idQuestion = idQuestion;
        this.randomAnswerOrder = randomAnswerOrder;
    }
    
    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getAnswerLimit() {
        return answerLimit;
    }

    public void setAnswerLimit(Integer answerLimit) {
        this.answerLimit = answerLimit;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean getRandomAnswerOrder() {
        return randomAnswerOrder;
    }

    public void setRandomAnswerOrder(boolean randomAnswerOrder) {
        this.randomAnswerOrder = randomAnswerOrder;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
 
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
    
     public boolean addAnswer(Answer answer){
        return this.answerList.add(answer);
    }
    
    public boolean removeAnswer(Answer answer){
        return this.answerList.remove(answer);
    }

    public QuestionMatrix getQuestionMatrixidQuestionMatrix() {
        return questionMatrixidQuestionMatrix;
    }

    public void setQuestionMatrixidQuestionMatrix(QuestionMatrix questionMatrixidQuestionMatrix) {
        this.questionMatrixidQuestionMatrix = questionMatrixidQuestionMatrix;
    }

    @XmlTransient
    public Survey getSurveyidSurvey() {
        return surveyidSurvey;
    }

    public void setSurveyidSurvey(Survey surveyidSurvey) {
        this.surveyidSurvey = surveyidSurvey;
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestion != null ? idQuestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.idQuestion == null && other.idQuestion != null) || (this.idQuestion != null && !this.idQuestion.equals(other.idQuestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Question[ idQuestion=" + idQuestion + " ]";
    }

    @Override
    public int compareTo(Question q) {
        if(q.getOrderNumber()<this.getOrderNumber()){
            return 1;
        } else {
            return -1;
        }
    }
    
}
