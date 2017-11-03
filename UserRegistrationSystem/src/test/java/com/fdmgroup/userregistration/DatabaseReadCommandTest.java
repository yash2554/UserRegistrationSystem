package com.fdmgroup.userregistration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.registrationsystem.DatabaseReadCommand;
import com.fdmgroup.registrationsystem.DatabaseWriteCommand;
import com.fdmgroup.registrationsystem.RegisterException;
import com.fdmgroup.registrationsystem.User;

public class DatabaseReadCommandTest {

	DatabaseWriteCommand dbWrite;
	DatabaseReadCommand dbRead;
	User user1, user2, user3;

	@Before
	public void setUp() throws Exception {
		dbWrite = new DatabaseWriteCommand();
		dbRead = new DatabaseReadCommand();
		user1 = new User("y3", "pass3", "role3");
		user3 = new User();
	}

	@Test
	public void test_readUser_Method_Input_anyUser_return_into_file() throws IOException, RegisterException, SQLException {
		user2 = dbRead.readUser("y3");
		assertEquals(user1.getUsername(), user2.getUsername());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_writeUser_Method_Input_anyNullUser_returnException_into_file() throws IOException, RegisterException, SQLException {
		user2 = dbRead.readUser(user3.getUsername());
		assertNull(user2);
	}

}
