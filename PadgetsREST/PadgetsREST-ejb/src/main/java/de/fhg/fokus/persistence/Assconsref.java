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
@Table(name = "assconsref")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assconsref.findAll", query = "SELECT a FROM Assconsref a"),
    @NamedQuery(name = "Assconsref.findByIdAssConsRef", query = "SELECT a FROM Assconsref a WHERE a.idAssConsRef = :idAssConsRef"),
    @NamedQuery(name = "Assconsref.findByAssConsRef", query = "SELECT a FROM Assconsref a WHERE a.assConsRef = :assConsRef")})
public class Assconsref implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAssConsRef")
    private Integer idAssConsRef;
    @Size(max = 255)
    @Column(name = "assConsRef")
    private String assConsRef;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Assconsref() {
    }

    public Assconsref(Integer idAssConsRef) {
        this.idAssConsRef = idAssConsRef;
    }

    public Integer getIdAssConsRef() {
        return idAssConsRef;
    }

    public void setIdAssConsRef(Integer idAssConsRef) {
        this.idAssConsRef = idAssConsRef;
    }

    public String getAssConsRef() {
        return assConsRef;
    }

    public void setAssConsRef(String assConsRef) {
        this.assConsRef = assConsRef;
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
        hash += (idAssConsRef != null ? idAssConsRef.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assconsref)) {
            return false;
        }
        Assconsref other = (Assconsref) object;
        if ((this.idAssConsRef == null && other.idAssConsRef != null) || (this.idAssConsRef != null && !this.idAssConsRef.equals(other.idAssConsRef))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Assconsref[ idAssConsRef=" + idAssConsRef + " ]";
    }
    
}
