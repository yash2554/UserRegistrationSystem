package com.fdmgroup.exercisesystem;


import org.junit.Before;
import org.junit.Test;

public class TestUser {

	User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
	}

	@Test(expected = RegisterException.class)
	public void test_setUserName_input_Null_return_exception() throws RegisterException{
		user.setUserName(null);
	}

	@Test(expected =  RegisterException.class)
	public void test_setUserPass_input_Null_return_exception() throws RegisterException{
		user.setUserPass(null);
	}
	
	@Test(expected= RegisterException.class)
	public void test_setUserRole_input_Null_return_exception() throws RegisterException{
	user.setUserRole(null);
	}

}
