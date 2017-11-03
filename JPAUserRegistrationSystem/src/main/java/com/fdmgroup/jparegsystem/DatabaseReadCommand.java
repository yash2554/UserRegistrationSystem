package com.fdmgroup.jparegsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * DatabaseReadCommand class implements the ReadCommand interface This class
 * contains readUser method with User object return type
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class DatabaseReadCommand implements ReadCommand {
	private User user;
	private UsersDAOImpl userImpl;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * DatabaseReadCommand constructor with no argument creates new User and
	 * UsersDAOImpl instances It also contains log4j configuration.
	 */
	public DatabaseReadCommand() {
		PropertyConfigurator.configure("./log4j.properties");
		user = new User();
		userImpl = new UsersDAOImpl();
	}

	/**
	 * This is readUser method which returns an User object by passing username
	 * as String. It also throws RegistrationException if any exception occurs.
	 * 
	 * @param username - String typed argument which will passed by the user.
	 * @return User - Method checks and returns User object with it existing values
	 * 
	 */
	public User readUser(String username) throws RegisterException {
		/**
		 * The if loop checks and throw custom exception if input String is
		 * empty or null
		 * 
		 */
		if (username == null) {
			errorLog.error(
					"[*] \"username\" null argument passed into readUser method invoked from DatabaseReadCommand class");
			throw new RegisterException("\"username\" null not allowed");
		}
		if (username.trim().length() == 0) {
			errorLog.error(
					"[*] \"username\" null argument passed into readUser method invoked from DatabaseReadCommand class");
			throw new RegisterException("\"username\" null not allowed");
		}
		accessLog.trace("[*] \"" + username + "\" passed into readUser method invoked from DatabaseReadCommand class");
		user = userImpl.getUser(username.trim());
		if (user != null) {
			accessLog.info("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
					+ ") returned by readUser method invoked from DatabaseReadCommand class");
		}
		return user;
	}

}
