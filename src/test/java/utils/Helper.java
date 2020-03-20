package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Helper {

	JsonPath objPath;
	public String getJsonPathValue(Response response , String key) {
		String responseStr = response.asString();
		objPath = new JsonPath(responseStr);
		return objPath.get(key);
	
	}
}
