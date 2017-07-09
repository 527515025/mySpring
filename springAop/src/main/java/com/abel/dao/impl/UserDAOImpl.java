package com.abel.dao.impl;


import com.abel.dao.UserDAO;
import com.abel.model.User;
import org.springframework.stereotype.Component;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {

	public Integer save(User user) {
		System.out.println("user saved!");
		return 20;
	}

	public void detele() {
		System.out.println("user delete!");
	}

}
