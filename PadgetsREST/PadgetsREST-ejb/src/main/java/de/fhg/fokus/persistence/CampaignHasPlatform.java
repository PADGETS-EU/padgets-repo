/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "campaign_has_platform")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampaignHasPlatform.findAll", query = "SELECT c FROM CampaignHasPlatform c"),
    @NamedQuery(name = "CampaignHasPlatform.findByIdCampaignhasPlatform", query = "SELECT c FROM CampaignHasPlatform c WHERE c.idCampaignhasPlatform = :idCampaignhasPlatform"),
    @NamedQuery(name = "CampaignHasPlatform.findByActive", query = "SELECT c FROM CampaignHasPlatform c WHERE c.active = :active")})
public class CampaignHasPlatform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCampaign_has_Platform")
    private Integer idCampaignhasPlatform;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;
    @JoinColumn(name = "idPlatform", referencedColumnName = "idPlatform")
    @ManyToOne(optional = false)
    private Platform idPlatform;

    public CampaignHasPlatform() {
    }

    public CampaignHasPlatform(Integer idCampaignhasPlatform) {
        this.idCampaignhasPlatform = idCampaignhasPlatform;
    }

    public Integer getIdCampaignhasPlatform() {
        return idCampaignhasPlatform;
    }

    public void setIdCampaignhasPlatform(Integer idCampaignhasPlatform) {
        this.idCampaignhasPlatform = idCampaignhasPlatform;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Campaign getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Campaign idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Platform getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Platform idPlatform) {
        this.idPlatform = idPlatform;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampaignhasPlatform != null ? idCampaignhasPlatform.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CampaignHasPlatform)) {
            return false;
        }
        CampaignHasPlatform other = (CampaignHasPlatform) object;
        if ((this.idCampaignhasPlatform == null && other.idCampaignhasPlatform != null) || (this.idCampaignhasPlatform != null && !this.idCampaignhasPlatform.equals(other.idCampaignhasPlatform))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.CampaignHasPlatform[ idCampaignhasPlatform=" + idCampaignhasPlatform + " ]";
    }
    
}
