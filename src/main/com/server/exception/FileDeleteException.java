package main.com.server.exception;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import main.com.server.util.ResponseUtil;

public class FileDeleteException extends RuntimeException {
	private final ResponseUtil responseUtil = new ResponseUtil();

	public FileDeleteException(HttpExchange he, String endpoint, int errorCode, String errorMessage, Exception e) {
		super(errorMessage);
		try {
			responseUtil.buildNotOkResponse(he, endpoint, errorCode, errorMessage,e);
		} catch (IOException ioe) {
			e.printStackTrace();
		}
	}
}
