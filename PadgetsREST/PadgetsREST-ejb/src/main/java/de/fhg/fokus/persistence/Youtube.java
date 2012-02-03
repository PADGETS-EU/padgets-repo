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
import javax.xml.bind.annotation.XmlRootElement; import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author hgo
 */
@Entity
@Table(name = "youtube")
@XmlRootElement  @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@NamedQueries({
    @NamedQuery(name = "Youtube.findAll", query = "SELECT y FROM Youtube y"),
    @NamedQuery(name = "Youtube.findByIdYouTube", query = "SELECT y FROM Youtube y WHERE y.idYouTube = :idYouTube"),
    @NamedQuery(name = "Youtube.findByMetricDate", query = "SELECT y FROM Youtube y WHERE y.metricDate = :metricDate"),
    @NamedQuery(name = "Youtube.findByVideoId", query = "SELECT y FROM Youtube y WHERE y.videoId = :videoId"),
    @NamedQuery(name = "Youtube.findByViewCounts", query = "SELECT y FROM Youtube y WHERE y.viewCounts = :viewCounts"),
    @NamedQuery(name = "Youtube.findByFavCount", query = "SELECT y FROM Youtube y WHERE y.favCount = :favCount"),
    @NamedQuery(name = "Youtube.findByLikes", query = "SELECT y FROM Youtube y WHERE y.likes = :likes"),
    @NamedQuery(name = "Youtube.findByDislikes", query = "SELECT y FROM Youtube y WHERE y.dislikes = :dislikes")})
public class Youtube implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idYouTube")
    private Integer idYouTube;
    @Column(name = "metricDate")
    @Temporal(TemporalType.DATE)
    private Date metricDate;
    @Size(max = 45)
    @Column(name = "videoId")
    private String videoId;
    @Column(name = "viewCounts")
    private Integer viewCounts;
    @Column(name = "favCount")
    private Integer favCount;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "dislikes")
    private Integer dislikes;
    @JoinColumn(name = "idCampaign", referencedColumnName = "idCampaign")
    @ManyToOne(optional = false)
    private Campaign idCampaign;

    public Youtube() {
    }

    public Youtube(Integer idYouTube) {
        this.idYouTube = idYouTube;
    }

    public Integer getIdYouTube() {
        return idYouTube;
    }

    public void setIdYouTube(Integer idYouTube) {
        this.idYouTube = idYouTube;
    }

    public Date getMetricDate() {
        return metricDate;
    }

    public void setMetricDate(Date metricDate) {
        this.metricDate = metricDate;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Integer getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(Integer viewCounts) {
        this.viewCounts = viewCounts;
    }

    public Integer getFavCount() {
        return favCount;
    }

    public void setFavCount(Integer favCount) {
        this.favCount = favCount;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
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
        hash += (idYouTube != null ? idYouTube.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Youtube)) {
            return false;
        }
        Youtube other = (Youtube) object;
        if ((this.idYouTube == null && other.idYouTube != null) || (this.idYouTube != null && !this.idYouTube.equals(other.idYouTube))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.fhg.fokus.persistence.Youtube[ idYouTube=" + idYouTube + " ]";
    }
    
}
