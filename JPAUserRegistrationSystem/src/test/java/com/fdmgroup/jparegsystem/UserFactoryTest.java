package com.fdmgroup.jparegsystem;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserFactoryTest {

	private static UserFactory userFact;

	@BeforeClass
	public static void setUp() throws Exception {
		userFact = new UserFactory();

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
	public void test_createUser_method_input_empty_string_as_username_return_exception() throws RegisterException {
		userFact.createUser("", "test16pass", "test16role");
	}

	@Test(expected = RegisterException.class)
	public void test_createUser_method_input_empty_string_as_password_return_exception() throws RegisterException {
		userFact.createUser("test17user", "     ", "test17role");
	}

	@Test(expected = RegisterException.class)
	public void test_createUser_method_input_empty_string_as_role_return_exception() throws RegisterException {
		userFact.createUser("test18user", "test18pass", "           ");
	}

	@Test
	public void test_createUser_method_input_valid_string_return_new_user_object() throws RegisterException {
		User input = userFact.createUser("test19user", "test19pass", "test19role");
		String actual = input.getPassword();
		String expected = "test19pass";
		assertEquals(expected, actual);
	}

}
