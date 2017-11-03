package com.fdmgroup.jparegsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseReadCommandTest.class, DatabaseWriteCommandTest.class, RegistrationControllerTest.class,
		UserFactoryTest.class, UsersDAOImplTest.class, UserTest.class })
public class AllTests {

}
