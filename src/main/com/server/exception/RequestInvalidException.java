package main.com.server.exception;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import main.com.server.util.ResponseUtil;

public class RequestInvalidException extends RuntimeException {
	private final ResponseUtil responseUtil = new ResponseUtil();
	public RequestInvalidException(HttpExchange he, String endpoint, int errorCode, String errorMessage) {
		super(errorMessage);
		try {
			responseUtil.buildNotOkResponse(he, endpoint, errorCode, errorMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
