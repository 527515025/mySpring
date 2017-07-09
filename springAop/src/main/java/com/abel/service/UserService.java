package com.abel.service;


import com.abel.aop.LogRecord;
import com.abel.dao.UserDAO;
import com.abel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @LogRecord(key="add is user")
    public Integer add(User user) {
        userDAO.save(user);
        return 40;
    }
}
