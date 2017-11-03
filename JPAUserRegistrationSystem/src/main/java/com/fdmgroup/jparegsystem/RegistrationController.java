package com.fdmgroup.jparegsystem;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is RegistrationController class contains registerNewUser method MVC
 * design pattern applied and it depends on DatabaseReadCommand,
 * DatabaseWriteCommand and UserFactory.
 * 
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class RegistrationController {
	private ReadCommand readIn;
	private WriteCommand writeOut;
	private UserFactory userFact;
	private Scanner scan;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * This is RegistrationController constructor with no arguments When it
	 * invokes it creates and instance of DatabaseReadCommand,
	 * DatabaseWriteCommand and UserFactory. it also contains log4j
	 * configuration.
	 * 
	 */
	public RegistrationController() {
		PropertyConfigurator.configure("./log4j.properties");
		readIn = new DatabaseReadCommand();
		writeOut = new DatabaseWriteCommand();
		userFact = new UserFactory();
	}

	/**
	 * 
	 * @param username
	 *            - String typed parameter
	 * @param password-
	 *            String typed parameter
	 * @param role-
	 *            String typed parameter
	 * @throws RegisterException
	 *             - Throws and custom exception from RegisterException
	 */
	public void registerNewUser(String username, String password, String role) throws RegisterException {
		/**
		 * The if loop checks and throw custom exception if input Strings are
		 * empty or null
		 * 
		 */
		if (username == null || username.trim().length() == 0) {
			errorLog.error(
					"[*] Null \"username\" argument detected in registerNewUser method for RegistrationController class");
			throw new RegisterException("Null Username not allowed");
		}
		if (password == null || password.trim().length() == 0) {
			errorLog.error(
					"[*] Null \"password\" argument detected in registerNewUser method for RegistrationController class");
			throw new RegisterException("Null Password not allowed");
		}
		if (role == null || role.trim().length() == 0) {
			errorLog.error(
					"[*] Null \"role\" argument detected in registerNewUser method for RegistrationController class");
			throw new RegisterException("Null Role not allowed");
		}
		accessLog.trace("[*] User (\"" + username.trim() + ", " + password.trim() + ", " + role.trim()
				+ "\") passed in registerNewUser method for RegistrationController class");

		User exist = readIn.readUser(username.trim());
		if (exist == null) {
			User newUser = userFact.createUser(username.trim(), password.trim(), role.trim());
			accessLog.trace("[*] " + newUser.getUsername()
					+ "- new user object created in registerNewUser method for RegistrationController class");
			if (newUser != null) {
				writeOut.writeUser(newUser);
				accessLog.info("[*] " + newUser.getUsername() + "- new user record stored into database successfully");
			}
		} else {
			accessLog.trace("[*] " + exist.getUsername()
					+ "- duplicate user object passed in registerNewUser method for RegistrationController class");
			System.out.println("\"" + exist.getUsername() + "\" already exists in our record");
			System.out.println("Do you want to update? ((1 for Yes)/(2 for No))");

			scan = new Scanner(System.in);
			int choice = scan.nextInt();
			if (choice == 1) {
				accessLog.trace("[*] " + exist.getUsername()
						+ "- updated user object passed in registerNewUser method for RegistrationController class");
				User updateUser = userFact.createUser(username.trim(), password.trim(), role.trim());
				accessLog.trace("[*] " + updateUser.getUsername()
						+ "- updated user object created in registerNewUser method for RegistrationController class");
				if (updateUser != null) {
					writeOut.writeUpdateUser(updateUser);
					accessLog.info(
							"[*] " + updateUser.getUsername() + "- user record updated into database successfully");
				}
			} else {
				accessLog.error("\"" + exist.getUsername() + "\" user wasn't updated into record");
				System.out.println("\"" + exist.getUsername() + "\" user wasn't updated");
				return;
			}
		}

	}

	/**
	 * This is a getUserFactory method to get UserFactory which returns
	 * UserFactory from the class.
	 * 
	 * @return UserFactory - it returns UserFactory instance from this class.
	 */
	public UserFactory getUserFactory() {
		accessLog.info("[*] getUserFactory method invoked from RegistrationController class");
		return userFact;
	}

	/**
	 * This is a setUserFactory method to set UserFactory which returns nothing.
	 * but it passes userFactory from the class and setUserFactorty value.
	 * 
	 * @param userFactory
	 *            - UserFactory instance as argument
	 * 
	 */
	public void setUserFactory(UserFactory userFactory) {
		accessLog.info(
				"[*] " + userFactory + " passed into setUserFactory method invoked from RegistrationController class");
		this.userFact = userFactory;
	}

}
