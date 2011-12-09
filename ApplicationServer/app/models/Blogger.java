/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.persistence.Entity;

import play.Logger;
import utils.Padgets;

import com.google.api.client.util.escape.CharEscapers;
import com.google.gdata.client.GoogleService;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.TextContent;
import com.google.gdata.util.ServiceException;
import components.Authentication;

/**
 * 
 * @author iptv
 */
@Entity
public class Blogger extends SMProvider {

	public Blogger() {
		super();

		name = "blogger";
		authType = Authentication.OAUTH;

		requestTokenUrl = "https://www.google.com/accounts/OAuthGetRequestToken?scope="
				+ CharEscapers.escapeUri("http://www.blogger.com/feeds/");
		accessTokenUrl = "https://www.google.com/accounts/OAuthGetAccessToken";
		authorizeUrl = "https://www.google.com/accounts/OAuthAuthorizeToken";
		consumerKey = "anonymous";
		consumerSecret = "anonymous";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see models.SMProvider#requestIdentity(models.User)
	 */
	@Override
	public void getUserData(SMPAccount smpAccount) {
		Logger.debug("");
		Feed feed = getFeed(smpAccount);

		// account already exists?
		SMPAccount storedAccount = SMPAccount.findByUserAndNetworkId(
				smpAccount.userId, getUserId(feed));
		if (storedAccount != null) {
			smpAccount.delete();
			smpAccount = storedAccount;
		}

		smpAccount.networkUserId = getUserId(feed);
		smpAccount.username = getUserName(feed);
		smpAccount.name = getUserName(feed);
		smpAccount.profileUrl = getProfileUrl(feed);
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
		try {
			GoogleService client = initiateService(smpAccount);
			final URL feedUrl = new URL(
					"http://www.blogger.com/feeds/default/blogs");
			Feed resultFeed = client.getFeed(feedUrl, Feed.class);

			for (Entry entry : resultFeed.getEntries()) {
				PublishChannel channel = new PublishChannel();
				channel.pchid = Padgets.generateNewId();

				// is already stored?
				PublishChannel storedPage = PublishChannel
						.findByNetworkPageId(getBlogId(entry));
				if (storedPage != null)
					channel = storedPage;

				System.out.println("Blogger User id: " + getUserId(entry));
				channel.accountId = smpAccount.accountId;
				channel.userId = smpAccount.userId;
				channel.networkPageId = getBlogId(entry);
				// page.accessToken
				channel.name = entry.getTitle().getPlainText();
				channel.network = this.name;
				channel.save();
			}
		} catch (Exception e) {
			// TODO proper error handling
			e.printStackTrace();
		}

	}

	@Override
	public PublishedItem createMessage(PublishChannel channel, Message message) {
		SMPAccount account = SMPAccount.findByAccountId(channel.accountId);
		if (account == null)
			return null;
		try {
			GoogleService client = initiateService(account);
			String blogId = channel.networkPageId;
			Entry entry = new Entry();
			entry.setTitle(new PlainTextConstruct(message.title));
			entry.setContent(new PlainTextConstruct(message.content));

			// Ask the service to insert the new entry
			URL postUrl = new URL("http://www.blogger.com/feeds/" + blogId
					+ "/posts/default");
			entry = client.insert(postUrl, entry);

			PublishedItem item = new PublishedItem();
			item.pid = Padgets.generateNewId();

			item.channelId = channel.pchid;
			item.messageId = message.mid;
			System.out.println(entry.getId());
			item.networkPostId = getLastId(entry.getId());
			item.url = entry.getSelfLink().getHref();
			item.permalink = entry.getLink("alternate", "text/html").getHref();
			item.save();

			return item;
		} catch (OAuthException e) {
		} catch (ServiceException sE) {
		} catch (IOException ioE) {
		}
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
		SMPAccount account = SMPAccount.findByAccountId(channel.accountId);

		PublishedItem item = PublishedItem.findByChannelIdAndMessageId(
				channel.pchid, message.mid);
		if (item == null)
			return;
		try {
			GoogleService client = initiateService(account);

			Entry entry = client.getEntry(new URL(item.url), Entry.class);

			entry.setTitle(new PlainTextConstruct(message.title));
			entry.setContent(new PlainTextConstruct(message.content));

			URL editUrl = new URL(entry.getEditLink().getHref());
			entry = client.update(editUrl, entry);

		} catch (OAuthException e) {

		} catch (ServiceException sE) {

		} catch (IOException ioE) {

		}

	}

	/**
	 * @param entry
	 * @return
	 */
	private String getBlogId(Entry entry) {
		String[] tmp = entry.getId().split("-");
		return tmp[tmp.length - 1];
	}

	/**
	 * @param entry
	 * @return
	 */
	private String getUserId(Entry entry) {
		String[] tmp = entry.getId().split("-");
		return tmp[1].substring(0, tmp[1].indexOf("."));
	}

	private Feed getFeed(SMPAccount smpAccount) {
		// Request the feed
		try {
			GoogleService client = initiateService(smpAccount);
			final URL feedUrl = new URL(
					"http://www.blogger.com/feeds/default/blogs");
			return client.getFeed(feedUrl, Feed.class);
		} catch (Exception e) {
			return new Feed();
		}
	}

	private String getUserId(Feed feed) {
		return getUserId(feed.getEntries().get(0));
	}

	private String getUserName(Feed feed) {
		return feed.getAuthors().get(0).getName();
	}

	private String getProfileUrl(Feed feed) {
		return feed.getAuthors().get(0).getUri();
	}

	private GoogleService initiateService(SMPAccount smpAccount)
			throws OAuthException {
		GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		oauthParameters.setOAuthConsumerKey(consumerKey);
		oauthParameters.setOAuthConsumerSecret(consumerSecret);
		oauthParameters.setOAuthToken(smpAccount.oAuthToken);
		oauthParameters.setOAuthTokenSecret(smpAccount.oAuthSecret);

		GoogleService client = new GoogleService("blogger",
				"Padget-Dashboard-v1");
		// BloggerService client = new BloggerService("Padget-Dashboard-v1");

		client.setOAuthCredentials(oauthParameters, new OAuthHmacSha1Signer());

		return client;
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
		// all comments: http://www.blogger.com/feeds/blogID/comments/default
		GoogleService client;
		SMPAccount account;
		String blogUrl;
		URL url;
		Feed feed, commentsFeed;
		// get channels
		List<PublishChannel> channels = PublishChannel.findByNetwork(this.name);
		try {
			// iterate over all blogger channels
			for (PublishChannel channel : channels) {
				// get feed url
				account = SMPAccount.findByAccountId(channel.accountId);
				blogUrl = "http://www.blogger.com/feeds/"
						+ channel.networkPageId + "/posts/default";
				// System.out.println(blogUrl);
				url = new URL(blogUrl);
				// auth
				client = initiateService(account);
				feed = client.getFeed(url, Feed.class);

				// iterate over all entries
				for (Entry entry : feed.getEntries()) {
					PublishedItem item = PublishedItem
							.findByNetworkPostId(getLastId(entry.getId()));
					if (item == null) {
						continue;
					}
					Link commentsLink = entry.getLink("replies",
							"application/atom+xml");
					// System.out.println(commentsLink.getHref());
					// get comments feed
					commentsFeed = client.getFeed(
							new URL(commentsLink.getHref()), Feed.class);
					for (Entry commentEntry : commentsFeed.getEntries()) {
						String commentText = ((TextContent) commentEntry
								.getContent()).getContent().getPlainText();

						String commentId = getLastId(commentEntry.getId());
						// create or update feedback object
						Comment comment = Comment
								.findByNetworkNameAndNetworkCommentId(
										this.name, commentId);

						if (comment == null) {
							comment = new Comment();
							comment.ufid = Padgets.generateNewId();
						}
						comment.timestamp = commentEntry.getUpdated()
								.getValue();
						comment.content = commentText;
						comment.publishedItemId = item.pid;
						comment.messageId = item.messageId;
						// TODO more authors possible
						comment.userProfileUrl = commentEntry.getAuthors()
								.get(0).getUri();
						comment.network = this.name;
						comment.networkCommentId = commentId;
						comment.networkCommentUrl = commentEntry.getLink(
								"alternate", "text/html").getHref();
						comment.save();
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getLastId(String id) {
		// tag:blogger.com,1999:blog-7160233155156945615.post-1263270805712313428
		return id.substring(id.lastIndexOf("-") + 1);
	}

	@Override
	public PublishedItem createMedia(PublishChannel channel, Media media) {
		// TODO Auto-generated method stub
		return null;
	}

	//
	// @Override
	// public String setPhoto(File file,String userId) {
	// throw new UnsupportedOperationException("Not supported yet.");
	// }
	//
	// @Override
	// public String getComments(String objectID,String userId) {
	// // try {
	// // GoogleService client = initiateService();
	// // String blogID = getBlogID();
	// // if (blogID.contains("ERROR")) {
	// // return blogID;
	// // }
	// // final URL feedUrl = new URL("http://www.blogger.com/feeds/"
	// // + blogID + "/comments/default");
	// // Feed resultFeed = client.getFeed(feedUrl, Feed.class);
	// //
	// // String comments = "";
	// // for (Entry entry : resultFeed.getEntries()) {
	// // comments += ((TextContent) entry.getContent()).getContent()
	// // .getPlainText();
	// // comments += "\n";
	// // }
	// // return comments;
	// // } catch (Exception e) {
	// // return "ERROR";
	// // }
	// throw new UnsupportedOperationException("Not supported yet.");
	// }
	//

	// @Override
	// public String authenticate(AuthData authData) {
	// ServiceInfo serviceInfo = new ServiceInfo(requestTokenUrl,
	// accessTokenUrl, authorizeUrl, consumerKey, consumerSecret);
	// try {
	// serviceInfo.requestTokenURL =
	// "https://www.google.com/accounts/OAuthGetRequestToken?scope="
	// + URLEncoder.encode("http://www.blogger.com/feeds/",
	// "utf-8");
	// } catch (UnsupportedEncodingException e) {
	// return "";
	// }
	//
	// User user = Application.getCurrentUser();
	// OAuth service = OAuth.service(serviceInfo);
	// TokenPair tokens = service.requestUnauthorizedToken();
	// // We received the unauthorized tokens in the OAuth object - store it
	// // before we proceed
	// user.setTokenPair(tokens, name);
	// user.save();
	// Application.redirectWS(service.redirectUrl(tokens));
	// return "";
	// }
	//
	// @Override
	// public boolean verifyResponse(String facebookCode) {
	// User user = Application.getCurrentUser();
	// ServiceInfo serviceInfo = new ServiceInfo(requestTokenUrl,
	// accessTokenUrl, authorizeUrl, consumerKey, consumerSecret);
	// // We got the verifier; now get the access token, store it and back to
	// // index
	// TokenPair tokens = OAuth.service(serviceInfo).requestAccessToken(
	// user.getTokenPair(name));
	// user.setTokenPair(tokens, name);
	// user.save();
	// return true;
	// }
	//

	//
	// @Override
	// public void requestIdentity(PadgetsUser padgetsUser) {
	// Logger.debug("%s", padgetsUser.toString());
	// Feed feed = getFeed(padgetsUser);
	//
	// Identity identity = Identity.findByUserId(this.name, getUserId(feed));
	// if (identity == null) {
	// identity = new Identity();
	// identity.save();
	// padgetsUser.identities.add(identity);
	// padgetsUser.save();
	// }
	//
	// identity.network = this.name;
	// identity.networkUserId = getUserId(feed);
	// identity.username = getUserName(feed);
	// identity.name = getUserName(feed);
	// identity.profileUrl = getProfileUrl(feed);
	//
	// Logger.debug("Identity username: %s", identity.username);
	//
	// identity.save();
	// }
	//
	// /* (non-Javadoc)
	// * @see models.smconnector.SMProvider#getAccountItemsList()
	// */
	// @Override
	// public List<AdministratedPage> getAccountItemsList(String userId) {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	//
	// // Request the feed
	// try {
	// GoogleService client = initiateService(padgetsUser);
	// final URL feedUrl = new
	// URL("http://www.blogger.com/feeds/default/blogs");
	// Feed resultFeed = client.getFeed(feedUrl, Feed.class);
	//
	// // Print the results
	// // System.out.println(resultFeed.getTitle().getPlainText());
	// List<AdministratedPage> ids = new ArrayList<AdministratedPage>();
	// AdministratedPage info = null;
	// for (int i = 0; i < resultFeed.getEntries().size(); i++) {
	// Entry entry = resultFeed.getEntries().get(i);
	//
	// info = new AdministratedPage();
	//
	// info.pageUserId = getUserId(entry);
	// info.pageId = getBlogId(entry);
	// info.name = entry.getTitle().getPlainText();
	// ids.add(info);
	// Logger.debug("Blog info: %s", info.toString());
	//
	// }
	// return ids;
	// } catch (Exception e) {
	// return new ArrayList<AdministratedPage>();
	// }
	// // throw new UnsupportedOperationException("Not supported yet.");
	// }
	//
	// /* (non-Javadoc)
	// * @see models.smconnector.SMProvider#postAddAccount(java.lang.String,
	// models.smconnector.CampaignSMPage)
	// */
	// @Override
	// public PadgetsUser postAddAccount(String userId, AdministratedPage page)
	// {
	// PadgetsUser padgetsUser = PadgetsUser.findByUserId(userId);
	// for (Identity identity : padgetsUser.identities) {
	// if ("blogger".equals(identity.network)) {
	// page.save();
	// identity.pages.add(page);
	// identity.save();
	// break;
	// }
	// }
	// padgetsUser.save();
	// return padgetsUser;
	// }

}
