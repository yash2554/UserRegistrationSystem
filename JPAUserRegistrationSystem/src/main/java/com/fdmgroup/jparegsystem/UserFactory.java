package com.fdmgroup.jparegsystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is UserFactory class with its constructor and its createUser method
 * which return user object with its argument/empty
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class UserFactory {
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * This is UserFactory constructor with no argument
	 * 
	 */
	public UserFactory() {
		PropertyConfigurator.configure("./log4j.properties");
	}

	/**
	 * This is createUser method with no argument which returns User object
	 * empty instance
	 * 
	 * @return User object empty instance return
	 * 
	 */
	public User createUser() {
		accessLog.info("[*] Empty user class object created from createUser Method in UserFactory class");
		User user = new User();
		return user;
	}

	/**
	 * 
	 * @param username
	 *            - String type
	 * @param password
	 *            - String type
	 * @param role
	 *            - String type
	 * @return User - it returns user object with its argument values.
	 * @throws RegisterException
	 *             - it also throws an custom exception if unexpected error
	 *             occurs
	 */
	public User createUser(String username, String password, String role) throws RegisterException {

		if (username.trim().length() == 0) {
			errorLog.error("[*] Null \"username\" argument detected in createUser method for UserFactory class");
			throw new RegisterException("Null Username not allowed");
		}
		if (password.trim().length() == 0) {
			errorLog.error("[*] Null \"password\" argument detected in createUser method for UserFactory class");
			throw new RegisterException("Null Password not allowed");
		}
		if (role.trim().length() == 0) {
			errorLog.error("[*] Null \"role\" argument detected in createUser method for UserFactory class");
			throw new RegisterException("Null Role not allowed");
		}
		accessLog.trace("[*] Strings (\"" + username.trim() + ", " + password.trim() + ", " + role.trim()
				+ "\") passed in createUser method for UserFactory class");
		return new User(username.trim(), password.trim(), role.trim());
	}
}
