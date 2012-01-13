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
@Table(name = "activites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activites.findAll", query = "SELECT a FROM Activites a"),
    @NamedQuery(name = "Activites.findByIdActivites", query = "SELECT a FROM Activites a WHERE a.idActivites = :idActivites"),
    @NamedQuery(name = "Activites.findByWhen", query = "SELECT a FROM Activites a WHERE a.when = :when"),
    @NamedQuery(name = "Activites.findByWho", query = "SELECT a FROM Activites a WHERE a.who = :who"),
    @NamedQuery(name = "Activites.findByPlattform", query = "SELECT a FROM Activites a WHERE a.plattform = :plattform")})
public class Activites implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idActivites")
    private Integer idActivites;
    @Size(max = 255)
    @Column(name = "when")
    private String when;
    @Size(max = 255)
    @Column(name = "who")
    private String who;
    @Lob
    @Size(max = 65535)
    @Column(name = "what")
    private String what;
    @Size(max = 255)
    @Column(name = "plattform")
    private String plattform;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Activites() {
    }

    public Activites(Integer idActivites) {
        this.idActivites = idActivites;
    }

    public Integer getIdActivites() {
        return idActivites;
    }

    public void setIdActivites(Integer idActivites) {
        this.idActivites = idActivites;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getPlattform() {
        return plattform;
    }

    public void setPlattform(String plattform) {
        this.plattform = plattform;
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
        hash += (idActivites != null ? idActivites.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activites)) {
            return false;
        }
        Activites other = (Activites) object;
        if ((this.idActivites == null && other.idActivites != null) || (this.idActivites != null && !this.idActivites.equals(other.idActivites))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Activites[ idActivites=" + idActivites + " ]";
    }
    
}
