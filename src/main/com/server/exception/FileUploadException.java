package main.com.server.exception;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import main.com.server.util.ResponseUtil;

public class FileUploadException extends RuntimeException {
	private final ResponseUtil responseUtil = new ResponseUtil();

	public FileUploadException(HttpExchange he, String endpoint, int errorCode, String errorMessage, Exception e) {
		super(errorMessage);
		try {
			responseUtil.buildNotOkResponse(he, endpoint, errorCode, errorMessage,e);
		} catch (IOException ioe) {
			e.printStackTrace();
		}
	}
}
