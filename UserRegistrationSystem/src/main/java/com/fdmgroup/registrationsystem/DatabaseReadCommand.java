package com.fdmgroup.registrationsystem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DatabaseReadCommand implements ReadCommand {

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

	public User readUser(String username) throws RegisterException, SQLException {
		PropertyConfigurator.configure("./log4j.properties");
		if (username.trim().length() == 0) {
			errorLog.error("[*]Null string not allowed - getUser");
			throw new RegisterException(username + " Null string not allowed");
		}
		User user = new User();
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
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
					user.setUsername(rset.getString("username"));
					user.setPassword(rset.getString("password"));
					user.setRole(rset.getString("role"));
				} else {
					return null;
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

}
