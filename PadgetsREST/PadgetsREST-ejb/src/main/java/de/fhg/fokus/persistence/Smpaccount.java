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
@Table(name = "smpaccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Smpaccount.findAll", query = "SELECT s FROM Smpaccount s"),
    @NamedQuery(name = "Smpaccount.findByIdSmpAccount", query = "SELECT s FROM Smpaccount s WHERE s.idSmpAccount = :idSmpAccount"),
    @NamedQuery(name = "Smpaccount.findByName", query = "SELECT s FROM Smpaccount s WHERE s.name = :name"),
    @NamedQuery(name = "Smpaccount.findByNetwork", query = "SELECT s FROM Smpaccount s WHERE s.network = :network"),
    @NamedQuery(name = "Smpaccount.findByNetworkUserId", query = "SELECT s FROM Smpaccount s WHERE s.networkUserId = :networkUserId"),
    @NamedQuery(name = "Smpaccount.findByOAuth2Token", query = "SELECT s FROM Smpaccount s WHERE s.oAuth2Token = :oAuth2Token"),
    @NamedQuery(name = "Smpaccount.findByOAuthSecret", query = "SELECT s FROM Smpaccount s WHERE s.oAuthSecret = :oAuthSecret"),
    @NamedQuery(name = "Smpaccount.findByOAuthToken", query = "SELECT s FROM Smpaccount s WHERE s.oAuthToken = :oAuthToken"),
    @NamedQuery(name = "Smpaccount.findByPrifileUrl", query = "SELECT s FROM Smpaccount s WHERE s.prifileUrl = :prifileUrl"),
    @NamedQuery(name = "Smpaccount.findByUsername", query = "SELECT s FROM Smpaccount s WHERE s.username = :username")})
public class Smpaccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSmpAccount")
    private Integer idSmpAccount;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "network")
    private String network;
    @Size(max = 255)
    @Column(name = "networkUserId")
    private String networkUserId;
    @Size(max = 255)
    @Column(name = "oAuth2Token")
    private String oAuth2Token;
    @Size(max = 255)
    @Column(name = "oAuthSecret")
    private String oAuthSecret;
    @Size(max = 255)
    @Column(name = "oAuthToken")
    private String oAuthToken;
    @Size(max = 255)
    @Column(name = "prifileUrl")
    private String prifileUrl;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")
    @ManyToOne(optional = false)
    private Userdata idUserData;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSmpAccount")
    private List<Publishchannel> publishchannelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSmpAccount")
    private List<Authdata> authdataList;

    public Smpaccount() {
    }

    public Smpaccount(Integer idSmpAccount) {
        this.idSmpAccount = idSmpAccount;
    }

    public Integer getIdSmpAccount() {
        return idSmpAccount;
    }

    public void setIdSmpAccount(Integer idSmpAccount) {
        this.idSmpAccount = idSmpAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNetworkUserId() {
        return networkUserId;
    }

    public void setNetworkUserId(String networkUserId) {
        this.networkUserId = networkUserId;
    }

    public String getOAuth2Token() {
        return oAuth2Token;
    }

    public void setOAuth2Token(String oAuth2Token) {
        this.oAuth2Token = oAuth2Token;
    }

    public String getOAuthSecret() {
        return oAuthSecret;
    }

    public void setOAuthSecret(String oAuthSecret) {
        this.oAuthSecret = oAuthSecret;
    }

    public String getOAuthToken() {
        return oAuthToken;
    }

    public void setOAuthToken(String oAuthToken) {
        this.oAuthToken = oAuthToken;
    }

    public String getPrifileUrl() {
        return prifileUrl;
    }

    public void setPrifileUrl(String prifileUrl) {
        this.prifileUrl = prifileUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Userdata getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Userdata idUserData) {
        this.idUserData = idUserData;
    }

    @XmlTransient
    public List<Publishchannel> getPublishchannelList() {
        return publishchannelList;
    }

    public void setPublishchannelList(List<Publishchannel> publishchannelList) {
        this.publishchannelList = publishchannelList;
    }

    @XmlTransient
    public List<Authdata> getAuthdataList() {
        return authdataList;
    }

    public void setAuthdataList(List<Authdata> authdataList) {
        this.authdataList = authdataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSmpAccount != null ? idSmpAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Smpaccount)) {
            return false;
        }
        Smpaccount other = (Smpaccount) object;
        if ((this.idSmpAccount == null && other.idSmpAccount != null) || (this.idSmpAccount != null && !this.idSmpAccount.equals(other.idSmpAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Smpaccount[ idSmpAccount=" + idSmpAccount + " ]";
    }
    
}
