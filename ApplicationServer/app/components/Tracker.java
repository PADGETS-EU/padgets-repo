package components;

import models.SMProvider;
import play.Logger;

public class Tracker {

	public static void track(String network) {
		Logger.debug("");
		SMProvider provider = SMProvider.findByName(network);
		provider.refreshUserFeedback();

	}

}
