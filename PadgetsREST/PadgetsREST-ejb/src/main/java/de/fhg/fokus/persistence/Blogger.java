/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "blogger")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogger.findAll", query = "SELECT b FROM Blogger b"),
    @NamedQuery(name = "Blogger.findByIdBlogger", query = "SELECT b FROM Blogger b WHERE b.idBlogger = :idBlogger"),
    @NamedQuery(name = "Blogger.findByMetricDate", query = "SELECT b FROM Blogger b WHERE b.metricDate = :metricDate"),
    @NamedQuery(name = "Blogger.findByVisitors", query = "SELECT b FROM Blogger b WHERE b.visitors = :visitors"),
    @NamedQuery(name = "Blogger.findByNewVisitors", query = "SELECT b FROM Blogger b WHERE b.newVisitors = :newVisitors")})
public class Blogger implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idBlogger")
    private Integer idBlogger;
    @Column(name = "metricDate")
    @Temporal(TemporalType.DATE)
    private Date metricDate;
    @Column(name = "visitors")
    private Integer visitors;
    @Column(name = "newVisitors")
    private Integer newVisitors;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Blogger() {
    }

    public Blogger(Integer idBlogger) {
        this.idBlogger = idBlogger;
    }

    public Integer getIdBlogger() {
        return idBlogger;
    }

    public void setIdBlogger(Integer idBlogger) {
        this.idBlogger = idBlogger;
    }

    public Date getMetricDate() {
        return metricDate;
    }

    public void setMetricDate(Date metricDate) {
        this.metricDate = metricDate;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public void setVisitors(Integer visitors) {
        this.visitors = visitors;
    }

    public Integer getNewVisitors() {
        return newVisitors;
    }

    public void setNewVisitors(Integer newVisitors) {
        this.newVisitors = newVisitors;
    }

    public Campaign getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Campaign idCampaign) {
        this.idCampaign = idCampaign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBlogger != null ? idBlogger.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogger)) {
            return false;
        }
        Blogger other = (Blogger) object;
        if ((this.idBlogger == null && other.idBlogger != null) || (this.idBlogger != null && !this.idBlogger.equals(other.idBlogger))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Blogger[ idBlogger=" + idBlogger + " ]";
    }
    
}
