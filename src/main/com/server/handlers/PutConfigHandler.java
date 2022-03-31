package main.com.server.handlers;

import static main.com.server.constant.ServerConstant.CONFIG_PATH;
import static main.com.server.constant.ServerConstant.SERVER_PATH;
import static main.com.server.handlers.Common.parseQuery;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import main.com.server.util.LogUtil;

public class PutConfigHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange he) throws IOException {
		// parse request
		Map<String, Object> parameters = new HashMap<>();
		parseQuery(he.getRequestURI().getQuery(), parameters);

		FileInputStream in = new FileInputStream(SERVER_PATH + File.separator + CONFIG_PATH);
		Properties props = new Properties();
		props.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream(SERVER_PATH + File.separator + CONFIG_PATH);
		props.setProperty("level", parameters.get("level").toString());
		new LogUtil().updateLogStatus(parameters.get("level").toString());
		props.store(out, null);
		out.close();

		he.sendResponseHeaders(200, 0);
		OutputStream os = he.getResponseBody();
		os.write(0);
		os.close();
	}
}

