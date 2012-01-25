/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "userdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdata.findAll", query = "SELECT u FROM Userdata u"),
    @NamedQuery(name = "Userdata.findByIdUserData", query = "SELECT u FROM Userdata u WHERE u.idUserData = :idUserData"),
    @NamedQuery(name = "Userdata.findByAge", query = "SELECT u FROM Userdata u WHERE u.age = :age"),
    @NamedQuery(name = "Userdata.findByAuthtime", query = "SELECT u FROM Userdata u WHERE u.authtime = :authtime"),
    @NamedQuery(name = "Userdata.findByEmail", query = "SELECT u FROM Userdata u WHERE u.email = :email"),
    @NamedQuery(name = "Userdata.findByUsername", query = "SELECT u FROM Userdata u WHERE u.username = :username"),
    @NamedQuery(name = "Userdata.findByFirstname", query = "SELECT u FROM Userdata u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Userdata.findByNiddlename", query = "SELECT u FROM Userdata u WHERE u.niddlename = :niddlename"),
    @NamedQuery(name = "Userdata.findBySurname", query = "SELECT u FROM Userdata u WHERE u.surname = :surname"),
    @NamedQuery(name = "Userdata.findByGender", query = "SELECT u FROM Userdata u WHERE u.gender = :gender"),
    @NamedQuery(name = "Userdata.findByOpenIDVerifiedIdentifier", query = "SELECT u FROM Userdata u WHERE u.openIDVerifiedIdentifier = :openIDVerifiedIdentifier"),
    @NamedQuery(name = "Userdata.findByOpenid", query = "SELECT u FROM Userdata u WHERE u.openid = :openid"),
    @NamedQuery(name = "Userdata.findByOrganization", query = "SELECT u FROM Userdata u WHERE u.organization = :organization"),
    @NamedQuery(name = "Userdata.findByUserSIGN", query = "SELECT u FROM Userdata u WHERE u.userSIGN = :userSIGN"),
    @NamedQuery(name = "Userdata.findByUserRole", query = "SELECT u FROM Userdata u WHERE u.userRole = :userRole"),
    @NamedQuery(name = "Userdata.findByViewLanguage", query = "SELECT u FROM Userdata u WHERE u.viewLanguage = :viewLanguage")})
public class Userdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUserData")
    private Integer idUserData;
    @Column(name = "age")
    @Temporal(TemporalType.DATE)
    private Date age;
    @Column(name = "authtime")
    private BigInteger authtime;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "niddlename")
    private String niddlename;
    @Size(max = 255)
    @Column(name = "surname")
    private String surname;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 255)
    @Column(name = "openIDVerifiedIdentifier")
    private String openIDVerifiedIdentifier;
    @Size(max = 255)
    @Column(name = "openid")
    private String openid;
    @Size(max = 255)
    @Column(name = "organization")
    private String organization;
    @Size(max = 255)
    @Column(name = "userSIGN")
    private String userSIGN;
    @Size(max = 255)
    @Column(name = "userRole")
    private String userRole;
    @Size(max = 255)
    @Column(name = "viewLanguage")
    private String viewLanguage;
    @ManyToMany(mappedBy = "userdataList")
    private List<Campaign> CampaignListHelpers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserData")
    private List<Smpaccount> smpaccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserData")
    private List<Publishchannel> publishchannelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserData")
    private List<Authdata> authdataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserData")
    private List<Message> messageList;
    @OneToMany(mappedBy = "idUserData")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Campaign> CampaignListManager;

    public Userdata() {
    }

    public Userdata(Integer idUserData) {
        this.idUserData = idUserData;
    }

    public Integer getIdUserData() {
        return idUserData;
    }

    public void setIdUserData(Integer idUserData) {
        this.idUserData = idUserData;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public BigInteger getAuthtime() {
        return authtime;
    }

    public void setAuthtime(BigInteger authtime) {
        this.authtime = authtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNiddlename() {
        return niddlename;
    }

    public void setNiddlename(String niddlename) {
        this.niddlename = niddlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOpenIDVerifiedIdentifier() {
        return openIDVerifiedIdentifier;
    }

    public void setOpenIDVerifiedIdentifier(String openIDVerifiedIdentifier) {
        this.openIDVerifiedIdentifier = openIDVerifiedIdentifier;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getUserSIGN() {
        return userSIGN;
    }

    public void setUserSIGN(String userSIGN) {
        this.userSIGN = userSIGN;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getViewLanguage() {
        return viewLanguage;
    }

    public void setViewLanguage(String viewLanguage) {
        this.viewLanguage = viewLanguage;
    }

    @XmlTransient
    public List<Smpaccount> getSmpaccountList() {
        return smpaccountList;
    }

    public void setSmpaccountList(List<Smpaccount> smpaccountList) {
        this.smpaccountList = smpaccountList;
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

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Campaign> getCampaignListHelpers() {
        return CampaignListHelpers;
    }

    public void setCampaignListHelpers(List<Campaign> CampaignListHelpers) {
        this.CampaignListHelpers = CampaignListHelpers;
    }

    public boolean addCampaignHelper(Campaign campaign) {
        return this.CampaignListHelpers.add(campaign);
    }

    public boolean removeCampaignHelper(Campaign campaign) {
        return this.CampaignListHelpers.remove(campaign);
    }

    public List<Campaign> getCampaignListManager() {
        return CampaignListManager;
    }

    public void setCampaignListManager(List<Campaign> CampaignListManager) {
        this.CampaignListManager = CampaignListManager;
    }

    public boolean addCampaignManager(Campaign campaign) {
        return this.CampaignListManager.add(campaign);
    }

    public boolean removeCampaignManager(Campaign campaign) {
        return this.CampaignListManager.remove(campaign);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserData != null ? idUserData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdata)) {
            return false;
        }
        Userdata other = (Userdata) object;
        if ((this.idUserData == null && other.idUserData != null) || (this.idUserData != null && !this.idUserData.equals(other.idUserData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Userdata[ idUserData=" + idUserData + " ]";
    }
}
