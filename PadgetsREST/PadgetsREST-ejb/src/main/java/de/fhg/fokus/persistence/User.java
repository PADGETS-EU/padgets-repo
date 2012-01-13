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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByAge", query = "SELECT u FROM User u WHERE u.age = :age"),
    @NamedQuery(name = "User.findByAuthtime", query = "SELECT u FROM User u WHERE u.authtime = :authtime"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByNiddlename", query = "SELECT u FROM User u WHERE u.niddlename = :niddlename"),
    @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByOpenIDVerifiedIdentifier", query = "SELECT u FROM User u WHERE u.openIDVerifiedIdentifier = :openIDVerifiedIdentifier"),
    @NamedQuery(name = "User.findByOpenid", query = "SELECT u FROM User u WHERE u.openid = :openid"),
    @NamedQuery(name = "User.findByOrganization", query = "SELECT u FROM User u WHERE u.organization = :organization"),
    @NamedQuery(name = "User.findByUserSIGN", query = "SELECT u FROM User u WHERE u.userSIGN = :userSIGN"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private Integer idUser;
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
    @Column(name = "Role")
    private String role;
    @ManyToMany(mappedBy = "userList")
    private List<Campaign> campaignList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Smpaccount> smpaccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Publishchannel> publishchannelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Authdata> authdataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private List<Campaign> campaignList1;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
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
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @XmlTransient
    public List<Campaign> getCampaignList1() {
        return campaignList1;
    }

    public void setCampaignList1(List<Campaign> campaignList1) {
        this.campaignList1 = campaignList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.User[ idUser=" + idUser + " ]";
    }
    
}
