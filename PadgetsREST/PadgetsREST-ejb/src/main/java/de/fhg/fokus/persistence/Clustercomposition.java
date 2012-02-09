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
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "clustercomposition")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Clustercomposition.findAll", query = "SELECT c FROM Clustercomposition c"),
    @NamedQuery(name = "Clustercomposition.findByIdCluster", query = "SELECT c FROM Clustercomposition c WHERE c.idCluster = :idCluster"),
    @NamedQuery(name = "Clustercomposition.findByGender", query = "SELECT c FROM Clustercomposition c WHERE c.gender = :gender"),
    @NamedQuery(name = "Clustercomposition.findByAgeBracket", query = "SELECT c FROM Clustercomposition c WHERE c.ageBracket = :ageBracket")})
public class Clustercomposition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCluster")
    private Integer idCluster;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "ageBracket")
    private String ageBracket;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCluster")
    private List<Facebookdata> facebookdataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCluster")
    private List<LocationHasPopulation> locationHasPopulationList;

    public Clustercomposition() {
    }

    public Clustercomposition(Integer idCluster) {
        this.idCluster = idCluster;
    }

    public Integer getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(Integer idCluster) {
        this.idCluster = idCluster;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(String ageBracket) {
        this.ageBracket = ageBracket;
    }

       @JsonIgnore     @XmlTransient
    public List<Facebookdata> getFacebookdataList() {
        return facebookdataList;
    }

    public void setFacebookdataList(List<Facebookdata> facebookdataList) {
        this.facebookdataList = facebookdataList;
    }

       @JsonIgnore     @XmlTransient
    public List<LocationHasPopulation> getLocationHasPopulationList() {
        return locationHasPopulationList;
    }

    public void setLocationHasPopulationList(List<LocationHasPopulation> locationHasPopulationList) {
        this.locationHasPopulationList = locationHasPopulationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCluster != null ? idCluster.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Clustercomposition)) {
            return false;
        }
        Clustercomposition other = (Clustercomposition) object;
        if ((this.idCluster == null && other.idCluster != null) || (this.idCluster != null && !this.idCluster.equals(other.idCluster))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Clustercomposition[ idCluster=" + idCluster + " ]";
    }
    
}
