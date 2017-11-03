package com.fdmgroup.jparegsystem;

import java.util.List;

public interface Users<T> {
	
	public abstract void addUser(T t);
	public abstract T getUser(String username);
	public abstract void updateUser(T t);
	public abstract void deleteUser(T t);
	public abstract List<T> readAllUser();
}
