/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "userrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u"),
    @NamedQuery(name = "Userrole.findByIdUserRole", query = "SELECT u FROM Userrole u WHERE u.idUserRole = :idUserRole"),
    @NamedQuery(name = "Userrole.findByUserRole", query = "SELECT u FROM Userrole u WHERE u.userRole = :userRole")})
public class Userrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUserRole")
    private Integer idUserRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "userRole")
    private String userRole;
    @Column(name = "lastActive")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActive;
    @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")
    @ManyToOne(optional = false)
    private Userdata idUserData;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Userrole() {
    }

    public Userrole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Userrole(Integer idUserRole, String userRole) {
        this.idUserRole = idUserRole;
        this.userRole = userRole;
    }

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    @JsonIgnore
    @XmlTransient
    public Userdata getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Userdata idUserData) {
        this.idUserData = idUserData;
    }

    @JsonIgnore
    @XmlTransient
    public Campaign getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Campaign idCampaign) {
        this.idCampaign = idCampaign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserRole != null ? idUserRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.idUserRole == null && other.idUserRole != null) || (this.idUserRole != null && !this.idUserRole.equals(other.idUserRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Userrole[ idUserRole=" + idUserRole + " ]";
    }
}
