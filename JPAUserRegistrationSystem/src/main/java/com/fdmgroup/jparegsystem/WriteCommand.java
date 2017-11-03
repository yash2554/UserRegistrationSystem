package com.fdmgroup.jparegsystem;

/**
 * This is an interface which contains writeUser and writeUpdateUser method with
 * no implementation
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public interface WriteCommand {

	public void writeUser(User user) throws RegisterException;

	public void writeUpdateUser(User user) throws RegisterException;

}
