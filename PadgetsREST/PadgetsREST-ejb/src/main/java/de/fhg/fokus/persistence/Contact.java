/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idContact")
    private Integer idContact;
    @Column(name = "provider")
    private Boolean provider;
    @Size(max = 255)
    @Column(name = "providerId")
    private String providerId;
    @Size(max = 255)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "idSmpAccount", referencedColumnName = "idSmpAccount")
    @ManyToOne(optional = false)
    private Smpaccount idSmpAccount;
    @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")
    @ManyToOne
    private Userdata idUserData;

    public Contact() {
    }

    @JsonIgnore
    @XmlTransient
    public Userdata getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Userdata idUserData) {
        this.idUserData = idUserData;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContact != null ? idContact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comment)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.idContact == null && other.idContact != null) || (this.idContact != null && !this.idContact.equals(other.idContact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Comment[ idComment=" + idContact + " ]";
    }

    /**
     * @return the idContact
     */
    public Integer getIdContact() {
        return idContact;
    }

    /**
     * @param idContact the idContact to set
     */
    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    /**
     * @return the provider
     */
    public Boolean getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(Boolean provider) {
        this.provider = provider;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the idSmpAccount
     */
    @JsonIgnore
    @XmlTransient
    public Smpaccount getIdSmpAccount() {
        return idSmpAccount;
    }

    /**
     * @param idSmpAccount the idSmpAccount to set
     */
    public void setIdSmpAccount(Smpaccount idSmpAccount) {
        this.idSmpAccount = idSmpAccount;
    }
}
