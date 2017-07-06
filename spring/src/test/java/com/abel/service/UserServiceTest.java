package com.abel.service;

import com.abel.aop.LogInterceptor;
import com.abel.dao.UserDAO;
import com.abel.dao.impl.UserDAOImpl;
import com.abel.model.User;
import com.abel.spring.BeanFactory;
import com.abel.spring.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.Proxy;

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


    @Test
    public void testProxy() {
        //被代理对象
        UserDAO userDAO = new UserDAOImpl();
        LogInterceptor li = new LogInterceptor();
        li.setTarget(userDAO);
        //三个参数
        //1.classloader 代理对象和被代理对象应该处于同一个 classloader
        //2.接口 产生的代理对象应该实现那些接口
        //3.handel 执行代理对象方法时，应用那个handel 处理。
        UserDAO userDAOProxy = (UserDAO) Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
        userDAOProxy.detele();
        userDAOProxy.save(new User());
    }
}
