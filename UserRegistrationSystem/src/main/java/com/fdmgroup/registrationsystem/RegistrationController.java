package com.fdmgroup.registrationsystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * this is RegistrationController class which has registerNewUser method.
 * 
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public class RegistrationController {

	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");
	WriteCommand writeOut;
	ReadCommand readIn;
	UserFactory userFact = new UserFactory();
	User exist;
	private Scanner opt;

	/**
	 * 
	 * @param name
	 *            this is the first argument for the registerNewUser method
	 *            String type
	 * @param pass
	 *            this is the second argument for the registerNewUser method
	 *            String type
	 * @param role
	 *            this is the third argument registerNewUser method String type
	 * @throws IOException
	 *             this is exception which throws at IO problem
	 * @throws SQLException
	 * @throws RegisterException
	 */
	public void registerNewUser(String name, String pass, String role)
			throws IOException, RegisterException, SQLException {
		PropertyConfigurator.configure("./log4j.properties");
		/**
		 * it checks if name is not null.
		 * 
		 */
		if (name == null || name.isEmpty()) {
			errorLog.error("Null \"name\" argument detected for registerNewUser Method");
			throw new IllegalArgumentException("Username cann't be null");
		}
		/**
		 * it checks if pass is not null.
		 * 
		 */
		if (pass == null || pass.isEmpty()) {
			errorLog.error("Null \"pass\" argument detected for registerNewUser Method");
			throw new IllegalArgumentException("Password cann't be null");
		}
		/**
		 * it checks if role is not null.
		 * 
		 */
		if (role == null || role.isEmpty()) {
			errorLog.error("Null \"role\" argument detected for registerNewUser Method");
			throw new IllegalArgumentException("Role cann't be null");
		}

		opt = new Scanner(System.in);
		System.out.println("\n1. To-File \n2. To-DB\n");
		int option = opt.nextInt();
		if (option == 1) {

			accessLog.debug("\"" + name + "\"- Checking for existing user into database");
			readIn = userFact.getInCommand("read");
			exist = readIn.readUser(name);
			/**
			 * it checks if user is already exist in database.
			 * 
			 */
			if (exist.getUsername() != null) {
				errorLog.error("Duplicate argument \"" + name + "\" detected for registerNewUser Method");
				System.out.println(name + " Already Exists");
			} else {
				accessLog.debug("\"" + name + "\"- Creating a new user into database");
				User user = userFact.createUser(name, pass, role);
				writeOut = userFact.getOutCommand("write");
				writeOut.writeUser(user);

			}

		} else if (option == 2) {

			accessLog.debug("\"" + name + "\"- Checking for existing user into database");
			readIn = userFact.getInCommand("select");
			exist = readIn.readUser(name);
			/**
			 * it checks if user is already exist in database.
			 * 
			 */
			if (exist.getUsername() != null) {
				errorLog.error("Duplicate argument \"" + name + "\" detected for registerNewUser Method");
				System.out.println(name + " Already Exists");
			} else {
				accessLog.debug("\"" + name + "\"- Creating a new user into database");
				User user = userFact.createUser(name, pass, role);
				writeOut = userFact.getOutCommand("insert");
				writeOut.writeUser(user);
			}

		} else {
			errorLog.error(option + "- Invalid option detected for registerNewUser Method");
			System.out.println(option + "- Invalid option");
		}
	}
}
