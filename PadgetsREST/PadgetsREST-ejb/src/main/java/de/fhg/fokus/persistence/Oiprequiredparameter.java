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
@Table(name = "oiprequiredparameter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oiprequiredparameter.findAll", query = "SELECT o FROM Oiprequiredparameter o"),
    @NamedQuery(name = "Oiprequiredparameter.findByIdOipRequiredParameter", query = "SELECT o FROM Oiprequiredparameter o WHERE o.idOipRequiredParameter = :idOipRequiredParameter"),
    @NamedQuery(name = "Oiprequiredparameter.findByRequiredParameter", query = "SELECT o FROM Oiprequiredparameter o WHERE o.requiredParameter = :requiredParameter"),
    @NamedQuery(name = "Oiprequiredparameter.findByRequiredParameterKey", query = "SELECT o FROM Oiprequiredparameter o WHERE o.requiredParameterKey = :requiredParameterKey")})
public class Oiprequiredparameter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOipRequiredParameter")
    private Integer idOipRequiredParameter;
    @Size(max = 255)
    @Column(name = "requiredParameter")
    private String requiredParameter;
    @Size(max = 255)
    @Column(name = "requiredParameterKey")
    private String requiredParameterKey;
    @JoinColumn(name = "idOpenIdProvider", referencedColumnName = "idOpenIdProvider")
    @ManyToOne(optional = false)
    private Openidprovider idOpenIdProvider;

    public Oiprequiredparameter() {
    }

    public Oiprequiredparameter(Integer idOipRequiredParameter) {
        this.idOipRequiredParameter = idOipRequiredParameter;
    }

    public Integer getIdOipRequiredParameter() {
        return idOipRequiredParameter;
    }

    public void setIdOipRequiredParameter(Integer idOipRequiredParameter) {
        this.idOipRequiredParameter = idOipRequiredParameter;
    }

    public String getRequiredParameter() {
        return requiredParameter;
    }

    public void setRequiredParameter(String requiredParameter) {
        this.requiredParameter = requiredParameter;
    }

    public String getRequiredParameterKey() {
        return requiredParameterKey;
    }

    public void setRequiredParameterKey(String requiredParameterKey) {
        this.requiredParameterKey = requiredParameterKey;
    }

    public Openidprovider getIdOpenIdProvider() {
        return idOpenIdProvider;
    }

    public void setIdOpenIdProvider(Openidprovider idOpenIdProvider) {
        this.idOpenIdProvider = idOpenIdProvider;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOipRequiredParameter != null ? idOipRequiredParameter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oiprequiredparameter)) {
            return false;
        }
        Oiprequiredparameter other = (Oiprequiredparameter) object;
        if ((this.idOipRequiredParameter == null && other.idOipRequiredParameter != null) || (this.idOipRequiredParameter != null && !this.idOipRequiredParameter.equals(other.idOipRequiredParameter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Oiprequiredparameter[ idOipRequiredParameter=" + idOipRequiredParameter + " ]";
    }
    
}
