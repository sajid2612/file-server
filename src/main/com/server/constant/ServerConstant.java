package main.com.server.constant;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ServerConstant {

	//Server Configs
	public static final File WEB_ROOT = new File(".");
	public static final String DEFAULT_FILE = "resources/index.html";
	public static final String FILE_NOT_FOUND = "resources/not_found.html";
	public static final String METHOD_NOT_SUPPORTED = "resources/not_supported.html";
	public static final int PORT = 7777;
	public static final List<String> SUPPORTED_METHODS = Arrays.asList("GET", "POST", "DELETE");
	public static final String SERVER_PATH = System.getProperty("user.home") + File.separator + "Desktop/wp";
	public static final String LOG_PATH = "/file-server/logs/server.log";
	public static final String ERROR_LOG_PATH = "/file-server/logs/error.log";
	public static final String CONFIG_PATH = "/file-server/config/server_config.properties";
	public static final String STORAGE_PATH = "/storage";

	//Http Method types
	public static final String GET_METHOD = "GET";
	public static final String POST_METHOD = "POST";
	public static final String DELETE_METHOD = "DELETE";

	//Htttp status codes
	public static final int BAD_REQUEST = 400;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int NOT_FOUND = 404;
	public static final int CONFLICT_FOUND = 409;
	public static final int RESOURCE_CREATED = 201;
	public static final int DELETED = 202;


	//Error messages
	public static final String METHOD_NOT_ALLOWED = "Request method type now allowed";
	public static final String CONTENT_TYPE_MISSING = "Header does not have content-type";
	public static final String FILE_UPLOAD_EXCEPTION = "Error occurred while uploading the file";
	public static final String FILE_DELETE_EXCEPTION = "Error occurred while deleting the file";
	public static final String FILE_TO_BE_DELETED_DOES_NOT_EXIST = "File to be deleted does not exist";
	public static final String FILE_ALREADY_EXISTS = "Conflict found, file already exists";
}
