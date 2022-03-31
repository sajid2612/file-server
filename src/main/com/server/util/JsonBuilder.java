package main.com.server.util;

public class JsonBuilder {
	public String toJsonFormat(String key, String value) {
		return "\"" +key+ "\": "+"\""+value+"\"";
	}
}
