/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "invitation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i"),
    @NamedQuery(name = "Invitation.findByIdInvitation", query = "SELECT i FROM Invitation i WHERE i.idInvitation = :idInvitation"),
    @NamedQuery(name = "Invitation.findByEmail", query = "SELECT i FROM Invitation i WHERE i.email = :email"),
    @NamedQuery(name = "Invitation.findByToken", query = "SELECT i FROM Invitation i WHERE i.token = :token")})
public class Invitation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInvitation")
    private Integer idInvitation;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Invitation() {
    }

    public Invitation(Integer idInvitation) {
        this.idInvitation = idInvitation;
    }

    public Integer getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(Integer idInvitation) {
        this.idInvitation = idInvitation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        hash += (idInvitation != null ? idInvitation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.idInvitation == null && other.idInvitation != null) || (this.idInvitation != null && !this.idInvitation.equals(other.idInvitation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Invitation[ idInvitation=" + idInvitation + " ]";
    }
    
}
