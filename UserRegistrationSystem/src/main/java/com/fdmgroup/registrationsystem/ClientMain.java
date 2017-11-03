package com.fdmgroup.registrationsystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * this is ClientMain class which has main method which is going to be used by
 * end user.
 * 
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public class ClientMain {
	/**
	 * This is controller object RegistrationController and then pass String
	 * arguments with its method registerNewUser.
	 * 
	 */
	private static RegistrationController controller = new RegistrationController();

	/**
	 * The application starts from here.
	 * 
	 * @param args
	 *            the main method argument
	 * @throws IOException
	 *             the IO Exception handled by main method
	 * @throws SQLException 
	 * @throws RegisterException 
	 */
	public static void main(String[] args) throws IOException, RegisterException, SQLException {

		ClientMain fun = new ClientMain();

		System.out.println("Registration System\n\n");
		controller.registerNewUser("Yash", "Password", "Admin");
		controller.registerNewUser("Yash2", "Password", "Admin");
		controller.registerNewUser("Yash3", "Password", "Admin");
		controller.registerNewUser("Yash4", "Password", "Admin");
		controller.registerNewUser("Yash1", "Password1", "Admin1");
		controller.registerNewUser("Yash5", "Password", "Admin");
		controller.registerNewUser("Yash2", "Password", "Admin");
		controller.registerNewUser("Yash3", "Password", "Admin");
		controller.registerNewUser("Yash8", "Password", "Admin");
		controller.registerNewUser("Yash9", "Password", "Admin");
		controller.registerNewUser("Yash10", "Password", "Admin");
		controller.registerNewUser("Yash65", "98996", "User");

		int i = 0;
		while (i > 0) {
			controller.registerNewUser(fun.getSaltString(), fun.getSaltString(), fun.getSaltString());
			i--;
		}

	}

	/**
	 * this method is just for fun testing which generates random string when it
	 * is invoked.
	 * 
	 * @return random string for testing purpose
	 */
	protected String getSaltString() {
		String SALTCHARS = "abcdef";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
