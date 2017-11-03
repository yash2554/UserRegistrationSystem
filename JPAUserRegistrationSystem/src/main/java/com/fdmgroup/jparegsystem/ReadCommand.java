package com.fdmgroup.jparegsystem;

/**
 * This is an interface which contains readUser method with no implementation
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public interface ReadCommand {

	User readUser(String username) throws RegisterException;
}
