package com.fdmgroup.exercisesystem;

import java.sql.SQLException;
import java.util.List;

public interface Users {

	public abstract void addUser(User user) throws RegisterException, SQLException;

	public abstract User getUser(String username) throws SQLException, RegisterException;

	public abstract void removeUser(String username) throws SQLException, RegisterException;

	public abstract void updateUser(User user) throws SQLException, RegisterException;

	public abstract List<User> listUsers() throws SQLException, RegisterException;

}
