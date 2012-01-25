/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "action")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a"),
    @NamedQuery(name = "Action.findByIdAction", query = "SELECT a FROM Action a WHERE a.idAction = :idAction"),
    @NamedQuery(name = "Action.findByActivityTime", query = "SELECT a FROM Action a WHERE a.activityTime = :activityTime"),
    @NamedQuery(name = "Action.findByWho", query = "SELECT a FROM Action a WHERE a.who = :who")})
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAction")
    private Integer idAction;
    @Column(name = "activityTime")
    @Temporal(TemporalType.DATE)
    private Date activityTime;
    @Size(max = 255)
    @Column(name = "who")
    private String who;
    @Lob
    @Size(max = 65535)
    @Column(name = "action")
    private String action;
    @JoinColumn(name = "idPlatform", referencedColumnName = "idPlatform")
    @ManyToOne(optional = false)
    private Platform idPlatform;
    @JoinColumn(name = "idActivity", referencedColumnName = "idActivity")
    @ManyToOne(optional = false)
    private Activity idActivity;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Action() {
    }

    public Action(Integer idAction) {
        this.idAction = idAction;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Platform getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Platform idPlatform) {
        this.idPlatform = idPlatform;
    }

    public Activity getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Activity idActivity) {
        this.idActivity = idActivity;
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
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Action[ idAction=" + idAction + " ]";
    }
    
}
