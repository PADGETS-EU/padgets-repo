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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "campaign")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campaign.findAll", query = "SELECT c FROM Campaign c"),
    @NamedQuery(name = "Campaign.findByIdCampaign", query = "SELECT c FROM Campaign c WHERE c.idCampaign = :idCampaign"),
    @NamedQuery(name = "Campaign.findByTitle", query = "SELECT c FROM Campaign c WHERE c.title = :title"),
    @NamedQuery(name = "Campaign.findByActive", query = "SELECT c FROM Campaign c WHERE c.active = :active"),
    @NamedQuery(name = "Campaign.findByCreationdate", query = "SELECT c FROM Campaign c WHERE c.creationdate = :creationdate"),
    @NamedQuery(name = "Campaign.findByStartdate", query = "SELECT c FROM Campaign c WHERE c.startdate = :startdate"),
    @NamedQuery(name = "Campaign.findByEnddate", query = "SELECT c FROM Campaign c WHERE c.enddate = :enddate"),
    @NamedQuery(name = "Campaign.findByNotes", query = "SELECT c FROM Campaign c WHERE c.notes = :notes"),
    @NamedQuery(name = "Campaign.findByUrl", query = "SELECT c FROM Campaign c WHERE c.url = :url"),
    @NamedQuery(name = "Campaign.findByHashTag", query = "SELECT c FROM Campaign c WHERE c.hashTag = :hashTag")})
public class Campaign implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCampaign")
    private Integer idCampaign;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @Column(name = "creationdate")
    @Temporal(TemporalType.DATE)
    private Date creationdate;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Size(max = 255)
    @Column(name = "notes")
    private String notes;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Size(max = 255)
    @Column(name = "hashTag")
    private String hashTag;
    @JoinTable(name = "campaign_has_publishchannel", joinColumns = {
        @JoinColumn(name = "Campaign_idCampaign", referencedColumnName = "idCampaign")}, inverseJoinColumns = {
        @JoinColumn(name = "PublishChannel_idPublishChannel", referencedColumnName = "idPublishChannel")})
    @ManyToMany
    private List<Publishchannel> publishchannelList;
    @JoinTable(name = "campaign_has_user", joinColumns = {
        @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")}, inverseJoinColumns = {
        @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")})
    @ManyToMany
    private List<Userdata> userdataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<CampaignHasPlatform> campaignHasPlatformList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campaignidCampaign")
    private List<Campaigntopics> campaigntopicsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Message> messageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Blogger> bloggerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Facebookvisits> facebookvisitsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Facebookdata> facebookdataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Action> actionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Youtube> youtubeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCampaign")
    private List<Survey> surveyList;
    @JoinColumn(name = "idLocation", referencedColumnName = "idLocation")
    @ManyToOne(optional = false)
    private Location idLocation;
    @JoinColumn(name = "idUser", referencedColumnName = "idUserData")
    @ManyToOne(optional = false)
    private Userdata idUser;

    public Campaign() {
    }

    public Campaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Campaign(Integer idCampaign, boolean active) {
        this.idCampaign = idCampaign;
        this.active = active;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    @XmlTransient
    public List<Publishchannel> getPublishchannelList() {
        return publishchannelList;
    }

    public void setPublishchannelList(List<Publishchannel> publishchannelList) {
        this.publishchannelList = publishchannelList;
    }

    @XmlTransient
    public List<Userdata> getUserdataList() {
        return userdataList;
    }

    public void setUserdataList(List<Userdata> userdataList) {
        this.userdataList = userdataList;
    }

    @XmlTransient
    public List<CampaignHasPlatform> getCampaignHasPlatformList() {
        return campaignHasPlatformList;
    }

    public void setCampaignHasPlatformList(List<CampaignHasPlatform> campaignHasPlatformList) {
        this.campaignHasPlatformList = campaignHasPlatformList;
    }

    @XmlTransient
    public List<Campaigntopics> getCampaigntopicsList() {
        return campaigntopicsList;
    }

    public void setCampaigntopicsList(List<Campaigntopics> campaigntopicsList) {
        this.campaigntopicsList = campaigntopicsList;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @XmlTransient
    public List<Blogger> getBloggerList() {
        return bloggerList;
    }

    public void setBloggerList(List<Blogger> bloggerList) {
        this.bloggerList = bloggerList;
    }

    @XmlTransient
    public List<Facebookvisits> getFacebookvisitsList() {
        return facebookvisitsList;
    }

    public void setFacebookvisitsList(List<Facebookvisits> facebookvisitsList) {
        this.facebookvisitsList = facebookvisitsList;
    }

    @XmlTransient
    public List<Facebookdata> getFacebookdataList() {
        return facebookdataList;
    }

    public void setFacebookdataList(List<Facebookdata> facebookdataList) {
        this.facebookdataList = facebookdataList;
    }

    @XmlTransient
    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    @XmlTransient
    public List<Youtube> getYoutubeList() {
        return youtubeList;
    }

    public void setYoutubeList(List<Youtube> youtubeList) {
        this.youtubeList = youtubeList;
    }

    @XmlTransient
    public List<Survey> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<Survey> surveyList) {
        this.surveyList = surveyList;
    }

    public Location getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Location idLocation) {
        this.idLocation = idLocation;
    }

    public Userdata getIdUser() {
        return idUser;
    }

    public void setIdUser(Userdata idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampaign != null ? idCampaign.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campaign)) {
            return false;
        }
        Campaign other = (Campaign) object;
        if ((this.idCampaign == null && other.idCampaign != null) || (this.idCampaign != null && !this.idCampaign.equals(other.idCampaign))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Campaign[ idCampaign=" + idCampaign + " ]";
    }
    
}
