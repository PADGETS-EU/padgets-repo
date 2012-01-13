/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "messagelinks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messagelinks.findAll", query = "SELECT m FROM Messagelinks m"),
    @NamedQuery(name = "Messagelinks.findByIdMessageLinks", query = "SELECT m FROM Messagelinks m WHERE m.idMessageLinks = :idMessageLinks"),
    @NamedQuery(name = "Messagelinks.findByLink", query = "SELECT m FROM Messagelinks m WHERE m.link = :link")})
public class Messagelinks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMessageLinks")
    private Integer idMessageLinks;
    @Size(max = 255)
    @Column(name = "link")
    private String link;
    @JoinColumn(name = "Message_idMessage", referencedColumnName = "idMessage")
    @ManyToOne(optional = false)
    private Message messageidMessage;

    public Messagelinks() {
    }

    public Messagelinks(Integer idMessageLinks) {
        this.idMessageLinks = idMessageLinks;
    }

    public Integer getIdMessageLinks() {
        return idMessageLinks;
    }

    public void setIdMessageLinks(Integer idMessageLinks) {
        this.idMessageLinks = idMessageLinks;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Message getMessageidMessage() {
        return messageidMessage;
    }

    public void setMessageidMessage(Message messageidMessage) {
        this.messageidMessage = messageidMessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMessageLinks != null ? idMessageLinks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagelinks)) {
            return false;
        }
        Messagelinks other = (Messagelinks) object;
        if ((this.idMessageLinks == null && other.idMessageLinks != null) || (this.idMessageLinks != null && !this.idMessageLinks.equals(other.idMessageLinks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Messagelinks[ idMessageLinks=" + idMessageLinks + " ]";
    }
    
}
