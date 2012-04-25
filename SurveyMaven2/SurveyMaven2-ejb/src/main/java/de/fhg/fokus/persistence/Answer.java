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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByIdAnswer", query = "SELECT a FROM Answer a WHERE a.idAnswer = :idAnswer"),
    @NamedQuery(name = "Answer.findByTextfield", query = "SELECT a FROM Answer a WHERE a.textfield = :textfield"),
    @NamedQuery(name = "Answer.findByOrderNumber", query = "SELECT a FROM Answer a WHERE a.orderNumber = :orderNumber")})
public class Answer implements Serializable, Comparable<Answer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAnswer")
    private Integer idAnswer;
    @Size(max = 255)
    @Column(name = "Label")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Textfield")
    private boolean textfield;
    @Column(name = "Order_Number")
    private Integer orderNumber;
    @JoinColumn(name = "Question_idQuestion", referencedColumnName = "idQuestion")
    @ManyToOne(optional = false)
    private Question questionidQuestion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answeridAnswer")
    private List<AnswererAnsweredAnswer> answererAnsweredAnswerList;

    public Answer() {
    }

    public Answer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Answer(Integer idAnswer, boolean textfield) {
        this.idAnswer = idAnswer;
        this.textfield = textfield;
    }
 
    public Integer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }
    
    @XmlID
    public String getId(){
        return getIdAnswer().toString();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonIgnore
    @XmlTransient
    public boolean getTextfield() {
        return textfield;
    }

    public void setTextfield(boolean textfield) {
        this.textfield = textfield;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @JsonIgnore
    @XmlTransient
    public Question getQuestionidQuestion() {
        return questionidQuestion;
    }

    public void setQuestionidQuestion(Question questionidQuestion) {
        this.questionidQuestion = questionidQuestion;
    }

    @JsonIgnore
    @XmlTransient
    public List<AnswererAnsweredAnswer> getAnswererAnsweredAnswerList() {
        return answererAnsweredAnswerList;
    }

    public void setAnswererAnsweredAnswerList(List<AnswererAnsweredAnswer> answererAnsweredAnswerList) {
        this.answererAnsweredAnswerList = answererAnsweredAnswerList;
    }

    public boolean addAnswererAnsweredAnswer(AnswererAnsweredAnswer q) {
        return this.answererAnsweredAnswerList.add(q);
    }

    public boolean removeAnswererAnsweredAnswer(AnswererAnsweredAnswer q) {
        return this.answererAnsweredAnswerList.remove(q);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswer != null ? idAnswer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.idAnswer == null && other.idAnswer != null) || (this.idAnswer != null && !this.idAnswer.equals(other.idAnswer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Answer[ idAnswer=" + idAnswer + " ]";
    }

    @Override
    public int compareTo(Answer a) {
        if (a.getOrderNumber() < this.getOrderNumber()) {
            return 1;
        } else {
            return -1;
        }
    }
}
