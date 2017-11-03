package com.fdmgroup.jparegsystem;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseWriteCommandTest {

	private static DatabaseReadCommand dbRead;
	private static DatabaseWriteCommand dbWrite;

	@BeforeClass
	public static void setUp() throws Exception {
		dbRead = new DatabaseReadCommand();
		dbWrite = new DatabaseWriteCommand();

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
	public void test_writeUser_method_Input_null_as_username_String_Return_Throw_exception() throws RegisterException {
		dbWrite.writeUser(new User(" ", "test4pass", "test4role"));
	}

	@Test(expected = RegisterException.class)
	public void test_writeUser_method_Input_spaces_as_username_String_Return_Throw_exception()
			throws RegisterException {
		dbWrite.writeUser(new User("         ", "test5pass", "test5role"));
	}

	@Test(expected = RegisterException.class)
	public void test_writeUser_method_Input_spaces_as_password_String_Return_Throw_exception()
			throws RegisterException {
		dbWrite.writeUser(new User("test55user", "       ", "test55role"));
	}

	@Test(expected = RegisterException.class)
	public void test_writeUser_method_Input_spaces_as_role_String_Return_Throw_exception() throws RegisterException {
		dbWrite.writeUser(new User("test56user", "test56pass", "                   "));
	}

	@Test
	public void test_writeUser_method_Input_exist_username_String_Return_user_username() throws RegisterException {
		User expected = new User("test6user", "test6pass", "test6role");
		dbWrite.writeUser(expected);
		User actual = dbRead.readUser("test6user");
		assertEquals(expected.getUsername(), actual.getUsername());
	}

	@Test
	public void test_writeUpdateUser_method_Input_exist_username_String_Return_user_username()
			throws RegisterException {
		User input = new User("test7user", "test7pass", "test7role");
		User expected = new User("test7user", "test7Updatepass", "test7Updaterole");
		dbWrite.writeUser(input);
		dbWrite.writeUpdateUser(expected);
		User actual = dbRead.readUser("test7user");
		assertEquals(expected.getPassword(), actual.getPassword());
	}

}
