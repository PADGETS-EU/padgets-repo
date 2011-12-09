/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "creator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creator.findAll", query = "SELECT c FROM Creator c"),
    @NamedQuery(name = "Creator.findByIdCreator", query = "SELECT c FROM Creator c WHERE c.idCreator = :idCreator"),
    @NamedQuery(name = "Creator.findByName", query = "SELECT c FROM Creator c WHERE c.name = :name")})
public class Creator implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCreator")
    private Integer idCreator;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @OneToMany(mappedBy = "creatoridCreator")
    private List<Survey> surveyList;

    public Creator() {
    }

    public Creator(Integer idCreator) {
        this.idCreator = idCreator;
    }

    public Integer getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Integer idCreator) {
        this.idCreator = idCreator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Survey> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<Survey> surveyList) {
        this.surveyList = surveyList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCreator != null ? idCreator.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creator)) {
            return false;
        }
        Creator other = (Creator) object;
        if ((this.idCreator == null && other.idCreator != null) || (this.idCreator != null && !this.idCreator.equals(other.idCreator))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Creator[ idCreator=" + idCreator + " ]";
    }
    
}
