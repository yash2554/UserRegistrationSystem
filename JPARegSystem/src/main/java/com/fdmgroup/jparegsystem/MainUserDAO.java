package com.fdmgroup.jparegsystem;

public class MainUserDAO {

	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		
		User user1 = new User("user1","pass1","role1");
		User user2 = new User("user2","pass2","role2");
		User user3 = new User("user3","pass3","role3");
		User user4 = new User("user4","pass4","role4");
		User user5 = new User("user4","pass4update","role4update");


		
		dao.addUser(user1);
		dao.addUser(user2);
		dao.addUser(user3);
		dao.addUser(user4);
		System.out.println(dao.getUser("user1").getUsername());
		dao.updateUser(user5);
		dao.deleteUser(user5);
		System.out.println(dao.readAllUser().size());
		
	}

}
