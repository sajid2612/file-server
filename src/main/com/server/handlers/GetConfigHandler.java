package main.com.server.handlers;

import static main.com.server.constant.ServerConstant.CONFIG_PATH;
import static main.com.server.constant.ServerConstant.SERVER_PATH;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import main.com.server.util.JsonBuilder;
import main.com.server.util.LogUtil;

public class GetConfigHandler implements HttpHandler {
	LogUtil logUtil = new LogUtil();
	private static final JsonBuilder jsonBuilder = new JsonBuilder();
	private static final Charset CHARSET = StandardCharsets.UTF_8;
	@Override
	public void handle(HttpExchange he) throws IOException {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(SERVER_PATH + File.separator + CONFIG_PATH));
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		StringBuilder response = new StringBuilder(100);
		response.append("{")
				.append(jsonBuilder.toJsonFormat("Running Port", prop.getProperty("port"))).append(",")
				.append(jsonBuilder.toJsonFormat("Build Version", prop.getProperty("version"))).append(",")
				.append(jsonBuilder.toJsonFormat("Debug Mode", prop.getProperty("debug")))
				.append("}");
		he.sendResponseHeaders(200, response.length());
		final Headers headers = he.getResponseHeaders();
		headers.set("Content-Type", String.format("application/json; charset=%s", CHARSET));
		OutputStream os = he.getResponseBody();
		os.write(response.toString().getBytes());
		os.close();
		logUtil.logMessage("[Get Configs]>>" + response.toString());
	}
}

