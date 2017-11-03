package com.fdmgroup.jparegsystem;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * This is UsersDAOImpl implements UsersDAO interface with User entity.
 * 
 * @author yash.patel
 * @version 1.1
 * @since 03/11/2017
 *
 */
public class UsersDAOImpl implements UsersDAO<User> {

	private EntityManagerFactory emFact;
	private static final Logger accessLog = Logger.getLogger("accessLogger");
	private static final Logger errorLog = Logger.getLogger("errorLogger");

	/**
	 * This is UsersDAOImpl constructor with no arguments
	 */
	public UsersDAOImpl() {
		PropertyConfigurator.configure("./log4j.properties");
		emFact = Persistence.createEntityManagerFactory("usersDAO");
	}

	/**
	 * This is addUser Method with User object argument. it also throws an
	 * exception it unexpected error occurs
	 * 
	 * It checks the User object argument and then if entered username doesn't
	 * exist then it will store/insert a record into database
	 * 
	 */
	public void addUser(User user) throws RegisterException {
		if (user == null) {
			errorLog.error(
					"[*] \"User object\" null argument passed into addUser method invoked from UsersDAOImpl class");
			throw new RegisterException("\"Object\" null not allowed");

		} else {
			if (user.getUsername().trim().length() == 0) {
				errorLog.error(
						"[*] \"username\" null argument passed into addUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"username\" null not allowed");
			}
			if (user.getPassword().trim().length() == 0) {
				errorLog.error(
						"[*] \"password\" null argument passed into addUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"password\" null not allowed");
			}
			if (user.getRole().trim().length() == 0) {
				errorLog.error("[*] \"role\" null argument passed into addUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"role\" null not allowed");
			}
			accessLog.trace("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
					+ ") passed into addUser method invoked from UsersDAOImpl class");
			EntityManager eManager = emFact.createEntityManager();
			accessLog.trace("[*] EntityManager created successfully from addUser method in UsersDAOImpl class");
			eManager.getTransaction().begin();
			if (eManager.find(User.class, user.getUsername()) != null) {
				accessLog.trace("[*] " + user.getUsername()
						+ "- duplicate user object passed in addUser method invoked from UsersDAOImpl class");
				System.out.println(user.getUsername() + "User Already Exists !");
			} else {
				eManager.persist(user);
				accessLog.info("[*] " + user.getUsername() + "- new user record stored into database successfully");
			}
			eManager.getTransaction().commit();
			accessLog.trace(
					"[*] EntityManager invoked getTransaction method and commit successfully from UsersDAOImpl class");
			eManager.close();
			accessLog.trace("[*] EntityManager closed successfully from addUser method in UsersDAOImpl class");
		}
	}

	/**
	 * This is getUser Method with String typed username argument. it also
	 * throws an exception it unexpected error occurs
	 * 
	 * It checks the User object argument and then if entered username exists
	 * then it will return a User object retrieved from database
	 * 
	 */

	public User getUser(String username) throws RegisterException {
		if (username.trim().length() == 0) {
			errorLog.error("[*] \"username\" null argument passed into getUser method invoked from UsersDAOImpl class");
			throw new RegisterException("\"username\" null not allowed");
		}
		accessLog.trace("[*] \"" + username.trim() + "\" passed into addUser method invoked from UsersDAOImpl class");
		User user = null;
		EntityManager eManager = emFact.createEntityManager();
		accessLog.trace("[*] EntityManager created successfully from getUser method in UsersDAOImpl class");
		user = eManager.find(User.class, username.trim());
		eManager.close();
		accessLog.trace("[*] EntityManager closed successfully from from getUser method in UsersDAOImpl class");
		return user;
	}

