package main.com.server.handlers;

import static main.com.server.handlers.Common.parseQuery;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import main.com.server.constant.ServerConstant;
import main.com.server.exception.FileDeleteException;
import main.com.server.exception.FileUploadException;
import main.com.server.util.LogUtil;
import main.com.server.validator.ValidateRequest;

public class DeleteFileHandler implements HttpHandler {
	private final LogUtil logUtil = new LogUtil();
	private final ValidateRequest validator = new ValidateRequest();

	@Override
	public void handle(HttpExchange he) throws IOException {
		validator.validateDeleteFile(he);
		Map<String, Object> parameters = new HashMap<>();
		parseQuery(he.getRequestURI().getQuery(), parameters);
		String fileName = parameters.get("fileName").toString();

		File toBeDeleted = new File(ServerConstant.SERVER_PATH + File.separator + ServerConstant.STORAGE_PATH + File.separator + fileName);
		if (!toBeDeleted.exists()) {
			throw new FileDeleteException(he, "deleteFile", ServerConstant.NOT_FOUND, ServerConstant.FILE_TO_BE_DELETED_DOES_NOT_EXIST, null);
		}
		if (toBeDeleted.delete()) {
			logUtil.logMessage("[deleteFile] File deleted successfully " + fileName);
		} else {
			throw new FileDeleteException(he, "deleteFile", ServerConstant.INTERNAL_SERVER_ERROR, ServerConstant.FILE_DELETE_EXCEPTION, null);
		}

		String response = "";
		he.sendResponseHeaders(ServerConstant.DELETED, 0);
		OutputStream os = he.getResponseBody();
		os.write("File Deleted Successfully".getBytes());
		os.close();
	}
}

