package com.fdmgroup.registrationsystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 *          this class implements WriteCommand interface which has writeUser
 *          method
 */
public class FileWriteCommand implements WriteCommand {
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");
	String FILENAME;
	BufferedWriter bw = null;
	FileWriter fw = null;

	/**
	 * this method take string argument as User object and it write into the
	 * file.
	 * 
	 * it also throws exceptions for null input argument as well as it logs
	 * registration activities.
	 * 
	 */
	public void writeUser(User user) throws IOException {
		PropertyConfigurator.configure("./log4j.properties");
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			errorLog.error("Null argument detected for writeUser Method");
			throw new NullPointerException("User cann't be null");
		} else {

			FILENAME = "./user_information.txt";
			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			bw.write(user.getUsername() + "::" + user.getPassword() + "::" + user.getRole() + "\n");
			System.out.println(user.getUsername() + " Registerd Successfully");
			accessLog.debug("new user=\"" + user.getUsername() + "\" registration detected for writeUser Method");
			if (bw != null)
				accessLog.info("BufferWriter closed");
			bw.close();
			if (fw != null)
				accessLog.info("FileWriter closed");
			fw.close();

		}
	}

}
