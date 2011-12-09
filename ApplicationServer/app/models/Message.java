package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;

import play.db.jpa.Model;
import rest.conf.MapperSingelton;
import rest.dto.MessageDTO;

@Entity
public class Message extends Model {
	public long mid;
	public long campaignId;

	// public Sender sender;

	public String title;
	public String campaignTitle;
	@Column(columnDefinition = "TEXT")
	public String content;
	@ElementCollection
	public List<String> links;

	public String geolocation;

	// public TargetSocialMediaPlatforms targetsmp;

	public boolean isPublished;
	public long timestamp;
	public String permalink;

	// ==========================
	@Transient
	public Set<Comment> comments;

	/**
	 * @param campaignId
	 * @return
	 */
	public static List<Message> findByCampaignId(long campaignId) {
		return Message.find("campaignId", campaignId).fetch();
	}

	/**
	 * @return
	 */
	public MessageDTO getDTO() {
		return MapperSingelton.getInstance().map(this, MessageDTO.class);
	}

	/**
	 * @param messageId
	 * @return
	 */
	public static Message findByMessageId(long messageId) {
		return Message.find("mid", messageId).first();
	}

	public static List<Comment> getComments(long messageId) {
		return Comment.findByMessageId(messageId);
	}
}
