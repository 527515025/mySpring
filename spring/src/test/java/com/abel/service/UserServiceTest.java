package com.abel.service;

import com.abel.dao.UserDAO;
import com.abel.model.User;
import com.abel.spring.BeanFactory;
import com.abel.spring.ClassPathXmlApplicationContext;
import org.junit.Test;

public class UserServiceTest {

	@Test
	public void testAdd() throws Exception {

		BeanFactory factory = new ClassPathXmlApplicationContext();
		// 自动创建 bean
//		UserDAO userDAO = (UserDAO)factory.getBean("u");
//		UserService userService = new UserService(userDAO);

		//依赖注入，自动装配
		UserService userService = (UserService) factory.getBean("userService");
		userService.add(new User());
	}


}
