/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "answerer")
@XmlAccessorType(XmlAccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "Answerer.findAll", query = "SELECT a FROM Answerer a"),
    @NamedQuery(name = "Answerer.findByIdAnswerer", query = "SELECT a FROM Answerer a WHERE a.idAnswerer = :idAnswerer"),
    @NamedQuery(name = "Answerer.findByName", query = "SELECT a FROM Answerer a WHERE a.name = :name"),
    @NamedQuery(name = "Answerer.findBySex", query = "SELECT a FROM Answerer a WHERE a.sex = :sex"),
    @NamedQuery(name = "Answerer.findByAge", query = "SELECT a FROM Answerer a WHERE a.age = :age"),
    @NamedQuery(name = "Answerer.findByRegion", query = "SELECT a FROM Answerer a WHERE a.region = :region"),
    @NamedQuery(name = "Answerer.findBySocialNetwork", query = "SELECT a FROM Answerer a WHERE a.socialNetwork = :socialNetwork"),
    @NamedQuery(name = "Answerer.findByExternalId", query = "SELECT a FROM Answerer a WHERE a.externalId = :externalId")})
public class Answerer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAnswerer")
    private Integer idAnswerer;
    @Size(max = 255)
    @Column(name = "Name")
    private String name;
    @Column(name = "Sex")
    private Boolean sex;
    @Size(max = 45)
    @Column(name = "Age")
    private String age;
    @Size(max = 255)
    @Column(name = "Region")
    private String region;
    @Size(max = 255)
    @Column(name = "SocialNetwork")
    private String socialNetwork;
    @Size(max = 45)
    @Column(name = "ExternalId")
    private String externalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answereridAnswerer")
    private List<AnswererAnsweredAnswer> answererAnsweredAnswerList;

    public Answerer() {
    }

    public Answerer(Integer idAnswerer) {
        this.idAnswerer = idAnswerer;
    }

    public Integer getIdAnswerer() {
        return idAnswerer;
    }

    public void setIdAnswerer(Integer idAnswerer) {
        this.idAnswerer = idAnswerer;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @XmlElementWrapper(name = "Votes")
    @XmlElement(name = "Vote")
    public List<AnswererAnsweredAnswer> getAnswererAnsweredAnswerList() {
        return answererAnsweredAnswerList;
    }

    public void setAnswererAnsweredAnswerList(List<AnswererAnsweredAnswer> answererAnsweredAnswerList) {
        this.answererAnsweredAnswerList = answererAnsweredAnswerList;
    }
    
        public boolean addAnswererAnsweredAnswer(AnswererAnsweredAnswer q) {
        return this.answererAnsweredAnswerList.add(q);
    }

    public boolean removeAnswererAnsweredAnswer(AnswererAnsweredAnswer q) {
        return this.answererAnsweredAnswerList.remove(q);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnswerer != null ? idAnswerer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answerer)) {
            return false;
        }
        Answerer other = (Answerer) object;
        if ((this.idAnswerer == null && other.idAnswerer != null) || (this.idAnswerer != null && !this.idAnswerer.equals(other.idAnswerer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Answerer[ idAnswerer=" + idAnswerer + " ]";
    }
    
}
