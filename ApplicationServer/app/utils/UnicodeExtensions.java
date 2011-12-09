package utils;

import play.templates.JavaExtensions;

public class UnicodeExtensions extends JavaExtensions {

	public static String unicodeEscape(String string) {
		return StringUtil.unicodeEscape(string);
	}
}