package com.fdmgroup.userregistration;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.registrationsystem.User;
import com.fdmgroup.registrationsystem.UserFactory;

public class UserFactoryTest {

	String savedData = "./user_information.txt";
	UserFactory userFact;

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
		userFact = new UserFactory();
	}

	@Test
	public void test_UserFactory_createNewUser_InputString_returnUser_checkforUsername() {
		User expected = new User("y1", "pass1", "role1");
		User actual = userFact.createUser("y1", "pass1", "role1");
		assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	public void test_UserFactory_createNewUser_InputString_returnUser_checkforPassword() {
		User expected = new User("y1", "pass1", "role1");
		User actual = userFact.createUser("y1", "pass1", "role1");
		System.out.println(expected.getUsername());
		assertEquals(expected.getPassword(), actual.getPassword());
	}

	@Test
	public void test_UserFactory_createNewUser_InputString_returnUser_checkforRole() {
		User expected = new User("y11", "pass11", "role11");
		User actual = userFact.createUser("y11", "pass11", "role11");
		assertEquals(expected.getRole(), actual.getRole());
	}

}