	/**
	 * This is updateUser Method with User object argument. it also throws an
	 * exception it unexpected error occurs
	 * 
	 * It checks the username argument and then if entered username exists then
	 * it will update User object into from database with its updated values
	 * 
	 */
	public void updateUser(User user) throws RegisterException {
		if (user == null) {
			errorLog.error(
					"[*] \"User object\" null argument passed into updateUser method invoked from UsersDAOImpl class");
			throw new RegisterException("\"Object\" null not allowed");

		} else {

			if (user.getUsername().trim().length() == 0) {
				errorLog.error(
						"[*] \"username\" null argument passed into updateUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"username\" null not allowed");
			}
			if (user.getPassword().trim().length() == 0) {
				errorLog.error(
						"[*] \"password\" null argument passed into updateUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"password\" null not allowed");
			}
			if (user.getRole().trim().length() == 0) {
				errorLog.error(
						"[*] \"role\" null argument passed into updateUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"role\" null not allowed");
			}
			accessLog.trace("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
					+ ") passed into updateUser method invoked from UsersDAOImpl class");
			EntityManager eManager = emFact.createEntityManager();
			accessLog.trace("[*] EntityManager created successfully from updateUser method in UsersDAOImpl class");
			eManager.getTransaction().begin();
			eManager.createQuery("UPDATE User j SET j.password='" + user.getPassword() + "',j.role='" + user.getRole()
					+ "' WHERE j.username='" + user.getUsername() + "'").executeUpdate();
			eManager.getTransaction().commit();
			accessLog.trace(
					"[*] EntityManager invoked getTransaction method and commit successfully from UsersDAOImpl class");
			eManager.close();
			accessLog.trace("[*] EntityManager closed successfully from updateUser method in UsersDAOImpl class");
		}
	}

	/**
	 * This is deleteUser Method with User object argument. it also throws an
	 * exception it unexpected error occurs
	 * 
	 */
	public void deleteUser(User user) throws RegisterException {
		if (user == null) {
			errorLog.error(
					"[*] \"User object\" null argument passed into addUser method invoked from UsersDAOImpl class");
			throw new RegisterException("\"Object\" null not allowed");

		} else {
			if (user.getUsername().trim().length() == 0) {
				errorLog.error(
						"[*] \"username\" null argument passed into deleteUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"username\" null not allowed");
			}
			if (user.getPassword().trim().length() == 0) {
				errorLog.error(
						"[*] \"password\" null argument passed into deleteUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"password\" null not allowed");
			}
			if (user.getRole().trim().length() == 0) {
				errorLog.error(
						"[*] \"role\" null argument passed into deleteUser method invoked from UsersDAOImpl class");
				throw new RegisterException("\"role\" null not allowed");
			}
			accessLog.trace("[*] User(" + user.getUsername() + ", " + user.getPassword() + ", " + user.getRole()
					+ ") passed into deleteUser method invoked from UsersDAOImpl class");
			EntityManager eManager = emFact.createEntityManager();
			accessLog.trace("[*] EntityManager created successfully from deleteUser method in UsersDAOImpl class");
			eManager.getTransaction().begin();
			User exist = eManager.find(User.class, user.getUsername());
			eManager.remove(exist);
			eManager.getTransaction().commit();
			accessLog.trace(
					"[*] EntityManager invoked getTransaction method and commit successfully from UsersDAOImpl class");
			eManager.close();
			accessLog.trace("[*] EntityManager closed successfully from deleteUser method in UsersDAOImpl class");
		}

	}

	/**
	 * This is readAllUser Method with no argument. it also throws an exception
	 * it unexpected error occurs
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<User> readAllUser() {
		List<User> users = new ArrayList<User>();
		accessLog.trace("[*] EntityManager created successfully from readAllUser method in UsersDAOImpl class");
		EntityManager eManager = emFact.createEntityManager();
		Query query = eManager.createQuery("SELECT j FROM User j", User.class);
		users = query.getResultList();
		accessLog.trace("[*] List of Users retrieved by readAllUser method from UsersDAOImpl class");
		eManager.close();
		accessLog.trace("[*] EntityManager closed successfully from readAllUser method in UsersDAOImpl class");
		return users;

	}

}
