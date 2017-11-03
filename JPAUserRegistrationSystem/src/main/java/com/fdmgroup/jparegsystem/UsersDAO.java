package com.fdmgroup.jparegsystem;

import java.util.List;

/**
 * This is an interface named UserDAO which takes entity of User and it contains
 * abstract methods with no implementation
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public interface UsersDAO<T> {

	public abstract void addUser(T t) throws RegisterException;

	public abstract T getUser(String username) throws RegisterException;

	public abstract void updateUser(T t) throws RegisterException;

	public abstract void deleteUser(T t) throws RegisterException;

	public abstract List<T> readAllUser() throws RegisterException;
}
