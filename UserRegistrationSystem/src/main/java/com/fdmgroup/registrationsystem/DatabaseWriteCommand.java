package com.fdmgroup.registrationsystem;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class DatabaseWriteCommand implements WriteCommand {
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
	
	public void writeUser(User user) throws IOException, RegisterException, SQLException {
		PropertyConfigurator.configure("./log4j.properties");
		if (user == null) {
			errorLog.error("[*]Null user object not allowed - addUser");
			throw new RegisterException("Null user object not allowed");
		}

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(sqlUrl, sqlUser, sqlPass);
			query = "{call add_user(?,?,?)}";
			String username = user.getUsername();
			String password = user.getPassword();
			String role = user.getRole();

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

}
