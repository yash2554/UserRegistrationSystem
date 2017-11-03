package com.fdmgroup.registrationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * this class implements ReadCommand interface which has readUser method
 * 
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public class FileReadCommand implements ReadCommand {
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");
	String FILENAME;
	BufferedReader br = null;
	FileReader fr = null;

	/**
	 * this method take string argument as User name and check if that user is
	 * already exist in the database or not it user already exist then it
	 * returns user object which it's existing information otherwise empty user
	 * object
	 * 
	 * it also throws exceptions for null input argument as well as it logs
	 * registration activities.
	 * 
	 */
	public User readUser(String username) throws IOException {
		PropertyConfigurator.configure("./log4j.properties");
		User user = new User();
		if (username == null || username.isEmpty()) {
			accessLog.trace("Null argument detected for readUser Method");
			errorLog.error("Null argument detected for readUser Method");
			throw new IllegalArgumentException("Username cann't be null.");
		} else {
			
			FILENAME = "./user_information.txt";
			File file = new File(FILENAME);
			if (!file.exists()) {
				accessLog.debug("user_information.txt file doesn't exist");
				file.createNewFile();
				accessLog.info("user_information.txt file created");
			}
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] strArr = sCurrentLine.split("::");
				for (String str : strArr) {
					if (username.equals(str)) {
						// System.out.println(sCurrentLine);
						accessLog.info("User("+strArr[0]+","+strArr[1]+","+strArr[2]+") New user object created");
						user.setUsername(strArr[0]);
						user.setPassword(strArr[1]);
						user.setRole(strArr[2]);
					}
				}

			}

			if (br != null)
				accessLog.info("BufferReader closed");
			br.close();

			if (fr != null)
				accessLog.info("FileReader closed");
			fr.close();
			return user;

		}
	}
}
