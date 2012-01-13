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
@Table(name = "media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Media.findAll", query = "SELECT m FROM Media m"),
    @NamedQuery(name = "Media.findByIdMedia", query = "SELECT m FROM Media m WHERE m.idMedia = :idMedia"),
    @NamedQuery(name = "Media.findByPath", query = "SELECT m FROM Media m WHERE m.path = :path"),
    @NamedQuery(name = "Media.findByPermalink", query = "SELECT m FROM Media m WHERE m.permalink = :permalink"),
    @NamedQuery(name = "Media.findByTimestamp", query = "SELECT m FROM Media m WHERE m.timestamp = :timestamp"),
    @NamedQuery(name = "Media.findByTitle", query = "SELECT m FROM Media m WHERE m.title = :title"),
    @NamedQuery(name = "Media.findByType", query = "SELECT m FROM Media m WHERE m.type = :type"),
    @NamedQuery(name = "Media.findByUrl", query = "SELECT m FROM Media m WHERE m.url = :url"),
    @NamedQuery(name = "Media.findByIsPublished", query = "SELECT m FROM Media m WHERE m.isPublished = :isPublished")})
public class Media implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedia")
    private Integer idMedia;
    @Size(max = 255)
    @Column(name = "path")
    private String path;
    @Size(max = 255)
    @Column(name = "permalink")
    private String permalink;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isPublished")
    private boolean isPublished;
    @ManyToMany(mappedBy = "mediaList")
    private List<Message> messageList;

    public Media() {
    }

    public Media(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public Media(Integer idMedia, boolean isPublished) {
        this.idMedia = idMedia;
        this.isPublished = isPublished;
    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    @XmlTransient
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedia != null ? idMedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.idMedia == null && other.idMedia != null) || (this.idMedia != null && !this.idMedia.equals(other.idMedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Media[ idMedia=" + idMedia + " ]";
    }
    
}
