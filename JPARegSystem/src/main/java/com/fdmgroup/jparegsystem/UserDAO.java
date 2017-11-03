package com.fdmgroup.jparegsystem;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UserDAO implements Users<User> {
	
	private EntityManagerFactory emFact;

	public UserDAO() {
		emFact = Persistence.createEntityManagerFactory("usersDAO");
	}

	public void addUser(User user) {
		EntityManager eManager = emFact.createEntityManager();
		eManager.getTransaction().begin();
		if(eManager.find(User.class, user.getUsername()) != null){
			System.out.println(user.getUsername() + "User Already Exists !");
		}else{
			eManager.persist(user);
		}
		eManager.getTransaction().commit();
		eManager.close();
	}

	public User getUser(String username) {
		User user = null;
		EntityManager eManager = emFact.createEntityManager();
		user = eManager.find(User.class, username);
		eManager.close();
		return user;
	}

	public void updateUser(User user) {
		EntityManager eManager = emFact.createEntityManager();
		eManager.getTransaction().begin();
		eManager.createQuery("UPDATE User j SET j.password='" + user.getPassword() + "',j.role='" + user.getRole()
				+ "' WHERE j.username='" + user.getUsername() + "'").executeUpdate();
		eManager.getTransaction().commit();
		eManager.close();
	}

	public void deleteUser(User user) {
		EntityManager eManager = emFact.createEntityManager();
		eManager.getTransaction().begin();
		User exist = eManager.find(User.class, user.getUsername());
		eManager.remove(exist);
		eManager.getTransaction().commit();
		eManager.close();

	}

	@SuppressWarnings("unchecked")
	public List<User> readAllUser() {
		List<User> users = new ArrayList<User>();
		EntityManager eManager = emFact.createEntityManager();
		Query query = eManager.createQuery("SELECT j FROM User j", User.class);
		users = query.getResultList();
		eManager.close();
		return users;

	}

}
