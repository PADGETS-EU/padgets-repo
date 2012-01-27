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
 * @author hgo
 */
@Entity
@Table(name = "publisheditem")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
@NamedQueries({
    @NamedQuery(name = "Publisheditem.findAll", query = "SELECT p FROM Publisheditem p"),
    @NamedQuery(name = "Publisheditem.findByIdPublishedItem", query = "SELECT p FROM Publisheditem p WHERE p.idPublishedItem = :idPublishedItem"),
    @NamedQuery(name = "Publisheditem.findByNetworkPostId", query = "SELECT p FROM Publisheditem p WHERE p.networkPostId = :networkPostId"),
    @NamedQuery(name = "Publisheditem.findByPermalink", query = "SELECT p FROM Publisheditem p WHERE p.permalink = :permalink")})
public class Publisheditem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPublishedItem")
    private Integer idPublishedItem;
    @Size(max = 255)
    @Column(name = "networkPostId")
    private String networkPostId;
    @Size(max = 255)
    @Column(name = "permalink")
    private String permalink;
    @JoinColumn(name = "idMessage", referencedColumnName = "idMessage")
    @ManyToOne
    private Message idMessage;
    @JoinColumn(name = "idComment", referencedColumnName = "idComment")
    @ManyToOne
    private Comment idComment;
    @JoinColumn(name = "idPublishChannel", referencedColumnName = "idPublishChannel")
    @ManyToOne(optional = false)
    private Publishchannel idPublishChannel;

    public Publisheditem() {
    }

    public Publisheditem(Integer idPublishedItem) {
        this.idPublishedItem = idPublishedItem;
    }

    public Integer getIdPublishedItem() {
        return idPublishedItem;
    }

    public void setIdPublishedItem(Integer idPublishedItem) {
        this.idPublishedItem = idPublishedItem;
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

    public Message getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Message idMessage) {
        this.idMessage = idMessage;
    }

    public Comment getIdComment() {
        return idComment;
    }

    public void setIdComment(Comment idComment) {
        this.idComment = idComment;
    }

    public Publishchannel getIdPublishChannel() {
        return idPublishChannel;
    }

    public void setIdPublishChannel(Publishchannel idPublishChannel) {
        this.idPublishChannel = idPublishChannel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublishedItem != null ? idPublishedItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publisheditem)) {
            return false;
        }
        Publisheditem other = (Publisheditem) object;
        if ((this.idPublishedItem == null && other.idPublishedItem != null) || (this.idPublishedItem != null && !this.idPublishedItem.equals(other.idPublishedItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Publisheditem[ idPublishedItem=" + idPublishedItem + " ]";
    }
    
}
