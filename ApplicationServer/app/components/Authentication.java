package components;

import models.AuthData;
import models.SMPAccount;
import models.SMProvider;
import models.User;
import play.Logger;
import play.libs.OAuth;
import play.libs.OAuth.ServiceInfo;
import play.libs.OAuth.TokenPair;
import play.libs.OpenID;
import play.libs.OpenID.UserInfo;
import utils.OAuth1;
import utils.OAuth2;
import utils.Padgets;

import com.google.api.client.util.escape.CharEscapers;

public class Authentication {

	public static String OAUTH = "oauth";
	public static String OAUTH2 = "oauth2";
	public static String OPENID = "openID";

	public static String auth(AuthData authData) {
		SMProvider provider = SMProvider.findByName(authData.network);

		SMPAccount smpAccount = new SMPAccount();
		smpAccount.accountId = Padgets.generateNewId();
		smpAccount.network = authData.network;
		smpAccount.userId = authData.userId;
		smpAccount.save();

		authData.accountId = smpAccount.accountId;

		if (OAUTH.equals(provider.authType)) {
			authData.type = OAUTH;
			authData.save();
			return oauth(provider, authData, smpAccount);
		}
		if (OAUTH2.equals(provider.authType)) {
			authData.type = OAUTH2;
			authData.save();
			return oauth2(provider, authData);
		}
		return null;
	}

	private static String oauth2(SMProvider provider, AuthData authData) {
		OAuth2 oAuth2 = getOAuth2(provider);

		oAuth2.callbackURL = Padgets.url + "oauth/" + authData.hash;
		return oAuth2.requestAccessToken();
	}

	private static String oauth(SMProvider provider, AuthData authData, SMPAccount smpAccount) {
		OAuth1 service = OAuth1.service(getOAuth(provider));
		TokenPair tokens = service.requestUnauthorizedToken(Padgets.url + "oauth/" + authData.hash);

		smpAccount.oAuthToken = tokens.token;
		smpAccount.oAuthSecret = tokens.secret;
		smpAccount.save();

		Logger.debug("Redirect URL: %s", service.redirectUrl(tokens));
		return service.redirectUrl(tokens);
	}

	private static OAuth2 getOAuth2(SMProvider provider) {
		return new OAuth2(provider.authorizationURL, provider.accessTokenURL2, provider.clientid,
				provider.secret, provider.scope);
	}

	private static ServiceInfo getOAuth(SMProvider provider) {
		return new ServiceInfo(provider.requestTokenUrl, provider.accessTokenUrl,
				provider.authorizeUrl, provider.consumerKey, provider.consumerSecret);
	}

	// OAuth 1 & 2
	public static String verifyResponse(String hash) {
		AuthData authData = AuthData.find("hash", hash).first();
		if (authData != null) {
			SMProvider provider = SMProvider.findByName(authData.network);
			SMPAccount smpAccount = SMPAccount.findByAccountId(authData.accountId);

			verify(provider, smpAccount);

			provider.getUserData(smpAccount);
			provider.getUserPublishChannels(smpAccount);
			authData = authData.delete();

			return CharEscapers.decodeUri(authData.redirectUrl);
		}
		return null;
	}

	private static void verify(SMProvider provider, SMPAccount smpAccount) {
		// OAuth 1
		if (OAuth.isVerifierResponse() && OAUTH.equals(provider.authType)) {
			ServiceInfo serviceInfo = getOAuth(provider);
			TokenPair requestTokens = new TokenPair(smpAccount.oAuthToken, smpAccount.oAuthSecret);
			TokenPair tokens = OAuth1.service(serviceInfo).requestAccessToken(requestTokens);
			smpAccount.oAuthToken = tokens.token;
			smpAccount.oAuthSecret = tokens.secret;
			smpAccount.save();
		}
		// OAuth 2
		if (OAuth2.isCodeResponse() && OAUTH2.equals(provider.authType)) {
			OAuth2 oAuth2 = getOAuth2(provider);

			String token = oAuth2.getAccessToken();

			smpAccount.oAuth2Token = token;
			smpAccount.save();
		}
	}

