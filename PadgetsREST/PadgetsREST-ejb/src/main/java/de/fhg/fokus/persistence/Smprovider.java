/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Hannes Gorges
 */
@Entity
@Table(name = "smprovider")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Smprovider.findAll", query = "SELECT s FROM Smprovider s"),
    @NamedQuery(name = "Smprovider.findByIdSmProvider", query = "SELECT s FROM Smprovider s WHERE s.idSmProvider = :idSmProvider"),
    @NamedQuery(name = "Smprovider.findByDtype", query = "SELECT s FROM Smprovider s WHERE s.dtype = :dtype"),
    @NamedQuery(name = "Smprovider.findByAccessTokenUrl", query = "SELECT s FROM Smprovider s WHERE s.accessTokenUrl = :accessTokenUrl"),
    @NamedQuery(name = "Smprovider.findByAccessTokenUrl2", query = "SELECT s FROM Smprovider s WHERE s.accessTokenUrl2 = :accessTokenUrl2"),
    @NamedQuery(name = "Smprovider.findByAuthType", query = "SELECT s FROM Smprovider s WHERE s.authType = :authType"),
    @NamedQuery(name = "Smprovider.findByAuthorizationUrl", query = "SELECT s FROM Smprovider s WHERE s.authorizationUrl = :authorizationUrl"),
    @NamedQuery(name = "Smprovider.findByAuthorizedUrl", query = "SELECT s FROM Smprovider s WHERE s.authorizedUrl = :authorizedUrl"),
    @NamedQuery(name = "Smprovider.findByClientId", query = "SELECT s FROM Smprovider s WHERE s.clientId = :clientId"),
    @NamedQuery(name = "Smprovider.findByConsumerKey", query = "SELECT s FROM Smprovider s WHERE s.consumerKey = :consumerKey"),
    @NamedQuery(name = "Smprovider.findByConsumerSecret", query = "SELECT s FROM Smprovider s WHERE s.consumerSecret = :consumerSecret"),
    @NamedQuery(name = "Smprovider.findByName", query = "SELECT s FROM Smprovider s WHERE s.name = :name"),
    @NamedQuery(name = "Smprovider.findByRequestTokenUrl", query = "SELECT s FROM Smprovider s WHERE s.requestTokenUrl = :requestTokenUrl"),
    @NamedQuery(name = "Smprovider.findByScope", query = "SELECT s FROM Smprovider s WHERE s.scope = :scope"),
    @NamedQuery(name = "Smprovider.findBySecret", query = "SELECT s FROM Smprovider s WHERE s.secret = :secret")})
public class Smprovider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSmProvider")
    private Integer idSmProvider;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "DTYPE")
    private String dtype;
    @Size(max = 255)
    @Column(name = "accessTokenUrl")
    private String accessTokenUrl;
    @Size(max = 235)
    @Column(name = "accessTokenUrl2")
    private String accessTokenUrl2;
    @Size(max = 255)
    @Column(name = "authType")
    private String authType;
    @Size(max = 255)
    @Column(name = "authorizationUrl")
    private String authorizationUrl;
    @Size(max = 255)
    @Column(name = "authorizedUrl")
    private String authorizedUrl;
    @Size(max = 255)
    @Column(name = "clientId")
    private String clientId;
    @Size(max = 255)
    @Column(name = "consumerKey")
    private String consumerKey;
    @Size(max = 255)
    @Column(name = "consumerSecret")
    private String consumerSecret;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "requestTokenUrl")
    private String requestTokenUrl;
    @Size(max = 255)
    @Column(name = "scope")
    private String scope;
    @Size(max = 255)
    @Column(name = "secret")
    private String secret;

    public Smprovider() {
    }

    public Smprovider(Integer idSmProvider) {
        this.idSmProvider = idSmProvider;
    }

    public Smprovider(Integer idSmProvider, String dtype) {
        this.idSmProvider = idSmProvider;
        this.dtype = dtype;
    }

    public Integer getIdSmProvider() {
        return idSmProvider;
    }

    public void setIdSmProvider(Integer idSmProvider) {
        this.idSmProvider = idSmProvider;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getAccessTokenUrl2() {
        return accessTokenUrl2;
    }

    public void setAccessTokenUrl2(String accessTokenUrl2) {
        this.accessTokenUrl2 = accessTokenUrl2;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getAuthorizedUrl() {
        return authorizedUrl;
    }

    public void setAuthorizedUrl(String authorizedUrl) {
        this.authorizedUrl = authorizedUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestTokenUrl() {
        return requestTokenUrl;
    }

    public void setRequestTokenUrl(String requestTokenUrl) {
        this.requestTokenUrl = requestTokenUrl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSmProvider != null ? idSmProvider.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Smprovider)) {
            return false;
        }
        Smprovider other = (Smprovider) object;
        if ((this.idSmProvider == null && other.idSmProvider != null) || (this.idSmProvider != null && !this.idSmProvider.equals(other.idSmProvider))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Smprovider[ idSmProvider=" + idSmProvider + " ]";
    }
    
}
