package com.fdmgroup.jparegsystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is an (User) Entity class for this JPA application. which contains
 * constructor and its private String variable with its getter and setter
 * methods.
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
@Entity
@Table(name = "jpausers_yash")
public class User {

	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;

	/**
	 * The User class constructor with no arguments. it contains log4j
	 * Configuration.
	 * 
	 */
	public User() {
		PropertyConfigurator.configure("./log4j.properties");
	}

	/**
	 * User Constructor with its three argument which set the private string
	 * variable of Entity class.
	 * 
	 * @param username
	 *            - String type which can not be null
	 * @param password-
	 *            String type which can not be null
	 * @param role-
	 *            String type which can not be null
	 * @throws RegisterException
	 *             - it also throw a custom exception if unexpected error occurs
	 */
	public User(String username, String password, String role) throws RegisterException {
		/**
		 * The if loop checks and throw custom exception if input Strings are
		 * empty or null
		 * 
		 */
		if (username == null || username.trim().length() == 0) {
			errorLog.error("[*] Null \"username\" argument detected in User constructor for User class");
			throw new RegisterException("Null Username not allowed");
		}
		if (password == null || password.trim().length() == 0) {
			errorLog.error("[*] Null \"password\" argument detected in User constructor for User class");
			throw new RegisterException("Null Password not allowed");
		}
		if (role == null || role.trim().length() == 0) {
			errorLog.error("[*] Null \"role\" argument detected in User constructor for User class");
			throw new RegisterException("Null Role not allowed");
		}
		accessLog.trace("[*] User (\"" + username + ", " + password + ", " + role
				+ "\") passed in User constructor for User class");
		this.username = username.trim();
		this.password = password.trim();
		this.role = role.trim();
	}

	/**
	 * getUsername is getter method for the username String variable
	 * 
	 * @return username from User class
	 */
	public String getUsername() {
		accessLog.info("[*] getUsername method invoked from User class");
		return username;
	}

	/**
	 * setUsername is setter method for the username String variable. it sets
	 * the string value to the username variable
	 * 
	 * @param username
	 *            - String type
	 * @throws RegisterException
	 *             - throws an exception if unexpected error occurs
	 */
	public void setUsername(String username) throws RegisterException {
		if (username.trim().length() == 0) {
			errorLog.error("[*] Null \"username\" argument detected in setUsername method for User class");
			throw new RegisterException("Null Username not allowed");
		}
		this.username = username.trim();
	}

	/**
	 * getPassword is getter method for the password String variable
	 * 
	 * @return password from User class
	 */
	public String getPassword() {
		accessLog.info("[*] getPassword method invoked from User class");
		return password;
	}

	/**
	 * setPassword is setter method for the password String variable. it sets
	 * the string value to the password variable
	 * 
	 * @param password
	 *            - String type
	 * @throws RegisterException
	 *             - throws an exception if unexpected error occurs
	 */
	public void setPassword(String password) throws RegisterException {
		if (password.trim().length() == 0) {
			errorLog.error("[*] Null \"password\" argument detected in setPassword method for User class");
			throw new RegisterException("Null Password not allowed");
		}
		this.password = password.trim();
	}

	/**
	 * getRole is getter method for the role String variable
	 * 
	 * @return role from User class
	 */
	public String getRole() {
		accessLog.info("[*] getRole method invoked from User class");
		return role;
	}

	/**
	 * setRole is setter method for the role String variable. it sets the string
	 * value to the role variable
	 * 
	 * @param role
	 *            - String type
	 * @throws RegisterException
	 *             - throws an exception if unexpected error occurs
	 */
	public void setRole(String role) throws RegisterException {
		if (role.trim().length() == 0) {
			errorLog.error("[*] Null \"role\" argument detected in setRole for User class");
			throw new RegisterException("Null Role not allowed");
		}
		this.role = role.trim();
	}

}
