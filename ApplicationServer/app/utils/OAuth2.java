package utils;

import java.util.Map;

import play.Logger;
import play.libs.WS;
import play.mvc.Http.Request;
import play.mvc.Scope.Params;

/**
 * Library to access ressources protected by OAuth 2.0. For OAuth 1.0a, see
 * play.libs.OAuth. See the facebook-oauth2 example for usage.
 * 
 */
public class OAuth2 {

	public String authorizationURL;
	public String accessTokenURL;
	public String clientid;
	public String secret;
	public String callbackURL;
	public String scope;

	public OAuth2(String authorizationURL, String accessTokenURL, String clientid, String secret,
			String scope) {
		this.accessTokenURL = accessTokenURL;
		this.authorizationURL = authorizationURL;
		this.clientid = clientid;
		this.secret = secret;
		this.scope = scope;
	}

	
//	public OAuth2(SMProvider provider) {
//		this(provider.authorizationURL, provider.accessTokenURL2, provider.clientid,
//				provider.secret, provider.scope);
//	}

	public static boolean isCodeResponse() {
		return Params.current().get("code") != null;
	}

	public String requestAccessToken() {
		return requestAccessToken(null);
	}

	public String requestAccessToken(Map<String, String> params) {
		// if (params == null)
		// params = new HashMap<String, String>();

		if (callbackURL == null)
			callbackURL = Request.current().getBase() + Request.current().path;

		// params.put("client_id", clientid);
		// params.put("redirect_uri", callbackURL);

		String url = authorizationURL + "?client_id=" + clientid + "&redirect_uri=" + callbackURL;
		if (!StringUtil.isEmptyOrWhitespace(scope)) {
			url += "&scope=" + scope;
		}
		return url;
	}

	public String getAccessToken() {
		String callbackURL = Request.current().getBase() + Request.current().path;
		String accessCode = Params.current().get("code");

		// String url = accessTokenURL + "?client_id=" + clientid +
		// "&client_secret=" + secret
		// + "&redirect_uri=" + callbackURL + "&code=" + accessCode;
		//
		// WS.HttpResponse res = WS.url(url).get();

		WS.HttpResponse res =
				WS.url(accessTokenURL)
						.setParameter("client_id", clientid)
						.setParameter("client_secret", secret)
						.setParameter("redirect_uri", callbackURL)
						.setParameter("code", accessCode)
						.get();
		Logger.debug("access_token: %s", res.getQueryString().get("access_token"));
		return res.getQueryString().get("access_token");
	}

	// @Facebook
	public String getAppAccessToken() {
		WS.HttpResponse res =
				WS.url(accessTokenURL)
						.setParameter("client_id", clientid)
						.setParameter("client_secret", secret)
						.setParameter("grant_type", "client_credentials")
						.get();
		return res.getQueryString().get("access_token");
	}
}
