/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "location_has_population")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationHasPopulation.findAll", query = "SELECT l FROM LocationHasPopulation l"),
    @NamedQuery(name = "LocationHasPopulation.findByIdLocationhasPopulation", query = "SELECT l FROM LocationHasPopulation l WHERE l.idLocationhasPopulation = :idLocationhasPopulation"),
    @NamedQuery(name = "LocationHasPopulation.findByPopulation", query = "SELECT l FROM LocationHasPopulation l WHERE l.population = :population")})
public class LocationHasPopulation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLocation_has_Population")
    private Integer idLocationhasPopulation;
    @Column(name = "population")
    private Integer population;
    @JoinColumn(name = "idCluster", referencedColumnName = "idCluster")
    @ManyToOne(optional = false)
    private Clustercomposition idCluster;
    @JoinColumn(name = "idLocation", referencedColumnName = "idLocation")
    @ManyToOne(optional = false)
    private Location idLocation;

    public LocationHasPopulation() {
    }

    public LocationHasPopulation(Integer idLocationhasPopulation) {
        this.idLocationhasPopulation = idLocationhasPopulation;
    }

    public Integer getIdLocationhasPopulation() {
        return idLocationhasPopulation;
    }

    public void setIdLocationhasPopulation(Integer idLocationhasPopulation) {
        this.idLocationhasPopulation = idLocationhasPopulation;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Clustercomposition getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(Clustercomposition idCluster) {
        this.idCluster = idCluster;
    }

    public Location getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Location idLocation) {
        this.idLocation = idLocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocationhasPopulation != null ? idLocationhasPopulation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationHasPopulation)) {
            return false;
        }
        LocationHasPopulation other = (LocationHasPopulation) object;
        if ((this.idLocationhasPopulation == null && other.idLocationhasPopulation != null) || (this.idLocationhasPopulation != null && !this.idLocationhasPopulation.equals(other.idLocationhasPopulation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.LocationHasPopulation[ idLocationhasPopulation=" + idLocationhasPopulation + " ]";
    }
    
}
