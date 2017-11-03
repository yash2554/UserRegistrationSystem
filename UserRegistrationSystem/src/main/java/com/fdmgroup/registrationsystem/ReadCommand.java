package com.fdmgroup.registrationsystem;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
/**
 * This application provides the registration console functionality which is
 * implemented with JUnit Tests and log4j Integration. this tool is command line
 * tool which maintains a external TXT file to maintain database of registered
 * users.
 * 
 * this is ReadCommand interface with its readUser method
 * 
 * @author yash.patel
 * @since 2017
 * @version 1.0
 *
 */
public interface ReadCommand {

	User readUser(String username) throws FileNotFoundException, IOException, RegisterException, SQLException;
}
