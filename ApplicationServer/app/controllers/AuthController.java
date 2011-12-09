package controllers;

import models.AuthData;
import models.OpenIDData;
import models.OpenIDProvider;
import play.Logger;
import play.mvc.Controller;
import utils.StringUtil;

import components.Authentication;

public class AuthController extends Controller {

	public static void login(String provider, String redirectUrl, String discoveryUrl) {
		Logger.debug("");
		// TODO check input
		OpenIDData data = new OpenIDData(provider, redirectUrl, discoveryUrl);
		OpenIDProvider openIDProvider = OpenIDProvider.findByName(data.provider);
		if (null == openIDProvider) {
			// no stored provider, check discovery url
			if (StringUtil.isEmptyOrWhitespace(data.discoveryUrl)) {
				// TODO nice error msg
				renderText("Error");
			}
			openIDProvider = new OpenIDProvider();
			openIDProvider.discoveryUrl = data.discoveryUrl;
		}

		AuthData authData = new AuthData(Authentication.OPENID, data);
		authData.save();

		openIDProvider.authenticate(authData);
	}

	public static void openID(String hash) {
		Logger.debug("Hash: %s", hash);
		// TODO error handling
		String redirectURL = Authentication.openID(hash);
		Logger.debug("Redirect: %s", redirectURL);
		if (null != redirectURL) {
			redirect(redirectURL);
		}
	}

	public static void oauth(String hash) {
		Logger.debug("Hash: %s", hash);
		String redirectURL = Authentication.verifyResponse(hash);
		Logger.debug("Redirect: %s", redirectURL);
		if (null != redirectURL) {
			redirect(redirectURL);
		}
	}

}
