package com.abel.controller;

import com.abel.model.User;
import com.abel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangyibo on 17/7/9.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Object save(HttpServletRequest servletRequest) {
        User user =new User();
        user.setUsername("abel");
        userService.add(user);
        return "ok";
    }
}
