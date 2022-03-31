import static main.com.server.constant.ServerConstant.PORT;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import main.com.server.handlers.DeleteFileHandler;
import main.com.server.handlers.GetConfigHandler;
import main.com.server.handlers.GetFileListHandler;
import main.com.server.handlers.PutConfigHandler;
import main.com.server.handlers.PostFileUploadHandler;
import main.com.server.handlers.RootHandler;


public class FileServerApp {

	public static void main(String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
			System.out.println("server started at " + PORT);
			server.createContext("/", new RootHandler());
			server.createContext("/listFiles", new GetFileListHandler());
			server.createContext("/uploadFile", new PostFileUploadHandler());
			server.createContext("/deleteFile", new DeleteFileHandler());
			server.createContext("/changeLogLevel", new PutConfigHandler());
			server.createContext("/getConfig", new GetConfigHandler());
			server.setExecutor(null);
			server.start();
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}
}
