/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "answerer_answered_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswererAnsweredAnswer.findAll", query = "SELECT a FROM AnswererAnsweredAnswer a"),
    @NamedQuery(name = "AnswererAnsweredAnswer.findByIdAnswereransweredAnswer", query = "SELECT a FROM AnswererAnsweredAnswer a WHERE a.idAnswereransweredAnswer = :idAnswereransweredAnswer"),
    @NamedQuery(name = "AnswererAnsweredAnswer.findByAnswerDate", query = "SELECT a FROM AnswererAnsweredAnswer a WHERE a.answerDate = :answerDate")})
public class AnswererAnsweredAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAnswerer_answered_Answer")
    private Integer idAnswereransweredAnswer;
    @Column(name = "Answer_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;
    @Lob
    @Size(max = 65535)
    @Column(name = "Answer_Text")
    private String answerText;
    @JoinColumn(name = "Answerer_idAnswerer", referencedColumnName = "idAnswerer")
    @ManyToOne(optional = false)
    private Answerer answereridAnswerer;
    @JoinColumn(name = "Answer_idAnswer", referencedColumnName = "idAnswer")
    @ManyToOne(optional = false)
    private Answer answeridAnswer;

    public AnswererAnsweredAnswer() {
    }

    public AnswererAnsweredAnswer(Integer idAnswereransweredAnswer) {
        this.idAnswereransweredAnswer = idAnswereransweredAnswer;
    }

    @JsonIgnore
    @XmlTransient
    public Integer getIdAnswereransweredAnswer() {
        return idAnswereransweredAnswer;
    }

    public void setIdAnswereransweredAnswer(Integer idAnswereransweredAnswer) {
        this.idAnswereransweredAnswer = idAnswereransweredAnswer;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    @JsonIgnore
    @XmlTransient
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @JsonIgnore
    @XmlTransient   
    public Answerer getAnswereridAnswerer() {
        return answereridAnswerer;
    }

    public void setAnswereridAnswerer(Answerer answereridAnswerer) {
        this.answereridAnswerer = answereridAnswerer;
    }

    @XmlIDREF
    public Answer getAnsweridAnswer() {
        return answeridAnswer;
    }

    public void setAnsweridAnswer(Answer answeridAnswer) {
        this.answeridAnswer = answeridAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswereransweredAnswer != null ? idAnswereransweredAnswer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnswererAnsweredAnswer)) {
            return false;
        }
        AnswererAnsweredAnswer other = (AnswererAnsweredAnswer) object;
        if ((this.idAnswereransweredAnswer == null && other.idAnswereransweredAnswer != null) || (this.idAnswereransweredAnswer != null && !this.idAnswereransweredAnswer.equals(other.idAnswereransweredAnswer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.AnswererAnsweredAnswer[ idAnswereransweredAnswer=" + idAnswereransweredAnswer + " ]";
    }
}
