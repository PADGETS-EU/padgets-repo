/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import play.db.jpa.Model;
import rest.conf.MapperSingelton;
import rest.dto.PublishChannelDTO;

@Entity
public class PublishChannel extends Model {
	public long pchid;
	public long accountId;
	public long userId;
	// public long campaignId;
	public String name;
	public String network;
	public String networkPageId;

	// ===========================
	public String oAuth2Token;

	@Override
	public String toString() {
		return "UserId: " + userId + ", Id: " + pchid + ", Name: " + name;
	}

	/**
	 * @param channelId
	 * @return
	 */
	public static PublishChannel findByChannelId(long channelId) {
		return PublishChannel.find("pchid", channelId).first();
	}

	public static List<PublishChannel> findByUserId(long userId) {
		return PublishChannel.find("userId", userId).fetch();
	}

	/**
	 * @param networkPageId
	 * @return
	 */
	public static PublishChannel findByNetworkPageId(String networkPageId) {
		return PublishChannel.find("networkPageId", networkPageId).first();
	}

	/**
	 * @param userId2
	 * @param network2
	 * @return
	 */
	public static List<PublishChannel> findByUserIdAndNetwork(long userId, String network) {
		// TODO change it to findByUserIdAndAccountId, what happens if we allow
		// more accounts per social network?
		return PublishChannel.find("byUseridAndNetwork", userId, network).fetch();
	}

	/**
	 * @return
	 */
	public PublishChannelDTO getPageDTO() {
		return MapperSingelton.getInstance().map(this, PublishChannelDTO.class);
	}

	/**
	 * @param cid
	 * @param string
	 * @return
	 */
	public static List<PublishChannel> findByCampaignIdAndNetwork(Long campaignId, String network) {
		List<PublishChannel> channels = new ArrayList<PublishChannel>();
		Set<Long> ids = Campaign.findByCampaignId(campaignId).channels;
		for (Long id : ids) {
			channels.add(PublishChannel.findByChannelId(id));
		}
		return channels;
	}

	public static List<PublishChannel> findByNetwork(String network) {
		return PublishChannel.find("network", network).fetch();
		
	}
}