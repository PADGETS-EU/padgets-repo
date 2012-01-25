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
@Table(name = "openidprovider")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Openidprovider.findAll", query = "SELECT o FROM Openidprovider o"),
    @NamedQuery(name = "Openidprovider.findByIdOpenIdProvider", query = "SELECT o FROM Openidprovider o WHERE o.idOpenIdProvider = :idOpenIdProvider"),
    @NamedQuery(name = "Openidprovider.findByDicoveryUrl", query = "SELECT o FROM Openidprovider o WHERE o.dicoveryUrl = :dicoveryUrl"),
    @NamedQuery(name = "Openidprovider.findByName", query = "SELECT o FROM Openidprovider o WHERE o.name = :name")})
public class Openidprovider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOpenIdProvider")
    private Integer idOpenIdProvider;
    @Size(max = 255)
    @Column(name = "dicoveryUrl")
    private String dicoveryUrl;
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    public Openidprovider() {
    }

    public Openidprovider(Integer idOpenIdProvider) {
        this.idOpenIdProvider = idOpenIdProvider;
    }

    public Integer getIdOpenIdProvider() {
        return idOpenIdProvider;
    }

    public void setIdOpenIdProvider(Integer idOpenIdProvider) {
        this.idOpenIdProvider = idOpenIdProvider;
    }

    public String getDicoveryUrl() {
        return dicoveryUrl;
    }

    public void setDicoveryUrl(String dicoveryUrl) {
        this.dicoveryUrl = dicoveryUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpenIdProvider != null ? idOpenIdProvider.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Openidprovider)) {
            return false;
        }
        Openidprovider other = (Openidprovider) object;
        if ((this.idOpenIdProvider == null && other.idOpenIdProvider != null) || (this.idOpenIdProvider != null && !this.idOpenIdProvider.equals(other.idOpenIdProvider))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Openidprovider[ idOpenIdProvider=" + idOpenIdProvider + " ]";
    }
    
}
