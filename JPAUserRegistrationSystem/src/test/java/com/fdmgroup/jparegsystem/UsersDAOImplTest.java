package com.fdmgroup.jparegsystem;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsersDAOImplTest {

	private static UsersDAOImpl userImpl;

	@BeforeClass
	public static void setUp() throws Exception {

		userImpl = new UsersDAOImpl();

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
	public void test_addUser_method_input_null_user_object_return_exception() throws RegisterException {
		userImpl.addUser(null);
	}

	@Test(expected = RegisterException.class)
	public void test_addUser_method_input_null_string_username_return_exception() throws RegisterException {
		userImpl.addUser(new User("", "test20pass", "test20role"));
	}

	@Test(expected = RegisterException.class)
	public void test_addUser_method_input_null_string_password_return_exception() throws RegisterException {
		userImpl.addUser(new User("test21user", "      ", "test21role"));
	}

	@Test(expected = RegisterException.class)
	public void test_addUser_method_input_null_string_role_return_exception() throws RegisterException {
		userImpl.addUser(new User("test22user", "test22pass", "   "));
	}

	@Test
	public void test_addUser_method_input_valid_user_object_return_insert_into_database() throws RegisterException {
		userImpl.addUser(new User("test23user", "test23pass", "test23role"));
		String actual = userImpl.getUser("test23user").getRole();
		assertEquals("test23role", actual);
	}

	@Test
	public void test_addUser_then_updateUser_method_input_valid_user_object_return_update_exist_user_from_database()
			throws RegisterException {
		userImpl.addUser(new User("test24user", "test24pass", "test24role"));
		userImpl.updateUser(new User("test24user", "updatetest24pass", "updatetest24role"));
		String actual = userImpl.getUser("test24user").getRole();
		assertEquals("updatetest24role", actual);
	}

	@Test(expected = RegisterException.class)
	public void test_updateUser_method_input_null_user_object_return_exception() throws RegisterException {
		userImpl.updateUser(null);
	}

	@Test(expected = RegisterException.class)
	public void test_updateUser_method_input_null_string_username_return_exception() throws RegisterException {
		userImpl.updateUser(new User("", "test25pass", "test25role"));
	}

	@Test(expected = RegisterException.class)
	public void test_updateUser_method_input_null_string_password_return_exception() throws RegisterException {
		userImpl.updateUser(new User("test26user", "      ", "test26role"));
	}

	@Test(expected = RegisterException.class)
	public void test_updateUser_method_input_null_string_role_return_exception() throws RegisterException {
		userImpl.updateUser(new User("test27user", "test27pass", "   "));
	}

}
