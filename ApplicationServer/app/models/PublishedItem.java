package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class PublishedItem extends Model {
    public long pid;
    public long channelId;
    public long messageId;
    
    public String networkPostId;
    public String url;
	public String permalink;
	/**
	 * @param pchid
	 * @param mid
	 * @return
	 */
	public static PublishedItem findByChannelIdAndMessageId(long pchid, long mid) {
		return PublishedItem.find("byChannelidAndMessageid", pchid, mid).first();
	}
	public static PublishedItem findByNetworkPostId(String networkPostId) {
		return PublishedItem.find("networkPostId", networkPostId).first();
	}
}
