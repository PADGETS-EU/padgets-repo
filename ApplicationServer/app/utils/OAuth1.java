package utils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import play.libs.OAuth.ServiceInfo;
import play.libs.OAuth.TokenPair;
import play.mvc.Http.Request;
import play.mvc.Scope.Params;

import com.google.common.base.Strings;

/**
 * Library to access ressources protected by OAuth 1.0a. For OAuth 2.0, see
 * play.libs.OAuth2.
 * 
 */
public class OAuth1 {

	private ServiceInfo info;
	private OAuthProvider provider;

	private OAuth1(ServiceInfo info) {
		this.info = info;
		provider =
				new DefaultOAuthProvider(info.requestTokenURL, info.accessTokenURL,
						info.authorizationURL);
		provider.setOAuth10a(true);
	}

	/**
	 * Create an OAuth object for the service described in info
	 * 
	 * @param info
	 *            must contain all informations related to the service
	 * @return the OAuth object
	 */
	public static OAuth1 service(ServiceInfo info) {
		return new OAuth1(info);
	}

	public static boolean isVerifierResponse() {
		return Params.current().get("oauth_verifier") != null;
	}

	/**
	 * Request the unauthorized token and secret. They can then be read with
	 * getTokens()
	 * 
	 * @return the url to redirect the user to get the verifier and continue the
	 *         process
	 */
	public TokenPair requestUnauthorizedToken(String callbackURL) {
		OAuthConsumer consumer = new DefaultOAuthConsumer(info.consumerKey, info.consumerSecret);
		if (Strings.isNullOrEmpty(callbackURL))
			callbackURL = Request.current().getBase() + Request.current().url;
		try {
			provider.retrieveRequestToken(consumer, callbackURL);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new TokenPair(consumer.getToken(), consumer.getTokenSecret());
	}

	public TokenPair requestAccessToken(TokenPair tokenPair) {
		OAuthConsumer consumer = new DefaultOAuthConsumer(info.consumerKey, info.consumerSecret);
		consumer.setTokenWithSecret(tokenPair.token, tokenPair.secret);
		String verifier = Params.current().get("oauth_verifier");
		try {
			provider.retrieveAccessToken(consumer, verifier);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new TokenPair(consumer.getToken(), consumer.getTokenSecret());
	}

	public String redirectUrl(TokenPair tokenPair) {
		return oauth.signpost.OAuth.addQueryParameters(provider.getAuthorizationWebsiteUrl(),
				oauth.signpost.OAuth.OAUTH_TOKEN, tokenPair.token);
	}

}
