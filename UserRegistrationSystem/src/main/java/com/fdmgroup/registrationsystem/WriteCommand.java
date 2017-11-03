package com.fdmgroup.registrationsystem;

import java.io.IOException;
import java.sql.SQLException;
/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * this is WriteCommand interface with its writeUser method
 * 
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public interface WriteCommand {

	public void writeUser(User user) throws IOException, RegisterException, SQLException;
}
