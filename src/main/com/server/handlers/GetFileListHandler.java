package main.com.server.handlers;


import static main.com.server.constant.ServerConstant.SERVER_PATH;
import static main.com.server.constant.ServerConstant.STORAGE_PATH;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import main.com.server.util.LogUtil;
import main.com.server.validator.ValidateRequest;

public class GetFileListHandler implements HttpHandler {
	private final LogUtil logUtil = new LogUtil();
	private final ValidateRequest validator = new ValidateRequest();
	@Override
	public void handle(HttpExchange he) throws IOException {
		validator.validateGetFile(he);
		StringBuilder response = new StringBuilder();
		File file = new File(SERVER_PATH + File.separator + STORAGE_PATH);
		String[] fileList = file.list();
		if (Objects.requireNonNull(fileList).length == 0) {
			response.append("No files found");
		}
		for (String str : fileList) {
			response.append(str).append("\n");
		}
		logUtil.logMessage("[Get List of Files]>>\n" + response.toString());
		he.sendResponseHeaders(200, response.length());
		OutputStream os = he.getResponseBody();
		os.write(response.toString().getBytes());
		os.close();
	}
}

