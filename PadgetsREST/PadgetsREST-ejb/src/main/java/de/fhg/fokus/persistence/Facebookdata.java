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
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "facebookdata")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
@NamedQueries({
    @NamedQuery(name = "Facebookdata.findAll", query = "SELECT f FROM Facebookdata f"),
    @NamedQuery(name = "Facebookdata.findByIdFacebookData", query = "SELECT f FROM Facebookdata f WHERE f.idFacebookData = :idFacebookData"),
    @NamedQuery(name = "Facebookdata.findByMetricDate", query = "SELECT f FROM Facebookdata f WHERE f.metricDate = :metricDate"),
    @NamedQuery(name = "Facebookdata.findByAgeBracket", query = "SELECT f FROM Facebookdata f WHERE f.ageBracket = :ageBracket"),
    @NamedQuery(name = "Facebookdata.findByGender", query = "SELECT f FROM Facebookdata f WHERE f.gender = :gender"),
    @NamedQuery(name = "Facebookdata.findByFans", query = "SELECT f FROM Facebookdata f WHERE f.fans = :fans"),
    @NamedQuery(name = "Facebookdata.findByActiveUsers", query = "SELECT f FROM Facebookdata f WHERE f.activeUsers = :activeUsers")})
public class Facebookdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idFacebookData")
    private Integer idFacebookData;
    @Column(name = "metricDate")
    @Temporal(TemporalType.DATE)
    private Date metricDate;
    @Size(max = 45)
    @Column(name = "ageBracket")
    private String ageBracket;
    @Size(max = 5)
    @Column(name = "gender")
    private String gender;
    @Column(name = "fans")
    private Integer fans;
    @Column(name = "activeUsers")
    private Integer activeUsers;
    @JoinColumn(name = "idCluster", referencedColumnName = "idCluster")
    @ManyToOne(optional = false)
    private Clustercomposition idCluster;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Facebookdata() {
    }

    public Facebookdata(Integer idFacebookData) {
        this.idFacebookData = idFacebookData;
    }

    public Integer getIdFacebookData() {
        return idFacebookData;
    }

    public void setIdFacebookData(Integer idFacebookData) {
        this.idFacebookData = idFacebookData;
    }

    public Date getMetricDate() {
        return metricDate;
    }

    public void setMetricDate(Date metricDate) {
        this.metricDate = metricDate;
    }

    public String getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(String ageBracket) {
        this.ageBracket = ageBracket;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Clustercomposition getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(Clustercomposition idCluster) {
        this.idCluster = idCluster;
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
        hash += (idFacebookData != null ? idFacebookData.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facebookdata)) {
            return false;
        }
        Facebookdata other = (Facebookdata) object;
        if ((this.idFacebookData == null && other.idFacebookData != null) || (this.idFacebookData != null && !this.idFacebookData.equals(other.idFacebookData))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Facebookdata[ idFacebookData=" + idFacebookData + " ]";
    }
    
}
