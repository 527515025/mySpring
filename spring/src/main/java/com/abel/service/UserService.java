package com.abel.service;


import com.abel.dao.UserDAO;
import com.abel.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void add(User user) {
        userDAO.save(user);
    }


//    public UserService(UserDAO userDAO) {
//        super();
//        this.userDAO = userDAO;
//    }
}
