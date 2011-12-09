/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.persistence.Entity;

import play.Logger;
import play.libs.IO;
import sun.awt.image.ByteArrayImageSource;
import utils.Padgets;

import com.restfb.Connection;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Account;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import components.Authentication;

/**
 * 
 * @author Lukasz Radziwonowicz
 */
@Entity
public class Facebook extends SMProvider {

	public Facebook() {
		super();

		name = "facebook";
		authType = Authentication.OAUTH2;

		authorizationURL = "https://graph.facebook.com/oauth/authorize";
		accessTokenURL2 = "https://graph.facebook.com/oauth/access_token";
		clientid = "193498190670482";
		secret = "cf1dae525f8736dfc15fdf93c0626285";
		scope = "email,read_stream,publish_stream,offline_access,user_likes,manage_pages";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#requestIdentity(models.User)
	 */
	@Override
	public void getUserData(SMPAccount smpAccount) {
		FacebookClient facebookClient = new DefaultFacebookClient(
				smpAccount.oAuth2Token);
		com.restfb.types.User fbUser = facebookClient.fetchObject("me",
				com.restfb.types.User.class,
				Parameter.with("fields", "id, name, username, link"));

		// account already exists?
		SMPAccount storedAccount = SMPAccount.findByUserAndNetworkId(
				smpAccount.userId, fbUser.getId());
		if (storedAccount != null) {
			smpAccount.delete();
			smpAccount = storedAccount;
		}

		smpAccount.networkUserId = fbUser.getId();
		smpAccount.username = fbUser.getName();
		smpAccount.name = fbUser.getName();
		smpAccount.profileUrl = fbUser.getLink();
		smpAccount.save();

		Logger.debug("Identity username: %s", smpAccount.username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#getUserAdministratedPages(models.SMPAccount)
	 */
	@Override
	public void getUserPublishChannels(SMPAccount smpAccount) {
		FacebookClient facebookClient = new DefaultFacebookClient(
				smpAccount.oAuth2Token);
		Connection<Account> accounts = facebookClient.fetchConnection(
				"me/accounts", Account.class);
		List<Account> fbAccounts = accounts.getData();
		for (Account account : fbAccounts) {
			if ("Application".equals(account.getCategory())) // filter out
																// application
																// pages
				continue;
			// create new page
			PublishChannel channel = new PublishChannel();
			channel.pchid = Padgets.generateNewId();

			// TODO actually we should delete all old channels and store only
			// new active
			// is already stored?
			PublishChannel storedPage = PublishChannel
					.findByNetworkPageId(account.getId());
			if (storedPage != null)
				channel = storedPage;

			channel.accountId = smpAccount.accountId;
			channel.userId = smpAccount.userId;
			channel.networkPageId = account.getId();
			channel.oAuth2Token = account.getAccessToken();
			channel.name = account.getName();
			channel.network = this.name;
			channel.save();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#createMessage(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public PublishedItem createMessage(PublishChannel channel, Message message) {
		SMPAccount account = SMPAccount.findByAccountId(channel.accountId);
		FacebookClient facebookClient = new DefaultFacebookClient(
				channel.oAuth2Token);

		FacebookType publishMessageResponse = facebookClient.publish(
				channel.networkPageId + "/feed", FacebookType.class,
				Parameter.with("message", message.content));

		PublishedItem item = new PublishedItem();
		item.pid = Padgets.generateNewId();

		item.channelId = channel.pchid;
		item.messageId = message.mid;
		// id format: "accountId_postId"
		item.networkPostId = publishMessageResponse.getId();
		item.url = "https://www.facebook.com/" + channel.networkPageId
				+ "/posts/" + getOnlyPostId(item.networkPostId);
		item.permalink = item.url;
		item.save();

		return item;
	}

	private String getOnlyPostId(String accountId_postId) {
		return accountId_postId.substring(accountId_postId.indexOf("_") + 1);
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

	@Override
	public PublishedItem createMedia(PublishChannel channel, Media media) {
		SMPAccount account = SMPAccount.findByAccountId(channel.accountId);
		FacebookClient facebookClient = new DefaultFacebookClient(
				channel.oAuth2Token);

		FacebookType publishPhotoResponse = facebookClient.publish(channel.networkPageId + "/photos", FacebookType.class,
				  BinaryAttachment.with(media.title, new ByteArrayInputStream(IO.readContent(new File(media.path)))),
				  Parameter.with("message", media.title));
				
		PublishedItem item = new PublishedItem();
		item.pid = Padgets.generateNewId();

		item.channelId = channel.pchid;
		item.messageId = media.mid;
		// id format: "accountId_postId"
		item.networkPostId = publishPhotoResponse.getId();
		item.url = "https://www.facebook.com/" + channel.networkPageId
				+ "/posts/" + getOnlyPostId(item.networkPostId);
		item.permalink = item.url;
		item.save();

		return item;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#createMessage(models.PublishChannel,
	 * models.Message, models.PublishedItem)
	 */
	@Override
	public void createMessage(PublishChannel twitterChannel, Message message,
			PublishedItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshUserFeedback() {
		// get channels
		List<PublishChannel> channels = PublishChannel.findByNetwork(this.name);
		for (PublishChannel channel : channels) {
			FacebookClient facebookClient = new DefaultFacebookClient(
					channel.oAuth2Token);

			Connection<Post> postConnection = facebookClient.fetchConnection(
					"me/posts", Post.class);
			List<Post> posts = postConnection.getData();
			for (Post post : posts) {
				// is it our post
				PublishedItem item = PublishedItem.findByNetworkPostId(post
						.getId());
				if (item == null)
					continue;

				// get list of comments
				List<com.restfb.types.Comment> comments = post.getComments()
						.getData();
				for (com.restfb.types.Comment fbComment : comments) {
					// create or update feedback object
					Comment comment = Comment
							.findByNetworkNameAndNetworkCommentId(this.name,
									fbComment.getId());
					if (comment == null) {
						comment = new Comment();
						comment.ufid = Padgets.generateNewId();
					}

					comment.timestamp = fbComment.getCreatedTime().getTime();
					comment.content = fbComment.getMessage();
					comment.publishedItemId = item.pid;
					comment.messageId = item.messageId;
					// TODO more authors possible
					comment.userProfileUrl = "https://www.facebook.com/"
							+ fbComment.getFrom().getId();
					comment.network = this.name;
					comment.networkCommentId = fbComment.getId();
					comment.networkCommentUrl = "https://www.facebook.com/"
							+ channel.networkPageId + "/posts/"
							+ getOnlyPostId(post.getId());
					comment.save();
					System.out.println(comment.content + " Link: "
							+ comment.networkCommentUrl);
				}
			}
		}

	}
	// @Override
	// public String setStatus(String title, String status, String userId) {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	//
	// FacebookClient facebookClient =
	// new DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// FacebookType publishMessageResponse =
	// facebookClient.publish("me/feed", FacebookType.class,
	// Parameter.with("message", status));
	//
	// System.out.println(publishMessageResponse.getId());
	//
	// return publishMessageResponse.getId();
	// }
	//
	// @Override
	// public String getComments(String objectID, String userId) {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	// FacebookClient facebookClient =
	// new DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// Post post = facebookClient.fetchObject(objectID, Post.class);
	// return post.getComments().getData().get(0).getMessage();
	// }
	//
	// @Override
	// public String setPhoto(File file, String userId) {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	// FacebookClient client = new
	// DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// InputStream fileReader;
	// try {
	// fileReader = new FileInputStream(file);// an inputstream is needed
	// // to publish the photo with
	// // the restFB framework
	//
	// FacebookType publishMessageResponse =
	// client.publish("me/photos", FacebookType.class, fileReader,
	// Parameter.with("source", ""));
	// System.out.println(publishMessageResponse.getId());
	// return publishMessageResponse.getId();
	// } catch (IOException e) {
	// return "ERROR: " + e.getMessage();
	// }
	//
	// }
	//
	// @Override
	// public void requestIdentity(PadgetsUser padgetsUser) {
	// FacebookClient facebookClient =
	// new DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// com.restfb.types.User fbUser =
	// facebookClient.fetchObject("me", com.restfb.types.User.class,
	// Parameter.with("fields", "id, name, username, link"));
	//
	// Identity identity = Identity.findByUserId(this.name, fbUser.getId());
	// if (identity == null) {
	// identity = new Identity();
	// identity.save();
	// padgetsUser.identities.add(identity);
	// padgetsUser.save();
	// }
	// identity.network = this.name;
	// identity.networkUserId = fbUser.getId();
	// identity.username = fbUser.getName();
	// identity.name = fbUser.getName();
	// identity.profileUrl = fbUser.getLink();
	// identity.save();
	//
	// Logger.debug("Identity username: %s", identity.username);
	//
	// // identity.save();
	// //
	// // padgetsUser.identities.add(identity);
	// // padgetsUser.save();
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see models.smconnector.SMProvider#getAccountItemsList()
	// */
	// @Override
	// public List<AdministratedPage> getAccountItemsList(String userId) {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	//
	// FacebookClient facebookClient =
	// new DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// Connection<Account> accounts =
	// facebookClient.fetchConnection("me/accounts", Account.class);
	// List<Account> lAcc = accounts.getData();
	// ArrayList<AdministratedPage> list = new ArrayList<AdministratedPage>();
	// for (Account acc : lAcc) {
	// AdministratedPage i = new AdministratedPage();
	// i.pageId = acc.getId();
	// i.name = acc.getName();
	// i.pageUserId = padgetsUser.userId;
	// list.add(i);
	// }
	// return list;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see models.smconnector.SMProvider#postAddAccount(java.lang.String,
	// * models.smconnector.CampaignSMPage)
	// */
	// @Override
	// public PadgetsUser postAddAccount(String userId, AdministratedPage page)
	// {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	//
	// FacebookClient facebookClient =
	// new DefaultFacebookClient(padgetsUser.getTokenPair(name).token);
	// Connection<Account> accounts =
	// facebookClient.fetchConnection("me/accounts", Account.class);
	// List<Account> lAcc = accounts.getData();
	// for (Account acc : lAcc) {
	// if (acc.getId().equals(page.pageId)) {
	// // save fan page access token
	// for (Identity identity : padgetsUser.identities) {
	// if ("facebook".equals(identity.network)) {
	// page.name=acc.getName();
	// page.accessToken=acc.getAccessToken();
	// page.save();
	// identity.pages.add(page);
	// identity.save();
	// break;
	// }
	// }
	// padgetsUser.save();
	// }
	// }
	// return padgetsUser;
	// }
}
