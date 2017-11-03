package com.fdmgroup.registrationsystem;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * UserFactory is to designed based on Factory Design Pattern it contains three
 * different methods 1) creatUser 2) getOutCommand 3)getInCommand
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public class UserFactory {
	User user;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * createUser method invoke new User object and it sets properties as
	 * arguments of this method. it returns User object.
	 * 
	 * @param name
	 *            first argument for the User constructor
	 * @param pass
	 *            second argument for the user constructor
	 * @param role
	 *            third argument for the user constructor
	 * @return it returns User object with its attributes
	 */
	public User createUser(String name, String pass, String role) {
		if (name != null && pass != null && role != null) {
			user = new User(name, pass, role);
			accessLog.info(name+" User object created");
		}
		return user;
	}

	/**
	 * 
	 * this method follows Command Design Pattern which has two different
	 * command : 1) WRITE 2) READ
	 * 
	 * @param inCommand
	 *            it check for the "Write" argument
	 * @return this method returns a new FileWriteCommand Object if method gets
	 *         "WRITE" as an argument
	 * @throws IOException
	 *             also throws exception if there is null argument which is
	 *             unexpected
	 */
	public WriteCommand getOutCommand(String inCommand) throws IOException {
		PropertyConfigurator.configure("./log4j.properties");
		if (inCommand == null) {
			errorLog.error("Null \"inCommand\" argument detected for getOutCommand Method");
			return null;
		}
		if (inCommand.equalsIgnoreCase("WRITE")) {
			accessLog.debug("\"WRITE\" argument detected for getInCommand Method");
			return new FileWriteCommand();

		}
		if (inCommand.equalsIgnoreCase("INSERT")) {
			accessLog.debug("\"INSERT\" argument detected for getInCommand Method");
			return new DatabaseWriteCommand();

		}
		return null;

	}

	/**
	 * 
	 * this method follows Command Design Pattern which has two different
	 * command : 1) WRITE 2) READ
	 * 
	 * @param inCommand
	 *            it check for the "READ" argument
	 * @return this method returns a new FileReadCommand Object if method gets
	 *         "READ" as an argument
	 * @throws IOException
	 *             also throws exception if there is null argument which is
	 *             unexpected
	 */
	public ReadCommand getInCommand(String inCommand) throws IOException {
		PropertyConfigurator.configure("./log4j.properties");
		if (inCommand == null) {
			errorLog.error("Null \"inCommand\" argument detected for getInCommand Method");
			return null;
		}
		if (inCommand.equalsIgnoreCase("READ")) {
			accessLog.debug("\"READ\" argument detected for getInCommand Method");
			return new FileReadCommand();

		}
		if (inCommand.equalsIgnoreCase("SELECT")) {
			accessLog.debug("\"SELECT\" argument detected for getInCommand Method");
			return new DatabaseReadCommand();

		}
		return null;

	}

}
