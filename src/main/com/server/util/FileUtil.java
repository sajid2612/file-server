package main.com.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];

		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
		}
		return fileData;
	}

	public static void uploadFile(String sourceFile) {
		byte[] array = new byte[50];
		try {
			FileInputStream sourceFileStream = new FileInputStream(sourceFile);
			FileOutputStream destFileStream = new FileOutputStream("newFile");

			// reads all data from input.txt
			sourceFileStream.read(array);

			// writes all data to newFile
			destFileStream.write(array);
			System.out.println("The input.txt file is copied to newFile.");

			// closes the stream
			sourceFileStream.close();
			destFileStream.close();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}
}
