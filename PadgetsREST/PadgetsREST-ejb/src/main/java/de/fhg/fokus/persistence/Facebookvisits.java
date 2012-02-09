/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "facebookvisits")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Facebookvisits.findAll", query = "SELECT f FROM Facebookvisits f"),
    @NamedQuery(name = "Facebookvisits.findByIdFacebookVisits", query = "SELECT f FROM Facebookvisits f WHERE f.idFacebookVisits = :idFacebookVisits"),
    @NamedQuery(name = "Facebookvisits.findByMetricDate", query = "SELECT f FROM Facebookvisits f WHERE f.metricDate = :metricDate"),
    @NamedQuery(name = "Facebookvisits.findByVisits", query = "SELECT f FROM Facebookvisits f WHERE f.visits = :visits")})
public class Facebookvisits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFacebookVisits")
    private Integer idFacebookVisits;
    @Column(name = "metricDate")
    @Temporal(TemporalType.DATE)
    private Date metricDate;
    @Column(name = "visits")
    private Integer visits;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Facebookvisits() {
    }

    public Facebookvisits(Integer idFacebookVisits) {
        this.idFacebookVisits = idFacebookVisits;
    }

    public Integer getIdFacebookVisits() {
        return idFacebookVisits;
    }

    public void setIdFacebookVisits(Integer idFacebookVisits) {
        this.idFacebookVisits = idFacebookVisits;
    }

    public Date getMetricDate() {
        return metricDate;
    }

    public void setMetricDate(Date metricDate) {
        this.metricDate = metricDate;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
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
        hash += (idFacebookVisits != null ? idFacebookVisits.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Facebookvisits)) {
            return false;
        }
        Facebookvisits other = (Facebookvisits) object;
        if ((this.idFacebookVisits == null && other.idFacebookVisits != null) || (this.idFacebookVisits != null && !this.idFacebookVisits.equals(other.idFacebookVisits))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Facebookvisits[ idFacebookVisits=" + idFacebookVisits + " ]";
    }
    
}
