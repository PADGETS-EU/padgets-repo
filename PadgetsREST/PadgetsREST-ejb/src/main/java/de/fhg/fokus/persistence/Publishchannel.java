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
@Table(name = "publishchannel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publishchannel.findAll", query = "SELECT p FROM Publishchannel p"),
    @NamedQuery(name = "Publishchannel.findByIdPublishChannel", query = "SELECT p FROM Publishchannel p WHERE p.idPublishChannel = :idPublishChannel"),
    @NamedQuery(name = "Publishchannel.findByName", query = "SELECT p FROM Publishchannel p WHERE p.name = :name"),
    @NamedQuery(name = "Publishchannel.findByNetwork", query = "SELECT p FROM Publishchannel p WHERE p.network = :network"),
    @NamedQuery(name = "Publishchannel.findByNetworkPageId", query = "SELECT p FROM Publishchannel p WHERE p.networkPageId = :networkPageId"),
    @NamedQuery(name = "Publishchannel.findByOAuth2Token", query = "SELECT p FROM Publishchannel p WHERE p.oAuth2Token = :oAuth2Token")})
public class Publishchannel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPublishChannel")
    private Integer idPublishChannel;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "network")
    private String network;
    @Size(max = 255)
    @Column(name = "networkPageId")
    private String networkPageId;
    @Size(max = 255)
    @Column(name = "oAuth2Token")
    private String oAuth2Token;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;
    @JoinColumn(name = "idSmpAccount", referencedColumnName = "idSmpAccount")
    @ManyToOne(optional = false)
    private Smpaccount idSmpAccount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPublishChannel")
    private List<Publisheditem> publisheditemList;

    public Publishchannel() {
    }

    public Publishchannel(Integer idPublishChannel) {
        this.idPublishChannel = idPublishChannel;
    }

    public Integer getIdPublishChannel() {
        return idPublishChannel;
    }

    public void setIdPublishChannel(Integer idPublishChannel) {
        this.idPublishChannel = idPublishChannel;
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

    public String getNetworkPageId() {
        return networkPageId;
    }

    public void setNetworkPageId(String networkPageId) {
        this.networkPageId = networkPageId;
    }

    public String getOAuth2Token() {
        return oAuth2Token;
    }

    public void setOAuth2Token(String oAuth2Token) {
        this.oAuth2Token = oAuth2Token;
    }

    public Campaign getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Campaign idCampaign) {
        this.idCampaign = idCampaign;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Smpaccount getIdSmpAccount() {
        return idSmpAccount;
    }

    public void setIdSmpAccount(Smpaccount idSmpAccount) {
        this.idSmpAccount = idSmpAccount;
    }

    @XmlTransient
    public List<Publisheditem> getPublisheditemList() {
        return publisheditemList;
    }

    public void setPublisheditemList(List<Publisheditem> publisheditemList) {
        this.publisheditemList = publisheditemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublishChannel != null ? idPublishChannel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publishchannel)) {
            return false;
        }
        Publishchannel other = (Publishchannel) object;
        if ((this.idPublishChannel == null && other.idPublishChannel != null) || (this.idPublishChannel != null && !this.idPublishChannel.equals(other.idPublishChannel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Publishchannel[ idPublishChannel=" + idPublishChannel + " ]";
    }
    
}