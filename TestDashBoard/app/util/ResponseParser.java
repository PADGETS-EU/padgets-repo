package util;

import java.util.HashMap;
import java.util.Map;

public class ResponseParser {

	public static Map<String, String> parse(String response) throws Exception {
		Map<String, String> result = new HashMap<String, String>();

		if (response.startsWith("{") && response.endsWith("}")) {
			response = response.substring(1, response.length() - 1);
			String[] tmp = response.split(",");
			String[] keyValue;
			if (tmp[tmp.length - 1] == null || tmp[tmp.length - 1].equals("")) {
				throw new Exception("Parse Error. No JSON format.");
			}
			for (int i = 0; i < tmp.length ; i++) {
				if (tmp[i].contains(":")) {
					keyValue = tmp[i].split(":");
					result.put(keyValue[0].substring(1, keyValue[0].length() - 1),
							keyValue[1].substring(1, keyValue[1].length() - 1));
				} else {
					throw new Exception("Parse Error. No JSON format.");
				}

			}
		}else{
			throw new Exception("Parse Error. No JSON format.");
		}

		return result;
	}
}
