package com.abel.dao;


import com.abel.model.User;

public interface UserDAO {
	public Integer save(User user);
	public void detele();
}
