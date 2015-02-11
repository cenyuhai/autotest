package com.feisystems.automationtest.libary;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RavenDbAPI {

	public static String getBaseUrl() {
		String baseUrl = null;

		Properties prop = new Properties();
		InputStream in = Object.class
				.getResourceAsStream("/autotest.properties");
		try {
			prop.load(in);
		} catch (IOException e) {

		}

		baseUrl = prop.getProperty("DbUrl").trim();
		if (!baseUrl.endsWith("/")) {
			baseUrl += "/";
		}
		return baseUrl;
	}

	public static void deleteDocumentByAttriute(String docs, String attr,
			String value) {
		String jsonStr = getUrlContents(getBaseUrl() + "indexes/dynamic/"
				+ docs);
		try {
			List<String> ids = getDocIdsFromJson(jsonStr, attr, value);
			for (String id : ids) {
				RavenDbAPI.deleteDocumentById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public static void deleteDocumentById(String id) {
		String basePath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		basePath = basePath.substring(1, basePath.length());
		String cmd = null;

		if (System.getProperty("os.name").contains("Linux")) {
			cmd = "curl -X DELETE " + getBaseUrl() + "docs/" + id;
		} else if (System.getProperty("os.name").contains("Windows")) {
			cmd = basePath + "curl" + File.separator + "curl.exe -X DELETE "
					+ getBaseUrl() + "docs/" + id;
		}

		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			process.destroy();
		}
	}

	public static List<String> getDocIdsFromJson(String json, String attribute,
			String value) throws Exception {
		JSONObject jsonObject;
		jsonObject = new JSONObject(json);
		JSONArray results = jsonObject.getJSONArray("Results");
		List<String> ids = new ArrayList<String>();
		String attrValue = null;
		boolean notFound;
		for (int i = 0; i < results.length(); i++) {
			notFound = false;
			JSONObject result = results.getJSONObject(i);
			JSONObject metaData = result.getJSONObject("@metadata");

			if (attribute.equals("id")) {
				ids.add(metaData.getString("@" + attribute));
			} else {
				String[] args = attribute.split("\\.");
				for (int j = 0; j < args.length - 1; j++) {
					try {
						result = result.getJSONObject(args[j]);
					} catch (Exception ex) {
						notFound = true;
						break;
					}
				}
				if (notFound)
					continue;

				if (args.length > 0)
					attrValue = result.getString(args[args.length - 1]);
				else
					attrValue = result.getString(attribute);

				if (attrValue.equals(value)) {
					ids.add(metaData.getString("@id"));
				}

			}

		}
		return ids;

	}

	public static List<String> getDocIdsFromJson(String json) throws Exception {
		return getDocIdsFromJson(json, "id", null);
	}

	public static List<String> getCollectionEntityId(String docs) {
		try {
			String jsonStr = getUrlContents(getBaseUrl() + "indexes/dynamic/"
					+ docs);
			return getDocIdsFromJson(jsonStr);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ArrayList<String>();
		
	}

	private static String getUrlContents(String theUrl) {
		StringBuilder content = new StringBuilder();

		// many of these calls can throw exceptions, so i've just
		// wrapped them all in one try/catch statement.
		try {
			// create a url object
			URL url = new URL(theUrl);
			// create a urlconnection object
			URLConnection urlConnection = url.openConnection();
			// wrap the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// read from the urlconnection via the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();

	}

	public static void deleteCollectionByName(String name) {
		try {
			List<String> ids = getCollectionEntityId(name);
			if (ids != null) {
				for (String id : ids) {
					RavenDbAPI.deleteDocumentById(id);
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// Logger logger = TestUtils.getLogger(RavenDbAPI.class);
		// SeleniumWrapper selenium = new SeleniumWrapper();
		// String jsonStr =
		// getUrlContents("http://localhost:9020/databases/eLtss/indexes/dynamic/casenotes");
		// String[] ids = getDocIdsFromJson(jsonStr);
		// selenium.open("http://localhost:9020/databases/eLtss/indexes/dynamic/staffs");
		/*
		 * String jsonStr = selenium.getAttribute("css=pre@innerHTML");
		 * 
		 * try { String[] ids = getDocIdsFromJson(jsonStr); for(String id : ids)
		 * {
		 * //selenium.open("DELETE http://localhost:9020/databases/eLtss/docs/"
		 * + id); deleteDocumentById(id); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

	}

}
