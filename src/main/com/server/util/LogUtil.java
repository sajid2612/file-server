package main.com.server.util;

import static main.com.server.constant.ServerConstant.ERROR_LOG_PATH;
import static main.com.server.constant.ServerConstant.LOG_PATH;
import static main.com.server.constant.ServerConstant.SERVER_PATH;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {
	static Logger logger = null;
	static Logger errorLogger = null;
	static Level LOG_LEVEL=Level.INFO;

	public void logMessage(String message) {
		if (logger == null) {
			init("consoleLogger");
		}
		logger.log(Level.INFO, message);
	}

	public void logErrorMessage(String message) {
		if (errorLogger == null) {
			init("errorLogger");
		}
		errorLogger.log(Level.SEVERE, message);
	}

	public void updateLogStatus(String logLevel) {
		try {
			LOG_LEVEL = Level.parse(logLevel);
			if (logger != null) {
				System.out.println("Log level changed to >> " + logLevel);
				logger.setLevel(LOG_LEVEL);
			} else {
				init("consoleLogger");
			}
		} catch (Exception iae) {
			System.out.println("Exception Occurred>>" +iae.getMessage());
		}
	}

	private void init(String loggerName) {
		FileHandler fh;
		try {
			String logPath = SERVER_PATH + File.separator + (loggerName.equals("consoleLogger") ? LOG_PATH : ERROR_LOG_PATH);
			File file = new File(logPath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			fh = new FileHandler(logPath, true);
			fh.setFormatter(new SimpleFormatter());
			if (loggerName.equals("consoleLogger")) {
				logger = Logger.getLogger(loggerName);
				logger.setLevel(LOG_LEVEL);
				logger.addHandler(fh);
				//logger.addHandler(new ConsoleHandler());
			} else if (loggerName.equals("errorLogger")) {
				errorLogger = Logger.getLogger(loggerName);
				errorLogger.addHandler(fh);
				//errorLogger.addHandler(new ConsoleHandler());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
