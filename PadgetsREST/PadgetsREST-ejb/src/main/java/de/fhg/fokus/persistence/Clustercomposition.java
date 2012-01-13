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
@Table(name = "clustercomposition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clustercomposition.findAll", query = "SELECT c FROM Clustercomposition c"),
    @NamedQuery(name = "Clustercomposition.findByIdClusterComposition", query = "SELECT c FROM Clustercomposition c WHERE c.idClusterComposition = :idClusterComposition"),
    @NamedQuery(name = "Clustercomposition.findByGender", query = "SELECT c FROM Clustercomposition c WHERE c.gender = :gender"),
    @NamedQuery(name = "Clustercomposition.findByAgeBracket", query = "SELECT c FROM Clustercomposition c WHERE c.ageBracket = :ageBracket"),
    @NamedQuery(name = "Clustercomposition.findByCluster", query = "SELECT c FROM Clustercomposition c WHERE c.cluster = :cluster")})
public class Clustercomposition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClusterComposition")
    private Integer idClusterComposition;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "ageBracket")
    private String ageBracket;
    @Size(max = 45)
    @Column(name = "cluster")
    private String cluster;

    public Clustercomposition() {
    }

    public Clustercomposition(Integer idClusterComposition) {
        this.idClusterComposition = idClusterComposition;
    }

    public Integer getIdClusterComposition() {
        return idClusterComposition;
    }

    public void setIdClusterComposition(Integer idClusterComposition) {
        this.idClusterComposition = idClusterComposition;
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

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClusterComposition != null ? idClusterComposition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clustercomposition)) {
            return false;
        }
        Clustercomposition other = (Clustercomposition) object;
        if ((this.idClusterComposition == null && other.idClusterComposition != null) || (this.idClusterComposition != null && !this.idClusterComposition.equals(other.idClusterComposition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Clustercomposition[ idClusterComposition=" + idClusterComposition + " ]";
    }
    
}
