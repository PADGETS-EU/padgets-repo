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
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "platform")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Platform.findAll", query = "SELECT p FROM Platform p"),
    @NamedQuery(name = "Platform.findByIdPlatform", query = "SELECT p FROM Platform p WHERE p.idPlatform = :idPlatform"),
    @NamedQuery(name = "Platform.findByName", query = "SELECT p FROM Platform p WHERE p.name = :name")})
public class Platform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPlatform")
    private Integer idPlatform;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlatform")
    private List<CampaignHasPlatform> campaignHasPlatformList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlatform")
    private List<Action> actionList;

    public Platform() {
    }

    public Platform(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public Integer getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

       @JsonIgnore     @XmlTransient
    public List<CampaignHasPlatform> getCampaignHasPlatformList() {
        return campaignHasPlatformList;
    }

    public void setCampaignHasPlatformList(List<CampaignHasPlatform> campaignHasPlatformList) {
        this.campaignHasPlatformList = campaignHasPlatformList;
    }

       @JsonIgnore     @XmlTransient
    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatform != null ? idPlatform.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Platform)) {
            return false;
        }
        Platform other = (Platform) object;
        if ((this.idPlatform == null && other.idPlatform != null) || (this.idPlatform != null && !this.idPlatform.equals(other.idPlatform))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Platform[ idPlatform=" + idPlatform + " ]";
    }
    
}
