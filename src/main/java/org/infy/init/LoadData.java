
package org.infy.init;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadData {

	private static String url;
	private static String username;
	private static org.apache.logging.log4j.Logger log = LogManager.getLogger(LoadData.class);

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		LoadData.username = username;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		LoadData.url = url;
	}

	public static JSONObject readJSONFileToCreatePipeline(String path) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject req = (JSONObject) parser.parse(new FileReader(path));
			JSONObject pipeline = (JSONObject) ((JSONArray) req.get("pipelines")).get(0);
			return pipeline;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	public static JSONObject readJSONFile(String path) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject req = (JSONObject) parser.parse(new FileReader(path));
			return req;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	public static void setLoginDetails() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject req = (JSONObject) parser.parse(new FileReader(Constants.LOGINDETAILS_JSON_PATH));

		log.info("URL " + req.get("url"));

		setUrl(req.get("url").toString());
		setUsername(req.get("user").toString());
	}
}
