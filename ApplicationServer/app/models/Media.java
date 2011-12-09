package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import play.db.jpa.Model;
import rest.conf.MapperSingelton;
import rest.dto.MediaDTO;
import utils.Padgets;

@Entity
public class Media extends Model {
	public long mid;
	public long campaignId;

	// public Sender sender;

	// public String campaignTitle;
	public String type;
	public String url;
	@Column(columnDefinition = "TEXT")
	public String title;
	// public TargetSocialMediaPlatforms targetsmp;

	public boolean isPublished;
	public long timestamp;
	public String permalink;

	// ==========================
	@Transient
	public Set<Comment> comments;
	public String path;

	/**
	 * @param campaignId
	 * @return
	 */
	public static List<Media> findByCampaignId(long campaignId) {
		return Media.find("campaignId", campaignId).fetch();
	}

	/**
	 * @return
	 */
	public MediaDTO getDTO() {
		return MapperSingelton.getInstance().map(this, MediaDTO.class);
	}

	/**
	 * @param mediaId
	 * @return
	 */
	public static Media findByMediaId(long mediaId) {
		return Media.find("mid", mediaId).first();
	}

	/**
	 * @param md5
	 * @return
	 */
	public static String createUrl(String md5) {
		return Padgets.url + "public/images/" + md5.substring(0, 1) + "/" + md5.substring(1, 2)
				+ "/" + md5;
	}
}
