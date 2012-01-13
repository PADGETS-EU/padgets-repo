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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByIdLocation", query = "SELECT l FROM Location l WHERE l.idLocation = :idLocation"),
    @NamedQuery(name = "Location.findByName", query = "SELECT l FROM Location l WHERE l.name = :name"),
    @NamedQuery(name = "Location.findByCluster1", query = "SELECT l FROM Location l WHERE l.cluster1 = :cluster1"),
    @NamedQuery(name = "Location.findByCluster2", query = "SELECT l FROM Location l WHERE l.cluster2 = :cluster2"),
    @NamedQuery(name = "Location.findByCluster3", query = "SELECT l FROM Location l WHERE l.cluster3 = :cluster3"),
    @NamedQuery(name = "Location.findByCluster4", query = "SELECT l FROM Location l WHERE l.cluster4 = :cluster4"),
    @NamedQuery(name = "Location.findByCluster5", query = "SELECT l FROM Location l WHERE l.cluster5 = :cluster5"),
    @NamedQuery(name = "Location.findByCluster6", query = "SELECT l FROM Location l WHERE l.cluster6 = :cluster6"),
    @NamedQuery(name = "Location.findByCluster7", query = "SELECT l FROM Location l WHERE l.cluster7 = :cluster7"),
    @NamedQuery(name = "Location.findByCluster8", query = "SELECT l FROM Location l WHERE l.cluster8 = :cluster8"),
    @NamedQuery(name = "Location.findByCluster9", query = "SELECT l FROM Location l WHERE l.cluster9 = :cluster9"),
    @NamedQuery(name = "Location.findByCluster10", query = "SELECT l FROM Location l WHERE l.cluster10 = :cluster10"),
    @NamedQuery(name = "Location.findByCluster11", query = "SELECT l FROM Location l WHERE l.cluster11 = :cluster11"),
    @NamedQuery(name = "Location.findByCluster12", query = "SELECT l FROM Location l WHERE l.cluster12 = :cluster12")})
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLocation")
    private Integer idLocation;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "cluster1")
    private Integer cluster1;
    @Column(name = "cluster2")
    private Integer cluster2;
    @Column(name = "cluster3")
    private Integer cluster3;
    @Column(name = "cluster4")
    private Integer cluster4;
    @Column(name = "cluster5")
    private Integer cluster5;
    @Column(name = "cluster6")
    private Integer cluster6;
    @Column(name = "cluster7")
    private Integer cluster7;
    @Column(name = "cluster8")
    private Integer cluster8;
    @Column(name = "cluster9")
    private Integer cluster9;
    @Column(name = "cluster10")
    private Integer cluster10;
    @Column(name = "cluster11")
    private Integer cluster11;
    @Column(name = "cluster12")
    private Integer cluster12;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLacotion")
    private List<Campaign> campaignList;

    public Location() {
    }

    public Location(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCluster1() {
        return cluster1;
    }

    public void setCluster1(Integer cluster1) {
        this.cluster1 = cluster1;
    }

    public Integer getCluster2() {
        return cluster2;
    }

    public void setCluster2(Integer cluster2) {
        this.cluster2 = cluster2;
    }

    public Integer getCluster3() {
        return cluster3;
    }

    public void setCluster3(Integer cluster3) {
        this.cluster3 = cluster3;
    }

    public Integer getCluster4() {
        return cluster4;
    }

    public void setCluster4(Integer cluster4) {
        this.cluster4 = cluster4;
    }

    public Integer getCluster5() {
        return cluster5;
    }

    public void setCluster5(Integer cluster5) {
        this.cluster5 = cluster5;
    }

    public Integer getCluster6() {
        return cluster6;
    }

    public void setCluster6(Integer cluster6) {
        this.cluster6 = cluster6;
    }

    public Integer getCluster7() {
        return cluster7;
    }

    public void setCluster7(Integer cluster7) {
        this.cluster7 = cluster7;
    }

    public Integer getCluster8() {
        return cluster8;
    }

    public void setCluster8(Integer cluster8) {
        this.cluster8 = cluster8;
    }

    public Integer getCluster9() {
        return cluster9;
    }

    public void setCluster9(Integer cluster9) {
        this.cluster9 = cluster9;
    }

    public Integer getCluster10() {
        return cluster10;
    }

    public void setCluster10(Integer cluster10) {
        this.cluster10 = cluster10;
    }

    public Integer getCluster11() {
        return cluster11;
    }

    public void setCluster11(Integer cluster11) {
        this.cluster11 = cluster11;
    }

    public Integer getCluster12() {
        return cluster12;
    }

    public void setCluster12(Integer cluster12) {
        this.cluster12 = cluster12;
    }

    @XmlTransient
    public List<Campaign> getCampaignList() {
        return campaignList;
    }

    public void setCampaignList(List<Campaign> campaignList) {
        this.campaignList = campaignList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocation != null ? idLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.idLocation == null && other.idLocation != null) || (this.idLocation != null && !this.idLocation.equals(other.idLocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Location[ idLocation=" + idLocation + " ]";
    }
    
}
