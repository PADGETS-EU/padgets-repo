/**
 * 
 */
package components;

import java.util.List;

import models.Campaign;
import models.Media;
import models.Message;
import models.PublishChannel;
import models.PublishedItem;
import models.SMProvider;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
public class Publisher {

	public static void publish(Campaign campaign, Message message) {
		// get channel
		PublishChannel channel;
		List<PublishChannel> channels;
		
		for (Long channelId : campaign.channels) {
			channel = PublishChannel.findByChannelId(channelId);
			SMProvider provider = SMProvider.findByName(channel.network);
			
			if ("blogger".equals(channel.network)) {
				PublishedItem item = provider.createMessage(channel, message);
				channels = PublishChannel.findByCampaignIdAndNetwork(campaign.cid, "twitter");
				for (PublishChannel twitterChannel : channels) {
					provider = SMProvider.findByName("twitter");
					provider.createMessage(twitterChannel, message, item);
				}
			}
			if ("facebook".equals(channel.network)) {
				PublishedItem item = provider.createMessage(channel, message);
				channels = PublishChannel.findByCampaignIdAndNetwork(campaign.cid, "twitter");
				for (PublishChannel twitterChannel : channels) {
					provider = SMProvider.findByName("twitter");
					provider.createMessage(twitterChannel, message, item);
				}
			}
		}
	}

	public static void update(Campaign campaign, Message message) {
		// get channel
		PublishChannel channel;
		for (Long channelId : campaign.channels) {
			channel = PublishChannel.findByChannelId(channelId);
			SMProvider provider = SMProvider.findByName(channel.network);
			provider.updateMessage(channel, message);

		}
	}
	
	public static void publish(Campaign campaign, Media media) {
		// get channel
		PublishChannel channel;
		List<PublishChannel> channels;
		
		for (Long channelId : campaign.channels) {
			channel = PublishChannel.findByChannelId(channelId);
			SMProvider provider = SMProvider.findByName(channel.network);
			

			if ("facebook".equals(channel.network)) {
				PublishedItem item = provider.createMedia(channel, media);
//				channels = PublishChannel.findByCampaignIdAndNetwork(campaign.cid, "twitter");
//				for (PublishChannel twitterChannel : channels) {
//					provider = SMProvider.findByName("twitter");
//					provider.createMessage(twitterChannel, media, item);
//				}
			}
		}
	}

}
