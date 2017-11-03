package com.fdmgroup.registrationsystem;

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
 *          this class maintains user object and it's properties like
 *          name,pass,role.
 *
 */
public class User {
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");
	private String username;
	private String password;
	private String role;

	public User() {
		super();
	}

	/**
	 * this is User class constructor which holds its user name, password and
	 * role arguments cann't be empty
	 * 
	 * this class also contains getter and setter methods to manage its private
	 * variables
	 * 
	 * @param user
	 *            first argument for the User constructor
	 * @param pass
	 *            second argument for the user constructor
	 * @param role
	 *            third argument for the user constructor
	 */
	public User(String user, String pass, String role) {
		PropertyConfigurator.configure("./log4j.properties");
		if (user != null && pass != null && role != null) {
			this.username = user;
			this.password = pass;
			this.role = role;
			accessLog.debug(user + " New User object created");

		}else{
			errorLog.error(user + " User object not created");
		}

	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
