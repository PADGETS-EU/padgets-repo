/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Survey.findBySKey", query = "SELECT s FROM Survey s WHERE s.sKey = :sKey")})
public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSurvey")
    private Integer idSurvey;
    @Size(max = 255)
    @Column(name = "sKey")
    private String sKey;
    @OneToMany(mappedBy = "idSurvey")
    private List<Publisheditem> publisheditemList;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Survey() {
    }

    public Survey(Integer idSurvey) {
        this.idSurvey = idSurvey;
    }

    public Integer getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(Integer idSurvey) {
        this.idSurvey = idSurvey;
    }

    public String getSKey() {
        return sKey;
    }

    public void setSKey(String sKey) {
        this.sKey = sKey;
    }

    @XmlTransient
    public List<Publisheditem> getPublisheditemList() {
        return publisheditemList;
    }

    public void setPublisheditemList(List<Publisheditem> publisheditemList) {
        this.publisheditemList = publisheditemList;
    }

    public Campaign getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Campaign idCampaign) {
        this.idCampaign = idCampaign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSurvey != null ? idSurvey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
