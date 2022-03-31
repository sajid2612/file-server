package main.com.server.validator;


import com.sun.net.httpserver.HttpExchange;
import main.com.server.constant.ServerConstant;
import main.com.server.exception.RequestInvalidException;

public class ValidateRequest {
	public void validateGetFile(HttpExchange he) {
		validateMethodType(he, "GET", "listFiles");
	}

	public void validateUploadFile(HttpExchange he) {
		validateMethodType(he, "POST", "uploadFile");
		validateContentType(he, "uploadFile");
	}

	private void validateContentType(HttpExchange he, String methodName) {
		if (!he.getRequestHeaders().getFirst("Content-Type").contains("multipart/form-data")) {
			throw new RequestInvalidException(he, methodName, ServerConstant.BAD_REQUEST, ServerConstant.CONTENT_TYPE_MISSING);
		}
	}

	private void validateMethodType(HttpExchange he, String allowedMethod, String methodName) {
		if (!he.getRequestMethod().equals(allowedMethod) ) {
			throw new RequestInvalidException(he, methodName, ServerConstant.BAD_REQUEST, ServerConstant.METHOD_NOT_ALLOWED);
		}
	}

	public void validateDeleteFile(HttpExchange he) {
		validateMethodType(he, "DELETE", "deleteFile");
	}
}
