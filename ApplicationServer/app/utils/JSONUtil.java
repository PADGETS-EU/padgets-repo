package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONUtil {

	private static Gson gsonPP = new GsonBuilder().setPrettyPrinting().create();
	private static Gson gson = new Gson();

	public static String prettyPrint(String json) {
		return prettyPrint(new JsonParser().parse(json));
	}

	public static String prettyPrint(JsonElement json) {
		return gsonPP.toJson(json);
	}
}
