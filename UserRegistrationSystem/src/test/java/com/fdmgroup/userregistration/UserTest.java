package com.fdmgroup.userregistration;


import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.registrationsystem.User;


public class UserTest {

	
	@After
	public void delete() throws IOException {
		String savedData = "./user_information.txt";
		File deleteFile = new File(savedData);
		deleteFile.delete();
		System.out.println("system cleaning-up");
		deleteFile.createNewFile();
		System.out.println("system cleaned");
	}
	
	
	String savedData="./user_information.txt";
	User user,userNull;
	
	@Before
	public void setUp() throws Exception {
	userNull = new User();
	user = new User("y","pass","role");
	}

	@Test
	public void test_User_Input_UserNull_return_getUsernameNull() {
		String expected = null;
		String actual = userNull.getUsername();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_User_Input_User_return_getPass() {
		String expected = "pass";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_User_Input_User_return_getUsername() {
		String expected = "y";
		String actual = user.getUsername();
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_User_Input_User_return_getRole() {
		String expected = "role";
		String actual = user.getRole();
		assertEquals(expected, actual);
	}

}
