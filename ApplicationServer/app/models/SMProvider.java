/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * 
 * @author iptv
 */
@Entity
public abstract class SMProvider extends Model {

	public String name;
	public String authType;

	// OAuth
	public String requestTokenUrl;
	public String accessTokenUrl;
	public String authorizeUrl;
	public String consumerKey;
	public String consumerSecret;

	// OAuth 2.0
	public String authorizationURL;
	public String accessTokenURL2;
	public String clientid;
	public String secret;
	public String scope;

	/**
	 * Request the identity of a given user in a social media platform
	 * 
	 * @param padgetsUser
	 *            the user whose identity should be requested
	 */
	public abstract void getUserData(SMPAccount smpAccount);

	/**
	 * @param smpAccount
	 */
	public abstract void getUserPublishChannels(SMPAccount smpAccount);

	// /**
	// * Sets a status message in the social media platform
	// *
	// * @param status
	// * the status message
	// * @return The id of the published status message in the social media
	// * platform
	// */
	// public abstract String setStatus(String title, String status, String
	// userId);
	//
	// /**
	// * Request all comments to a given object in the social media platform
	// *
	// * @param objectID
	// * The id of the object.
	// * @return The comments posted to this objectID
	// */
	// public abstract String getComments(String objectID, String userId);
	//
	// /**
	// * Uploads a photo to the social media platform.
	// *
	// * @param file
	// * @throws UnsupportedOperationException
	// * if the platform doesn't support this service
	// * @return The id of the published photo in the social media platform
	// */
	// public abstract String setPhoto(File file, String userId);

	public static SMProvider findByName(String name) {
		return SMProvider.find("name", name).first();
	}

	/**
	 * @param title
	 * @param status
	 * @param userId
	 * @return
	 */
	public abstract PublishedItem createMessage(PublishChannel channel, Message message);

	/**
	 * @param channel
	 * @param message
	 */
	public abstract void updateMessage(PublishChannel channel, Message message);

	/**
	 * @param twitterChannel
	 * @param message
	 * @param item
	 */
	public abstract void createMessage(PublishChannel twitterChannel, Message message, PublishedItem item);

	public abstract void refreshUserFeedback();

	public abstract PublishedItem createMedia(PublishChannel channel, Media media);

	// /**
	// * @param parameters
	// */
	// public abstract PadgetsUser postAddAccount(String userId,
	// AdministratedPage page);
	//
	// /**
	// * @param userId
	// * @return
	// *
	// */
	// public abstract List<AdministratedPage> getAccountItemsList(String
	// userId);
}
