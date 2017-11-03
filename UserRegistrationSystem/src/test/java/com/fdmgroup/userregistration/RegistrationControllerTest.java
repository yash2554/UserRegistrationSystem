package com.fdmgroup.userregistration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.registrationsystem.FileReadCommand;
import com.fdmgroup.registrationsystem.RegisterException;
import com.fdmgroup.registrationsystem.RegistrationController;
import com.fdmgroup.registrationsystem.User;

public class RegistrationControllerTest {

	RegistrationController regCon;
	FileReadCommand fileRead;
	User user = new User("y2", "pass2", "role2");

	@After
	public void delete() throws IOException {
		String savedData = "./user_information.txt";
		File deleteFile = new File(savedData);
		deleteFile.delete();
		System.out.println("system cleaning-up");
		deleteFile.createNewFile();
		System.out.println("system cleaned");
	}

	@Before
	public void setUp() throws Exception {
		regCon = new RegistrationController();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_RegistrationController_registerNewUser_InputNullString_return_exception()
			throws IOException, RegisterException, SQLException {
		regCon.registerNewUser("", "pass2", "role2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_RegistrationController_registerNewUser_InputAllNullString_return_exception()
			throws IOException, RegisterException, SQLException {
		regCon.registerNewUser("", "", "");
	}

}
