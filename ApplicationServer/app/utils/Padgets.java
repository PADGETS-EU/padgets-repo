package utils;

import java.util.Random;

import play.Play;

public class Padgets {

	public static final String url = Play.configuration.getProperty("server.url");

	/*
	 * creates a random string of 20 characters and returns it.
	 */
	public static long generateNewId() {
		final String CHARMAP = "123456789"; // abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder buf = new StringBuilder();
		Random r = new Random();

		for (int i = 0; i < 15; i++) {
			char c = CHARMAP.charAt(r.nextInt(9));
			buf.append(c);
		}

		return new Long(buf.toString()).longValue();

	}

	public static String getMediaType(String filename) {
		if (filename.contains(".")) {

			String ending = filename.substring(filename.lastIndexOf(".") + 1);
			String[] imageTypes = { "jpg", "jpeg", "png" };
			String[] videoTypes = { "mp4", "mpeg", "flv" };

			if (contains(imageTypes, ending)) {
				return "image/" + ending;
			}
			if (contains(videoTypes, ending)) {
				return "video/" + ending;
			}
			return null;
		} else {
			return null;
		}
	}

	private static boolean contains(String[] list, String value) {
		for (String s : list) {
			if (s.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static long toLong(String s) {
		return s == null ? 0 : new Long(s).longValue();
	}
}
