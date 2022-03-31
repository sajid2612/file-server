package main.com.server.handlers;

import static main.com.server.handlers.Common.parseQuery;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import main.com.server.constant.ServerConstant;
import main.com.server.exception.FileUploadException;
import main.com.server.util.LogUtil;
import main.com.server.validator.ValidateRequest;

public class PostFileUploadHandler implements HttpHandler {
	private final LogUtil logUtil = new LogUtil();
	private final ValidateRequest validator = new ValidateRequest();

	@Override
	public void handle(HttpExchange he) throws IOException {
		validator.validateUploadFile(he);
		Map<String, Object> parameters = new HashMap<>();
		parseQuery(he.getRequestURI().getQuery(), parameters);
		Path path = Paths.get(parameters.get("filePath").toString());
		if (Files.exists(path)) {
			throw new FileUploadException(he, "fileUpload", 409, ServerConstant.FILE_ALREADY_EXISTS, null);
		}
		String fileName = path.getFileName().toString();
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(path.toString());
			os = new FileOutputStream(ServerConstant.SERVER_PATH + File.separator + ServerConstant.STORAGE_PATH + File.separator + fileName);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (Exception e) {
			throw new FileUploadException(he, "fileUpload", 500, ServerConstant.FILE_UPLOAD_EXCEPTION, e);
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		logUtil.logMessage("[fileUpload] File Uploaded successfully" + fileName);
		StringBuilder response = new StringBuilder();
		he.sendResponseHeaders(ServerConstant.RESOURCE_CREATED, response.length());
		OutputStream os1 = he.getResponseBody();
		os1.write("File Uploaded Successfully".getBytes());
		os1.close();
	}
}

