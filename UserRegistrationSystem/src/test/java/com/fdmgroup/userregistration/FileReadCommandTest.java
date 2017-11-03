package com.fdmgroup.userregistration;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.registrationsystem.FileReadCommand;
import com.fdmgroup.registrationsystem.FileWriteCommand;
import com.fdmgroup.registrationsystem.User;

public class FileReadCommandTest {

	FileWriteCommand fileWrite;
	FileReadCommand fileRead;
	User user1, user2, user3;

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
		fileWrite = new FileWriteCommand();
		fileRead = new FileReadCommand();
		user1 = new User("y3", "pass3", "role3");
		user3 = new User();
	}

	@Test
	public void test_readUser_Method_Input_anyUser_return_into_file() throws IOException {
		user2 = fileRead.readUser("y3");
		assertEquals(user1.getUsername(), user2.getUsername());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_writeUser_Method_Input_anyNullUser_returnException_into_file() throws IOException {
		user2 = fileRead.readUser(user3.getUsername());
		assertNull(user2);
	}

}
