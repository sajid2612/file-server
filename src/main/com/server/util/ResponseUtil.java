package main.com.server.util;


import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseUtil {
	private final LogUtil logUtil = new LogUtil();
	public void buildNotOkResponse(HttpExchange he, String endpoint, int errorCode, String errorMessage) throws IOException {
		buildNotOkResponse(he, endpoint, errorCode, errorMessage, null);
	}

	public void buildNotOkResponse(HttpExchange he, String endpoint, int errorCode, String errorMessage, Exception e) throws IOException {
		he.sendResponseHeaders(errorCode, errorMessage.length());
		OutputStream os = he.getResponseBody();
		os.write(errorMessage.getBytes());
		logUtil.logErrorMessage("["+endpoint+"]" + errorMessage);
		if (e != null) {
			os.write(e.getMessage().getBytes());
			logUtil.logErrorMessage(e.getMessage());
		}
		os.close();
	}
}
