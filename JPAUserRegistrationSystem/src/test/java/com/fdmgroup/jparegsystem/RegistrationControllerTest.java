package com.fdmgroup.jparegsystem;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class RegistrationControllerTest {

	private static RegistrationController rControl;
	private static DatabaseReadCommand dbRead;

	@BeforeClass
	public static void setUp() throws Exception {
		rControl = new RegistrationController();
		dbRead = new DatabaseReadCommand();

		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String sqlUser = "trainee1";
		String sqlPass = "!QAZSE4";
		String query = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);

		try {
			query = "DROP TABLE jpausers_yash";
			pstmt = con.prepareStatement(query);

			if (!pstmt.execute()) {
				System.out.println("[*]System cleaning-up...");
				System.out.println("[*]Database Droped seccessfully...");
				con.commit();
				System.out.println("[*]System cleaned...");

				try {
					query = "CREATE TABLE jpausers_yash(username VARCHAR2(20) PRIMARY KEY,password VARCHAR2(20),role VARCHAR2(20))";
					pstmt = con.prepareStatement(query);

					if (!pstmt.execute()) {
						System.out.println("[*]System setting-up...");
						System.out.println("[*]Database created seccessfully...");
						con.commit();
					} else {
						System.out.println("[*]Database wasn't created...");
					}

				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}

			} else {
				System.out.println("[*]No such database...");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			System.out.println("[*]Database setup seccessfully...");
			if (con != null && !con.isClosed()) {
				System.out.println("[*]Connection closed...");
				con.close();
			}

		}
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_null_as_username_return_exception() throws RegisterException {
		rControl.registerNewUser(null, "test88pass", "test88role");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_empty_as_username_return_exception() throws RegisterException {
		rControl.registerNewUser("", "test8pass", "test8role");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_empty_password_return_exception() throws RegisterException {
		rControl.registerNewUser("test9user", "", "test9role");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_empty_as_role_return_exception() throws RegisterException {
		rControl.registerNewUser("test10user", "test10pass", "");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_spaces_as_username_return_exception() throws RegisterException {
		rControl.registerNewUser("   ", "test11pass", "test11role");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_spaces_as_password_return_exception() throws RegisterException {
		rControl.registerNewUser("test12user", "      ", "test12role");
	}

	@Test(expected = RegisterException.class)
	public void test_registerNewUser_input_spaces_as_role_return_exception() throws RegisterException {
		rControl.registerNewUser("test13user", "test13pass", "     ");
	}

	@Test
	public void test_registerNewUser_input_valid_string_as_username_or_password_or_role_return_insert_into_database()
			throws RegisterException {
		rControl.registerNewUser("test14user", "test14pass", "test14role");
		String actual = dbRead.readUser("test14user").getPassword();
		assertEquals("test14pass", actual);
	}

	@Test
	public void test_registerNewUser_input_valid_string_as_existing_username_return_update_into_database()
			throws RegisterException {
		rControl.registerNewUser("test144user", "test144pass", "test144role");
		rControl.registerNewUser("test144user", "updatetest144pass", "updatetest144role");
		String actual = dbRead.readUser("test144user").getPassword();
		assertEquals("updatetest144pass", actual);
	}

}
