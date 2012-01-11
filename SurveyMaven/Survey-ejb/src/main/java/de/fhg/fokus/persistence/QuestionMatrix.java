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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "question_matrix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionMatrix.findAll", query = "SELECT q FROM QuestionMatrix q"),
    @NamedQuery(name = "QuestionMatrix.findByIdQuestionMatrix", query = "SELECT q FROM QuestionMatrix q WHERE q.idQuestionMatrix = :idQuestionMatrix"),
    @NamedQuery(name = "QuestionMatrix.findByCaption", query = "SELECT q FROM QuestionMatrix q WHERE q.caption = :caption")})
public class QuestionMatrix implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idQuestion_Matrix")
    private Integer idQuestionMatrix;
    @Size(max = 255)
    @Column(name = "Caption")
    private String caption;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionMatrixidQuestionMatrix")
    private List<Question> questionList;

    public QuestionMatrix() {
    }

    public QuestionMatrix(Integer idQuestionMatrix) {
        this.idQuestionMatrix = idQuestionMatrix;
    }

    public Integer getIdQuestionMatrix() {
        return idQuestionMatrix;
    }

    public void setIdQuestionMatrix(Integer idQuestionMatrix) {
        this.idQuestionMatrix = idQuestionMatrix;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlTransient
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestionMatrix != null ? idQuestionMatrix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionMatrix)) {
            return false;
        }
        QuestionMatrix other = (QuestionMatrix) object;
        if ((this.idQuestionMatrix == null && other.idQuestionMatrix != null) || (this.idQuestionMatrix != null && !this.idQuestionMatrix.equals(other.idQuestionMatrix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.QuestionMatrix[ idQuestionMatrix=" + idQuestionMatrix + " ]";
    }
    
}
