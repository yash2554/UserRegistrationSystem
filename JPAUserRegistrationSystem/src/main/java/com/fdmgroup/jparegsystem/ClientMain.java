package com.fdmgroup.jparegsystem;

/**
 * User Registration System Using JPA This application is providing
 * functionality of simple registration system which build in Maven. The partial
 * project developed with command design pattern and MVC architectural pattern.
 * It Demonstrate Database IO using JPA.
 * The application also contains log4j for logging the errors and it records every log into two different log files.
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */ 
public class ClientMain {
	/**
	 * This method is the main method for this application.
	 * 
	 * @param args
	 *            - Main class argument
	 * @throws RegisterException
	 *             - it throws CustomExceptio
	 */
	public static void main(String[] args) throws RegisterException {

		RegistrationController controller = new RegistrationController();
		controller.registerNewUser("username1", "                 password1", "role1");
		controller.registerNewUser("username2", "password2", "role2");
		controller.registerNewUser("username3", "password3          ", "role3");
		controller.registerNewUser("username4", "password4", "role4");
		controller.registerNewUser("username5        ", "password5", "role5");

	}

}