	// /**
	// * Logs in a user over openID.
	// *
	// * @param parameters
	// * the network attribute is required. It contains the name of the
	// * openID provider.
	// */
	// public static void login(OpenIDData data) {
	// OpenIDProvider provider = OpenIDProvider.findByName(data.provider);
	// if (null == provider) {
	// // no stored provider, check discovery url
	// if (StringUtil.isEmptyOrWhitespace(data.discoveryUrl)){
	//
	// return null;
	// }
	// provider = new OpenIDProvider();
	// provider.discoveryUrl = data.discoveryUrl;
	// }
	//
	// AuthData authData = new AuthData(OPENID, parameters);
	// authData.save();
	//
	// provider.authenticate(authData);
	// }

	/**
	 * callback for openID authentication.
	 * 
	 * @param hash
	 *            a genarated hash to assign this request to a previous one.
	 * @return a previously given redirect url containing the userSIGN as
	 *         attribute
	 */
	public static String openID(String hash) {
		AuthData authData = AuthData.find("hash", hash).first();
		if (authData != null) {
			if (OpenID.isAuthenticationResponse() && OPENID.equals(authData.type)) {
				UserInfo verifiedUser = OpenID.getVerifiedID();
				if (verifiedUser == null) {
					// TODO Error
				}
				// user exist?
				User user = User.findByOpenIDVerifiedIdentifier(verifiedUser.id);
				if (user == null) {
					// create new user
					user = new User();
					user.uid = Padgets.generateNewId();

					// get user data
					user.openid = verifiedUser.id;
					user.openIDVerifiedIdentifier = verifiedUser.id;
					user.username =
							verifiedUser.extensions.get("fullname") != null
									? verifiedUser.extensions.get("fullname")
									: verifiedUser.extensions.get("firstname")
										+ " "
										+ verifiedUser.extensions.get("lastname");
					user.email = verifiedUser.extensions.get("email");
				}

				// change user status to logged in
				user.authtime = user.generateAuthtime();
				user.userSIGN = user.generateUserSIGN();

				user.save();
				authData = authData.delete();
				String redirectURL =
						CharEscapers.decodeUri(authData.redirectUrl)
							+ "?userId="
							+ user.uid
							+ "&userSIGN="
							+ user.userSIGN;
				return redirectURL;
			}
		}
		return null;
	}

	// /**
	// * @param config
	// * @param parameters
	// */
	// public static List<CampaignSMPage> getAccountItemsList(Config config,
	// Parameters parameters) {
	// SMProvider provider = SMProvider.findByName(parameters.network);
	// return provider.getAccountItemsList();
	// }
	//
	// /**
	// * @param parameters
	// * @param config
	// *
	// */
	// public static void postAddAccount(Config config, Parameters parameters) {
	// SMProvider provider = SMProvider.findByName(parameters.network);
	// provider.postAddAccount(parameters);
	// }

	// /**
	// * @param parameters
	// * @return TODO
	// */
	// public static PadgetsUser removeAccount(Parameters parameters) {
	// PadgetsUser padgetsUser =
	// PadgetsUser.findByUserSIGN(parameters.userSIGN);
	// if(padgetsUser== null){
	// padgetsUser=PadgetsUser.findByUserId(parameters.userId);
	// }
	// AccountData data = padgetsUser.removeTokenPair(parameters.network);
	// Identity identity = null;
	// for (Identity i : padgetsUser.identities) {
	// if (parameters.identityId.equals(i.identityId)) {
	// identity = i;
	// padgetsUser.identities.remove(i);
	// break;
	// }
	// }
	// padgetsUser.save();
	// if (identity != null)
	// identity.delete();
	// if(data!= null){
	// data.delete();
	// }
	// Logger.debug("User after remove: %s", padgetsUser);
	// return padgetsUser;
	// }
}
