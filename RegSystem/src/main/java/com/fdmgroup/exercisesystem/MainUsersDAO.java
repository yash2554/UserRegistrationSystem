package com.fdmgroup.exercisesystem;

import java.sql.SQLException;


public class MainUsersDAO {

	public static void main(String[] args) throws SQLException, RegisterException {
	
		
		User yash1 = new User("yash1", "password1", "role1");
		User yash2 = new User("yash2", "password2", "role2");
		User yash3 = new User("yash3", "password3", "role3");
		User yash4 = new User("yash4", "password4", "role4");
		User yash5 = new User("yash5", "password5", "role5");
		//User yash5 = new User("   ", "password5space", "role5space");
		User yash6 = new User("yash6", "password6", "role6");
		User yash7 = new User("yash3", "changedpassword3", "changedrole3");

		
		Users add = new UsersDAO();
		add.addUser(yash1);
		add.addUser(yash2);
		add.addUser(yash3);
		add.addUser(yash4);
		add.addUser(yash5);
		add.addUser(yash6); 

		Users get = new UsersDAO();
		get.getUser("yash2").getUserPass();

		Users remove = new UsersDAO();
		remove.removeUser("yash2");

		Users update = new UsersDAO();
		update.updateUser(yash7);

		Users list = new UsersDAO();
		list.listUsers().get(2).getUserName();
	}

}
