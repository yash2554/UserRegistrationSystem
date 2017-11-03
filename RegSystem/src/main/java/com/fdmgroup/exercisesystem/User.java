package com.fdmgroup.exercisesystem;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class User {

	private String userName;
	private String userPass;
	private String userRole;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	public User() {
		PropertyConfigurator.configure("./log4j.properties");
	}

	public User(String username, String password, String role) throws RegisterException {
		PropertyConfigurator.configure("./log4j.properties");
		if (username.trim().length() == 0 || password.trim().length() == 0 || role.trim().length() == 0) {
			errorLog.error("[*]Null values not allowed - User");
			throw new RegisterException("Null values not allowed");
		} else {
			accessLog.trace("[*]User (" + username + "," + password + "," + role + ") New user object created");
			this.userName = username;
			this.userPass = password;
			this.userRole = role;

		}
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserName(String name) throws RegisterException {
		if (name.trim() == null || name.trim().isEmpty()) {
			errorLog.error("[*]" + name + " Null value not allowed - setUserName");
			throw new RegisterException(name + " Null value not allowed");
		} else {
			this.userName = name;

		}
	}

	public void setUserPass(String pass) throws RegisterException {
		if (pass.trim() == null || pass.trim().isEmpty()) {
			errorLog.error("[*]" + pass + " Null value not allowed - setUserPass");
			throw new RegisterException(pass + " Null value not allowed");
		} else {
			this.userPass = pass;

		}
	}

	public void setUserRole(String role) throws RegisterException {
		if (role.trim() == null || role.trim().isEmpty()) {
			errorLog.error("[*]" + role + " Null value not allowed - setUserRole");
			throw new RegisterException(role + " Null value not allowed");
		} else {
			this.userRole = role;
		}
	}

}
