/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "authdata")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Authdata.findAll", query = "SELECT a FROM Authdata a"),
    @NamedQuery(name = "Authdata.findByIdAuthData", query = "SELECT a FROM Authdata a WHERE a.idAuthData = :idAuthData"),
    @NamedQuery(name = "Authdata.findByExpire", query = "SELECT a FROM Authdata a WHERE a.expire = :expire"),
    @NamedQuery(name = "Authdata.findByHashValue", query = "SELECT a FROM Authdata a WHERE a.hashValue = :hashValue"),
    @NamedQuery(name = "Authdata.findByNetwork", query = "SELECT a FROM Authdata a WHERE a.network = :network"),
    @NamedQuery(name = "Authdata.findByPermissions", query = "SELECT a FROM Authdata a WHERE a.permissions = :permissions"),
    @NamedQuery(name = "Authdata.findByRedirectUrl", query = "SELECT a FROM Authdata a WHERE a.redirectUrl = :redirectUrl"),
    @NamedQuery(name = "Authdata.findByType", query = "SELECT a FROM Authdata a WHERE a.type = :type")})
public class Authdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAuthData")
    private Integer idAuthData;
    @Size(max = 255)
    @Column(name = "expire")
    private String expire;
    @Size(max = 255)
    @Column(name = "hashValue")
    private String hashValue;
    @Size(max = 255)
    @Column(name = "network")
    private String network;
    @Size(max = 255)
    @Column(name = "permissions")
    private String permissions;
    @Size(max = 255)
    @Column(name = "redirectUrl")
    private String redirectUrl;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")
    @ManyToOne(optional = false)
    private Userdata idUserData;
    @JoinColumn(name = "idSmpAccount", referencedColumnName = "idSmpAccount")
    @ManyToOne(optional = false)
    private Smpaccount idSmpAccount;

    public Authdata() {
    }

    public Authdata(Integer idAuthData) {
        this.idAuthData = idAuthData;
    }

    public Integer getIdAuthData() {
        return idAuthData;
    }

    public void setIdAuthData(Integer idAuthData) {
        this.idAuthData = idAuthData;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Userdata getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Userdata idUserData) {
        this.idUserData = idUserData;
    }

    public Smpaccount getIdSmpAccount() {
        return idSmpAccount;
    }

    public void setIdSmpAccount(Smpaccount idSmpAccount) {
        this.idSmpAccount = idSmpAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuthData != null ? idAuthData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Authdata)) {
            return false;
        }
        Authdata other = (Authdata) object;
        if ((this.idAuthData == null && other.idAuthData != null) || (this.idAuthData != null && !this.idAuthData.equals(other.idAuthData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Authdata[ idAuthData=" + idAuthData + " ]";
    }
    
}
