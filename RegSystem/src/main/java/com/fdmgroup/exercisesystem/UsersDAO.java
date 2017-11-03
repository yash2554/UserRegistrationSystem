package com.fdmgroup.exercisesystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UsersDAO implements Users {

	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");
	String sqlUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	String sqlUser = "trainee1";
	String sqlPass = "!QAZSE4";
	String query = "";
	Connection con = null;
	Statement stmt = null;
	PreparedStatement statement = null;
	CallableStatement cstmt = null;
	ResultSet rset = null;

	public UsersDAO() throws RegisterException {
		PropertyConfigurator.configure("./log4j.properties");
		System.out.println("[*]Registering driver...");
		accessLog.trace("[*]Registering driver");
		try {
			System.out.println("[*]Connecting to database...");
			accessLog.trace("[*]Connecting to database...");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			System.out.println("[*]Database connected successfully...");
			accessLog.trace("[*]Database connected successfully...");
		} catch (SQLException e) {
			errorLog.error(e.getMessage());
			
		}

	}

	public void addUser(User user) throws RegisterException, SQLException{
		if (user == null) {
			errorLog.error("[*]Null user object not allowed - addUser");
			throw new RegisterException("Null user object not allowed");
		}

		try {
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "{call add_user(?,?,?)}";
			String username = user.getUserName();
			String password = user.getUserPass();
			String role = user.getUserRole();

			System.out.println("[*]Creating preparestatement...");
			accessLog.trace("[*]Creating preparestatement...- addUser");
			cstmt = con.prepareCall(query);
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			cstmt.setString(3, role);

			if (!cstmt.execute()) {
				System.out.println("[*]Inserted seccessfully...");
				accessLog.trace("[*]Inserted seccessfully...- addUser");
				con.commit();
			} else {
				System.out.println("[*]No such record...");
				errorLog.info("[*]No such record...-Insert Error - addUser");
			}
		} catch (SQLException sql) {
			errorLog.error(sql.getMessage());
		} finally {
			if (con != null && !con.isClosed()) {
				accessLog.trace("[*]Connection closed... - addUser");
				System.out.println("[*]Connection closed...");
				con.close();
			}

		}

	}

	public User getUser(String username) throws SQLException, RegisterException {
		if (username.trim().length()==0) {
			errorLog.error("[*]Null string not allowed - getUser");
			throw new RegisterException(username + " Null string not allowed");
		}
		User user = new User();
		try {
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "SELECT username,password,role FROM users_yash WHERE username ='" + username + "'";
			System.out.println("[*]Creating statement...");
			accessLog.trace("[*]Creating statement... - getUser");
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			System.out.println("[*]Looking for the record...");
			accessLog.trace("[*]Looking for the record... - getUser");

			while (rset.next()) {
				if (rset.getString("username") == username || rset.getString("username").equals(username)) {
					user.setUserName(rset.getString("username"));
					user.setUserPass(rset.getString("password"));
					user.setUserRole(rset.getString("role"));
				}
			}

		} catch (SQLException sql) {
			errorLog.error(sql.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				accessLog.trace("[*]Connection closed... - getUser");
			System.out.println("[*]Connection closed...");
			con.close();
		}
		return user;
	}

	public void removeUser(String username) throws SQLException, RegisterException {
		if (username.trim().length()==0) {
			errorLog.error("[*]Null user object not allowed - removeUser");
			throw new RegisterException(username + " Null user object not allowed");
		}
		try {
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "DELETE FROM users_yash WHERE username=?";
			accessLog.trace("[*]Creating preparestatement... - removeUser");
			System.out.println("[*]Creating preparestatement...");
			statement = con.prepareStatement(query);
			statement.setString(1, username);

			if (!statement.execute()) {
				accessLog.trace("[*] " + username + " Deleted seccessfully... - removeUser");
				System.out.println("[*]Deleted seccessfully...");
				con.commit();
			} else {
				accessLog.info("[*]No such record...-removeUser");
				System.out.println("[*]No such record...");
			}

		} catch (SQLException sql) {
			errorLog.error(sql.getMessage());
		} finally {
			if (statement != null)
				statement.close();
			if (con != null)
				accessLog.trace("[*]Connection closed... - removeUser");
			System.out.println("[*]Connection closed...");
			con.close();
		}

	}

	public void updateUser(User user) throws SQLException, RegisterException {
		if (user == null) {
			errorLog.error("[*]Null user object not allowed - updateUser");
			throw new RegisterException("Null user object not allowed");
		}
		try {
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "{call update_user(?,?,?)}";
			String username = user.getUserName();
			String password = user.getUserPass();
			String role = user.getUserRole();

			accessLog.trace("[*]Creating preparestatement... - updateUser");
			System.out.println("[*]Creating preparestatement...");
			cstmt = con.prepareCall(query);
			cstmt.setString(1, username);
			cstmt.setString(2, password);
			cstmt.setString(3, role);

			if (!cstmt.execute()) {
				accessLog.trace("[*] " + username + " Updated seccessfully... - updateUser");
				System.out.println("[*]Updated seccessfully...");
				con.commit();
			} else {
				accessLog.info("[*]No such record... - updateUser");
				System.out.println("[*]No such record...");
			}
		} catch (SQLException sql) {
			errorLog.error(sql.getMessage());
		} finally {
			if (con != null && !con.isClosed()) {
				accessLog.trace("[*]Connection closed... - updateUser");
				System.out.println("[*]Connection closed...");
				con.close();
			}

		}

	}

	public List<User> listUsers() throws SQLException, RegisterException {
		List<User> users = new ArrayList<User>();
		User user = new User();
		try {
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "SELECT username,password,role FROM users_yash";
			accessLog.trace("[*]Creating statement... - listUsers");
			System.out.println("[*]Creating statement...");
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			accessLog.info("[*]Looking for the records... - listUsers");
			System.out.println("[*]Looking for the records...");

			while (rset.next()) {
				user.setUserName(rset.getString("username"));
				user.setUserPass(rset.getString("password"));
				user.setUserRole(rset.getString("role"));
				users.add(user);
			}

		} catch (SQLException sql) {
			errorLog.error(sql.getMessage());
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				accessLog.trace("[*]Connection closed... - listUsers");
			System.out.println("[*]Connection closed...");
			con.close();
		}
		return users;
	}

}
