package com.fdmgroup.jparegsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * DatabaseWriteCommand class implements the WriteCommand interface This class
 * contains writeUser method with void return type
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class DatabaseWriteCommand implements WriteCommand {

	private UsersDAOImpl userImpl;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * This is DatabaseWriteCommand constructor with no argument. Constructor
	 * also contains log4j configuration
	 */
	public DatabaseWriteCommand() {
		PropertyConfigurator.configure("./log4j.properties");
		userImpl = new UsersDAOImpl();
	}

	/**
	 * This is writeUser method which runs addUser method from UsersDAOImpl It
	 * checks the argument if User object is valid or not. It also log an error
	 * and throws custom exception.
	 * 
	 * @param user
	 *            - user object passed by the user.
	 * 
	 * 
	 */
	public void writeUser(User user) throws RegisterException {
		/**
		 * The if loop checks and throw custom exception if input User object is
		 * empty or null
		 * 
		 */
		if (user == null) {
			errorLog.error(
					"[*] \"User Object\" null argument passed into writeUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"User\" null not allowed");
		}
		if (user.getUsername().trim().length() == 0) {
			errorLog.error(
					"[*] \"username\" null argument passed into writeUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"username\" null not allowed");
		}
		if (user.getPassword().trim().length() == 0) {
			errorLog.error(
					"[*] \"password\" null argument passed into writeUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"password\" null not allowed");
		}
		if (user.getRole().trim().length() == 0) {
			errorLog.error(
					"[*] \"role\" null argument passed into writeUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"role\" null not allowed");
		}
		accessLog.trace("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
				+ ") passed into writeUser method invoked from DatabaseWriteCommand class");
		userImpl.addUser(user);
	}

	/**
	 * This is writeUser method which runs updateUser method from UsersDAOImpl
	 * It checks the argument if User object is valid or not. It also log an
	 * error and throws custom exception.
	 * 
	 * @param user
	 *            - user object passed by the user.
	 * 
	 * 
	 */
	public void writeUpdateUser(User user) throws RegisterException {
		/**
		 * The if loop checks and throw custom exception if input User object is
		 * empty or null
		 * 
		 */
		if (user == null) {
			errorLog.error(
					"[*] \"User Object\" null argument passed into writeUpdateUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"User\" null not allowed");
		}
		if (user.getUsername().trim().length() == 0) {
			errorLog.error(
					"[*] \"username\" null argument passed into writeUpdateUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"username\" null not allowed");
		}
		if (user.getPassword().trim().length() == 0) {
			errorLog.error(
					"[*] \"password\" null argument passed into writeUpdateUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"password\" null not allowed");
		}
		if (user.getRole().trim().length() == 0) {
			errorLog.error(
					"[*] \"role\" null argument passed into writeUpdateUser method invoked from DatabaseWriteCommand class");
			throw new RegisterException("\"role\" null not allowed");
		}
		accessLog.trace("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
				+ ") passed into writeUpdateUser method invoked from DatabaseWriteCommand class");
		userImpl.updateUser(user);
	}

}
