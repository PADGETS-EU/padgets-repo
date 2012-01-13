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
@Table(name = "publisheditem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publisheditem.findAll", query = "SELECT p FROM Publisheditem p"),
    @NamedQuery(name = "Publisheditem.findByIdpublishedItem", query = "SELECT p FROM Publisheditem p WHERE p.idpublishedItem = :idpublishedItem"),
    @NamedQuery(name = "Publisheditem.findByNetworkPostId", query = "SELECT p FROM Publisheditem p WHERE p.networkPostId = :networkPostId"),
    @NamedQuery(name = "Publisheditem.findByPermalink", query = "SELECT p FROM Publisheditem p WHERE p.permalink = :permalink"),
    @NamedQuery(name = "Publisheditem.findByUrl", query = "SELECT p FROM Publisheditem p WHERE p.url = :url")})
public class Publisheditem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpublishedItem")
    private Integer idpublishedItem;
    @Size(max = 255)
    @Column(name = "networkPostId")
    private String networkPostId;
    @Size(max = 255)
    @Column(name = "permalink")
    private String permalink;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idPublishChannel", referencedColumnName = "idPublishChannel")
    @ManyToOne(optional = false)
    private Publishchannel idPublishChannel;
    @JoinColumn(name = "idMessage", referencedColumnName = "idMessage")
    @ManyToOne(optional = false)
    private Message idMessage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpublishedItem")
    private List<Comment> commentList;

    public Publisheditem() {
    }

    public Publisheditem(Integer idpublishedItem) {
        this.idpublishedItem = idpublishedItem;
    }

    public Integer getIdpublishedItem() {
        return idpublishedItem;
    }

    public void setIdpublishedItem(Integer idpublishedItem) {
        this.idpublishedItem = idpublishedItem;
    }

    public String getNetworkPostId() {
        return networkPostId;
    }

    public void setNetworkPostId(String networkPostId) {
        this.networkPostId = networkPostId;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Publishchannel getIdPublishChannel() {
        return idPublishChannel;
    }

    public void setIdPublishChannel(Publishchannel idPublishChannel) {
        this.idPublishChannel = idPublishChannel;
    }

    public Message getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Message idMessage) {
        this.idMessage = idMessage;
    }

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
        hash += (idpublishedItem != null ? idpublishedItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publisheditem)) {
            return false;
        }
        Publisheditem other = (Publisheditem) object;
        if ((this.idpublishedItem == null && other.idpublishedItem != null) || (this.idpublishedItem != null && !this.idpublishedItem.equals(other.idpublishedItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Publisheditem[ idpublishedItem=" + idpublishedItem + " ]";
    }
    
}
