package com.fdmgroup.exercisesystem;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

public class TestUserDAO {

	private UsersDAO userDao;
	
	@Before
	public void setUp() throws Exception {
		userDao = new UsersDAO();
		String sqlUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String sqlUser = "trainee1";
		String sqlPass = "!QAZSE4";
		String query = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);

		try {
			query = "DROP TABLE users_yash";
			pstmt = con.prepareStatement(query);
			
			if (!pstmt.execute()) {
				System.out.println("[*]System cleaning-up...");
				System.out.println("[*]Database Droped seccessfully...");
				con.commit();
				System.out.println("[*]System cleaned...");

				try {
					query = "CREATE TABLE users_yash(username VARCHAR2(20) PRIMARY KEY,password VARCHAR2(20),role VARCHAR2(20))";
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
	public void test_addUser_method_inputNull_return_exception() throws SQLException, RegisterException {
		userDao.addUser(null);
	}

	@Test(expected = RegisterException.class)
	public void test_addUser_method_inputempty_return_exception() throws SQLException, RegisterException {
		userDao.addUser(new User(null, null, null));
	}

	@Test(expected = RegisterException.class)
	public void test_getUser_method_inputNull_return_exception() throws SQLException, RegisterException {
		userDao.getUser(null);
		userDao.getUser("");
	}

	@Test(expected = RegisterException.class)
	public void test_removeUser_method_inputNull_return_exception() throws SQLException, RegisterException {
		userDao.removeUser(null);
		userDao.removeUser("");
	}

	@Test(expected = RegisterException.class)
	public void test_updateUser_method_inputNull_return_exception() throws SQLException, RegisterException {
		userDao.updateUser(null);
		userDao.updateUser(new User(null, null, null));
	}
	
	@Test
	public void test_addUser_method_inputNewUser_return_storeUser_into_database() throws SQLException, RegisterException {
		userDao.addUser(new User("testAdd2","testAdd2Pass","testAdd2"));
		String actual = userDao.getUser("testAdd2").getUserPass();
		String expected = "testAdd2Pass";
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void test_addUser_method_inputNewUser_getUserRole_return_storeUser_into_database() throws SQLException, RegisterException {
		userDao.addUser(new User("testAdd1","testAddPass1","testAddAdmin"));
		String actual = userDao.getUser("testAdd1").getUserRole();
		String expected = "testAddAdmin";
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_removeUser_method_inputUser_getUserRole_return_null_deleted_from_database() throws SQLException, RegisterException {
		userDao.addUser(new User("testRemove1","testRemovePass1","testRemoveAdmin"));
		userDao.removeUser("testRemove1");
		String actual = userDao.getUser("testRemove1").getUserRole();
		String expected = null;
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_updateUser_method_inputUser_getUserRole_return_testChangedUpdateAdmin_from_database() throws SQLException, RegisterException {
		userDao.addUser(new User("testUpdate","testUpdatePass","testUpdateAdmin"));
		User change = new User("testUpdate","changedpassword3","changedrole3");
		userDao.updateUser(change);
		String actual = userDao.getUser("testUpdate").getUserRole();
		String expected = "changedrole3";
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void test_listUser_method_inputUsers_getListUser_return_list_from_database() throws SQLException, RegisterException {
		userDao.addUser(new User("testUpdate","testUpdatePass","testUpdateAdmin"));
		userDao.addUser(new User("testUpdate2","testUpdatePass2","testUpdateAdmin2"));
		User change = new User("testUpdate","changedpassword3","changedrole3");
		userDao.updateUser(change);
		int actual = userDao.listUsers().size();
		int expected = 2;
		assertEquals(expected, actual);
	}
	

}
