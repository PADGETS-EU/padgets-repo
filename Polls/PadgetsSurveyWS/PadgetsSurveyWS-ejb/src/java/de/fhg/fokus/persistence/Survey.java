/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "survey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Survey.findAll", query = "SELECT s FROM Survey s"),
    @NamedQuery(name = "Survey.findByIdSurvey", query = "SELECT s FROM Survey s WHERE s.idSurvey = :idSurvey"),
    @NamedQuery(name = "Survey.findByName", query = "SELECT s FROM Survey s WHERE s.name = :name"),
    @NamedQuery(name = "Survey.findBySurveyKey", query = "SELECT s FROM Survey s WHERE s.surveyKey = :surveyKey"),
    @NamedQuery(name = "Survey.findByRandomQuestionOrder", query = "SELECT s FROM Survey s WHERE s.randomQuestionOrder = :randomQuestionOrder"),
    @NamedQuery(name = "Survey.findByStartDate", query = "SELECT s FROM Survey s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "Survey.findByEndDate", query = "SELECT s FROM Survey s WHERE s.endDate = :endDate")})
public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSurvey")
    private Integer idSurvey;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "survey_key")
    private String surveyKey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Random_Question_Order")
    private boolean randomQuestionOrder;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "Creator_idCreator", referencedColumnName = "idCreator")
    @ManyToOne
    private Creator creatoridCreator;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyidSurvey")
    private List<Question> questionList;

    public Survey() {
    }

    public Survey(Integer idSurvey) {
        this.idSurvey = idSurvey;
    }

    public Survey(Integer idSurvey, boolean randomQuestionOrder) {
        this.idSurvey = idSurvey;
        this.randomQuestionOrder = randomQuestionOrder;
    }

    @XmlTransient
    public Integer getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(Integer idSurvey) {
        this.idSurvey = idSurvey;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name="key")
    public String getSurveyKey() {
        return surveyKey;
    }

    public void setSurveyKey(String surveyKey) {
        this.surveyKey = surveyKey;
    }

    @XmlTransient
    public boolean getRandomQuestionOrder() {
        return randomQuestionOrder;
    }

    public void setRandomQuestionOrder(boolean randomQuestionOrder) {
        this.randomQuestionOrder = randomQuestionOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
@XmlTransient
    public Creator getCreatoridCreator() {
        return creatoridCreator;
    }

    public void setCreatoridCreator(Creator creatoridCreator) {
        this.creatoridCreator = creatoridCreator;
    }

    @XmlElement(name = "question")
    @XmlElementWrapper(name = "questions") 
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
    
        public boolean addQuestion(Question q){
        return this.questionList.add(q);
    }
    
    public boolean removeQuestion(Question q){
        return this.questionList.remove(q);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSurvey != null ? idSurvey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Survey)) {
            return false;
        }
        Survey other = (Survey) object;
        if ((this.idSurvey == null && other.idSurvey != null) || (this.idSurvey != null && !this.idSurvey.equals(other.idSurvey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Survey[ idSurvey=" + idSurvey + " ]";
    }
    
}
