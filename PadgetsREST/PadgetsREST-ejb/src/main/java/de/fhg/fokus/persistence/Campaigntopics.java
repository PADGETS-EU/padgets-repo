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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "campaigntopics")
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
@NamedQueries({
    @NamedQuery(name = "Campaigntopics.findAll", query = "SELECT c FROM Campaigntopics c"),
    @NamedQuery(name = "Campaigntopics.findByIdCampaignTopics", query = "SELECT c FROM Campaigntopics c WHERE c.idCampaignTopics = :idCampaignTopics"),
    @NamedQuery(name = "Campaigntopics.findByTopic", query = "SELECT c FROM Campaigntopics c WHERE c.topic = :topic")})
public class Campaigntopics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCampaignTopics")
    private Integer idCampaignTopics;
    @Size(max = 255)
    @Column(name = "topic")
    private String topic;
    @JoinColumn(name = "Campaign_idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign campaignidCampaign;

    public Campaigntopics() {
    }

    public Campaigntopics(Integer idCampaignTopics) {
        this.idCampaignTopics = idCampaignTopics;
    }

    @JsonIgnore
    @XmlTransient
    public Integer getIdCampaignTopics() {
        return idCampaignTopics;
    }

    public void setIdCampaignTopics(Integer idCampaignTopics) {
        this.idCampaignTopics = idCampaignTopics;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonIgnore
    @XmlTransient
    public Campaign getCampaignidCampaign() {
        return campaignidCampaign;
    }

    public void setCampaignidCampaign(Campaign campaignidCampaign) {
        this.campaignidCampaign = campaignidCampaign;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCampaignTopics != null ? idCampaignTopics.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Campaigntopics)) {
            return false;
        }
        Campaigntopics other = (Campaigntopics) object;
        if ((this.idCampaignTopics == null && other.idCampaignTopics != null) || (this.idCampaignTopics != null && !this.idCampaignTopics.equals(other.idCampaignTopics))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Campaigntopics[ idCampaignTopics=" + idCampaignTopics + " ]";
    }
}
