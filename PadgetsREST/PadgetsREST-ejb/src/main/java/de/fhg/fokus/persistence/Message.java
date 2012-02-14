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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "message")
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByTitle", query = "SELECT m FROM Message m WHERE m.title = :title"),
    @NamedQuery(name = "Message.findByLat", query = "SELECT m FROM Message m WHERE m.lat = :lat"),
    @NamedQuery(name = "Message.findByLng", query = "SELECT m FROM Message m WHERE m.lng = :lng"),
    @NamedQuery(name = "Message.findByPermalink", query = "SELECT m FROM Message m WHERE m.permalink = :permalink"),
    @NamedQuery(name = "Message.findByCreateTime", query = "SELECT m FROM Message m WHERE m.createTime = :createTime"),
    @NamedQuery(name = "Message.findByIsPublisehd", query = "SELECT m FROM Message m WHERE m.isPublisehd = :isPublisehd"),
    @NamedQuery(name = "Message.findByMediaPath", query = "SELECT m FROM Message m WHERE m.mediaPath = :mediaPath"),
    @NamedQuery(name = "Message.findByType", query = "SELECT m FROM Message m WHERE m.type = :type")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMessage")
    private Integer idMessage;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;
    @Size(max = 255)
    @Column(name = "permalink")
    private String permalink;
    @Column(name = "createTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPublisehd")
    private boolean isPublisehd;
    @Size(max = 255)
    @Column(name = "mediaPath")
    private String mediaPath;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "idUserData", referencedColumnName = "idUserData")
    @ManyToOne(optional = false)
    private Userdata idUserData;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;
    @OneToMany(mappedBy = "idMessage")
    private List<Publisheditem> publisheditemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMessage")
    private List<Comment> commentList;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Message(Integer idMessage, boolean isPublisehd) {
        this.idMessage = idMessage;
        this.isPublisehd = isPublisehd;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getIsPublisehd() {
        return isPublisehd;
    }

    public void setIsPublisehd(boolean isPublisehd) {
        this.isPublisehd = isPublisehd;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        @XmlElementWrapper(name = "publisheditems")
    @XmlElement(name = "publisheditem")
    public List<Publisheditem> getPublisheditemList() {
        return publisheditemList;
    }

    public void setPublisheditemList(List<Publisheditem> publisheditemList) {
        this.publisheditemList = publisheditemList;
    }

    @JsonIgnore
    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Message[ idMessage=" + idMessage + " ]";
    }
}
