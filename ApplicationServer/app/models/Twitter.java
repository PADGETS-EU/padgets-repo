/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.persistence.Entity;

import play.Logger;
import play.libs.OAuth.ServiceInfo;
import play.libs.OAuth.TokenPair;
import play.libs.WS;
import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import utils.Padgets;

import components.Authentication;

/**
 * 
 * @author iptv
 */
@Entity
public class Twitter extends SMProvider {

	public Twitter() {
		super();

		name = "twitter";
		authType = Authentication.OAUTH;

		requestTokenUrl = "https://api.twitter.com/oauth/request_token";
		accessTokenUrl = "https://api.twitter.com/oauth/access_token";
		authorizeUrl = "https://api.twitter.com/oauth/authorize";
		consumerKey = "ntcBccObEDNAh5thFmXpEA";
		consumerSecret = "202CTa7FpREvTWp93bJZKChDGfKDCBittwAgVnVSxs";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#requestIdentity(models.User)
	 */
	@Override
	public void getUserData(SMPAccount smpAccount) {
		twitter4j.Twitter twitter = getTwitterClient(smpAccount);
		try {
			twitter4j.User twittUser = twitter.verifyCredentials();

			// account already exists?
			SMPAccount storedAccount = SMPAccount.findByUserAndNetworkId(
					smpAccount.userId, String.valueOf(twittUser.getId()));
			if (storedAccount != null) {
				smpAccount.delete();
				smpAccount = storedAccount;
			}

			smpAccount.networkUserId = String.valueOf(twittUser.getId());
			smpAccount.name = twittUser.getName();
			smpAccount.username = twittUser.getScreenName();
			smpAccount.profileUrl = "http://twitter.com/" + smpAccount.username;
			smpAccount.save();

			Logger.debug("name: %s", smpAccount.username);

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private twitter4j.Twitter getTwitterClient(SMPAccount smpAccount) {
		AccessToken accessToken = new AccessToken(smpAccount.oAuthToken,
				smpAccount.oAuthSecret);
		Configuration conf = new ConfigurationBuilder()
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret).build();
		twitter4j.Twitter twitter = new TwitterFactory(conf)
				.getInstance(accessToken);
		return twitter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#getUserAdministratedPages(models.SMPAccount)
	 */
	@Override
	public void getUserPublishChannels(SMPAccount smpAccount) {
		PublishChannel channel = new PublishChannel();
		channel.pchid = Padgets.generateNewId();

		// is already stored?
		PublishChannel storedPage = PublishChannel
				.findByNetworkPageId(smpAccount.networkUserId);
		if (storedPage != null)
			channel = storedPage;

		channel.accountId = smpAccount.accountId;
		channel.userId = smpAccount.userId;
		channel.networkPageId = smpAccount.networkUserId;
		channel.name = smpAccount.username;
		channel.network = this.name;
		channel.save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#createMessage(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PublishedItem createMessage(PublishChannel channel, Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#updateMessage(models.PublishChannel,
	 * models.Message)
	 */
	@Override
	public void updateMessage(PublishChannel channel, Message message) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#createMessage(models.PublishChannel,
	 * models.Message, models.PublishedItem)
	 */
	@Override
	public void createMessage(PublishChannel channel, Message message,
			PublishedItem item) {
		SMPAccount account = SMPAccount.findByAccountId(channel.accountId);

		String status;
		if (message.content.length() < 99)
			status = message.content;
		else
			status = message.content.substring(0, 100);
		status += "... " + item.permalink;

		ServiceInfo serviceInfo = new ServiceInfo(requestTokenUrl,
				accessTokenUrl, authorizeUrl, consumerKey, consumerSecret);

		String url;
		try {
			url = "http://twitter.com/statuses/update.json?status="
					+ URLEncoder.encode(status, "utf-8");
			String response = WS
					.url(url)
					.oauth(serviceInfo,
							new TokenPair(account.oAuthToken,
									account.oAuthSecret)).post().getString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// twitter4j.Twitter client = getTwitterClient(account);
		//
		// StatusUpdate status = new
		// StatusUpdate("Some message... http://www.google.com");
		// try {
		// client.updateStatus(status);
		// } catch (TwitterException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	@Override
	public void refreshUserFeedback() {

		// get channels
		List<PublishChannel> channels = PublishChannel.findByNetwork(this.name);
		for (PublishChannel channel : channels) {
			SMPAccount account = SMPAccount.findByAccountId(channel.accountId);

			TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
			twitterStream.addListener(listener);
			// user() method internally creates a thread which manipulates
			// TwitterStream and calls these adequate listener methods continuously.
			twitterStream.user();
		}

	}

	static UserStreamListener listener = new UserStreamListener() {
		public void onStatus(Status status) {
			System.out.println("onStatus @" + status.getUser().getScreenName()
					+ " - " + status.getText());
		}

		public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			System.out.println("Got a status deletion notice id:"
					+ statusDeletionNotice.getStatusId());
		}

		public void onDeletionNotice(long directMessageId, long userId) {
			System.out.println("Got a direct message deletion notice id:"
					+ directMessageId);
		}

		public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			System.out.println("Got a track limitation notice:"
					+ numberOfLimitedStatuses);
		}

		public void onScrubGeo(long userId, long upToStatusId) {
			System.out.println("Got scrub_geo event userId:" + userId
					+ " upToStatusId:" + upToStatusId);
		}

		public void onFriendList(long[] friendIds) {
			System.out.print("onFriendList");
			for (long friendId : friendIds) {
				System.out.print(" " + friendId);
			}
			System.out.println();
		}

		public void onFavorite(User source, User target, Status favoritedStatus) {
			System.out.println("onFavorite source:@" + source.getScreenName()
					+ " target:@" + target.getScreenName() + " @"
					+ favoritedStatus.getUser().getScreenName() + " - "
					+ favoritedStatus.getText());
		}

		public void onUnfavorite(User source, User target,
				Status unfavoritedStatus) {
			System.out.println("onUnFavorite source:@" + source.getScreenName()
					+ " target:@" + target.getScreenName() + " @"
					+ unfavoritedStatus.getUser().getScreenName() + " - "
					+ unfavoritedStatus.getText());
		}

		public void onFollow(User source, User followedUser) {
			System.out.println("onFollow source:@" + source.getScreenName()
					+ " target:@" + followedUser.getScreenName());
		}

		public void onRetweet(User source, User target, Status retweetedStatus) {
			System.out.println("onRetweet @"
					+ retweetedStatus.getUser().getScreenName() + " - "
					+ retweetedStatus.getText());
		}

		public void onDirectMessage(DirectMessage directMessage) {
			System.out.println("onDirectMessage text:"
					+ directMessage.getText());
		}

		public void onUserListMemberAddition(User addedMember, User listOwner,
				UserList list) {
			System.out.println("onUserListMemberAddition added member:@"
					+ addedMember.getScreenName() + " listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListMemberDeletion(User deletedMember,
				User listOwner, UserList list) {
			System.out.println("onUserListMemberDeleted deleted member:@"
					+ deletedMember.getScreenName() + " listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListSubscription(User subscriber, User listOwner,
				UserList list) {
			System.out.println("onUserListSubscribed subscriber:@"
					+ subscriber.getScreenName() + " listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListUnsubscription(User subscriber, User listOwner,
				UserList list) {
			System.out.println("onUserListUnsubscribed subscriber:@"
					+ subscriber.getScreenName() + " listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListCreation(User listOwner, UserList list) {
			System.out.println("onUserListCreated  listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListUpdate(User listOwner, UserList list) {
			System.out.println("onUserListUpdated  listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserListDeletion(User listOwner, UserList list) {
			System.out.println("onUserListDestroyed  listOwner:@"
					+ listOwner.getScreenName() + " list:" + list.getName());
		}

		public void onUserProfileUpdate(User updatedUser) {
			System.out.println("onUserProfileUpdated user:@"
					+ updatedUser.getScreenName());
		}

		public void onBlock(User source, User blockedUser) {
			System.out.println("onBlock source:@" + source.getScreenName()
					+ " target:@" + blockedUser.getScreenName());
		}

		public void onUnblock(User source, User unblockedUser) {
			System.out.println("onUnblock source:@" + source.getScreenName()
					+ " target:@" + unblockedUser.getScreenName());
		}

		public void onException(Exception ex) {
			ex.printStackTrace();
			System.out.println("onException:" + ex.getMessage());
		}
	};

	@Override
	public PublishedItem createMedia(PublishChannel channel, Media media) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public String setStatus(String title, String status,String userId) {
	// try {
	// ServiceInfo serviceInfo =
	// new ServiceInfo(requestTokenUrl, accessTokenUrl, authorizeUrl,
	// consumerKey,
	// consumerSecret);
	//
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	// String url =
	// "http://twitter.com/statuses/update.json?status="
	// + URLEncoder.encode(status, "utf-8");
	// String response =
	// WS.url(url).oauth(serviceInfo,
	// padgetsUser.getTokenPair(name)).post().getString();
	//
	// return "published Message to Twitter";
	// } catch (Exception e) {
	// return "ERROR: " + e.getMessage();
	// }
	// }
	//
	// @Override
	// public String setPhoto(File file,String userId) {
	// throw new UnsupportedOperationException("Not supported yet.");
	// }
	//
	// @Override
	// public String getComments(String objectID,String userId) {
	// ServiceInfo serviceInfo =
	// new ServiceInfo(requestTokenUrl, accessTokenUrl, authorizeUrl,
	// consumerKey,
	// consumerSecret);
	//
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	// String url =
	// "http://twitter.com/statuses/user_timeline.json?include_rts=true";
	// return WS.url(url).oauth(serviceInfo,
	// padgetsUser.getTokenPair(name)).get().getString();
	// }
	//
	// @Override
	// public void requestIdentity(PadgetsUser padgetsUser) {
	// AccessToken accessToken =
	// new AccessToken(padgetsUser.getTokenPair(name).token,
	// padgetsUser.getTokenPair(name).secret);
	// Configuration conf =
	// new ConfigurationBuilder().setOAuthConsumerKey(consumerKey)
	// .setOAuthConsumerSecret(consumerSecret)
	// .build();
	// twitter4j.Twitter twitter = new
	// TwitterFactory(conf).getInstance(accessToken);
	// try {
	// twitter4j.User twittUser = twitter.verifyCredentials();
	//
	// Identity identity = Identity.findByUserId(this.name,
	// String.valueOf(twittUser.getId()));
	// if (identity == null) {
	// identity = new Identity();
	// identity.save();
	// padgetsUser.identities.add(identity);
	// padgetsUser.save();
	// }
	//
	// identity.networkUserId = String.valueOf(twittUser.getId());
	// identity.network = name;
	// identity.name = twittUser.getName();
	// identity.username = twittUser.getScreenName();
	// identity.profileUrl = "http://twitter.com/" + identity.username;
	// identity.save();
	//
	// Logger.debug("name: %s", identity.toString());
	//
	// } catch (TwitterException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// /* (non-Javadoc)
	// * @see models.smconnector.SMProvider#postAddAccount(models.Parameters)
	// */
	// @Override
	// public PadgetsUser postAddAccount(String userId, AdministratedPage page)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// /* (non-Javadoc)
	// * @see models.smconnector.SMProvider#getAccountItemsList()
	// */
	// @Override
	// public List<AdministratedPage> getAccountItemsList(String userId) {
	// // TODO Auto-generated method stub
	// return new ArrayList<AdministratedPage>();
	// }
}
