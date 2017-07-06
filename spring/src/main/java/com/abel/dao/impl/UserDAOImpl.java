package com.abel.dao.impl;


import com.abel.dao.UserDAO;
import com.abel.model.User;

public class UserDAOImpl implements UserDAO {

	public void save(User user) {
		//Hibernate
		//JDBC
		//XML
		//NetWork
		System.out.println("user saved!");
	}

	public void detele() {
		System.out.println("user delete!");
	}

}
