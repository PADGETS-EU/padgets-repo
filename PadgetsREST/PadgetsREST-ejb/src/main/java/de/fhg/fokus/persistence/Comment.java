/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByIdComment", query = "SELECT c FROM Comment c WHERE c.idComment = :idComment"),
    @NamedQuery(name = "Comment.findByAnnotation", query = "SELECT c FROM Comment c WHERE c.annotation = :annotation"),
    @NamedQuery(name = "Comment.findByNetwork", query = "SELECT c FROM Comment c WHERE c.network = :network"),
    @NamedQuery(name = "Comment.findByNetworkCommentId", query = "SELECT c FROM Comment c WHERE c.networkCommentId = :networkCommentId"),
    @NamedQuery(name = "Comment.findByNetworkCommentUrl", query = "SELECT c FROM Comment c WHERE c.networkCommentUrl = :networkCommentUrl"),
    @NamedQuery(name = "Comment.findByTimestamp", query = "SELECT c FROM Comment c WHERE c.timestamp = :timestamp"),
    @NamedQuery(name = "Comment.findByUserProfileUrl", query = "SELECT c FROM Comment c WHERE c.userProfileUrl = :userProfileUrl")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idComment")
    private Integer idComment;
    @Column(name = "annotation")
    private Boolean annotation;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "network")
    private String network;
    @Size(max = 255)
    @Column(name = "networkCommentId")
    private String networkCommentId;
    @Size(max = 255)
    @Column(name = "networkCommentUrl")
    private String networkCommentUrl;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 255)
    @Column(name = "userProfileUrl")
    private String userProfileUrl;
    @JoinColumn(name = "idpublishedItem", referencedColumnName = "idpublishedItem")
    @ManyToOne(optional = false)
    private Publisheditem idpublishedItem;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;

    public Comment() {
    }

    public Comment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Boolean getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Boolean annotation) {
        this.annotation = annotation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNetworkCommentId() {
        return networkCommentId;
    }

    public void setNetworkCommentId(String networkCommentId) {
        this.networkCommentId = networkCommentId;
    }

    public String getNetworkCommentUrl() {
        return networkCommentUrl;
    }

    public void setNetworkCommentUrl(String networkCommentUrl) {
        this.networkCommentUrl = networkCommentUrl;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public Publisheditem getIdpublishedItem() {
        return idpublishedItem;
    }

    public void setIdpublishedItem(Publisheditem idpublishedItem) {
        this.idpublishedItem = idpublishedItem;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComment != null ? idComment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.idComment == null && other.idComment != null) || (this.idComment != null && !this.idComment.equals(other.idComment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Comment[ idComment=" + idComment + " ]";
    }
    
}
