package com.fdmgroup.jparegsystem;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {
	private static User user, userNull;

	@BeforeClass
	public static void setUp() throws Exception {
		userNull = new User();
		user = new User("test15user", "test15pass", "test15role");
	}

	@Test(expected = RegisterException.class)
	public void test_setPassword_method_Input_null_as_username_String_Return_Throw_exception()
			throws RegisterException {
		user.setUsername("");
	}

	@Test(expected = RegisterException.class)
	public void test_setPassword_method_Input_spaces_as_Password_String_Return_Throw_exception()
			throws RegisterException {
		user.setPassword("     ");
	}

	@Test
	public void test_User_Input_UserNull_return_getUsernameNull() {
		String expected = null;
		String actual = userNull.getUsername();
		assertEquals(expected, actual);
	}

	@Test
	public void test_User_Input_User_return_getPass() {
		String expected = "test15pass";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void test_User_Input_User_return_getUsername() {
		String expected = "test15user";
		String actual = user.getUsername();
		assertEquals(expected, actual);
	}

	@Test
	public void test_User_Input_User_return_getRole() {
		String expected = "test15role";
		String actual = user.getRole();
		assertEquals(expected, actual);
	}

}
