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


    //通过jdk 的动态代理实现
    @Test
    public void testProxy() {
        //被代理对象
        UserDAO userDAO = new UserDAOImpl();
        LogInterceptor li = new LogInterceptor();
        li.setTarget(userDAO);
        //newProxyInstance 产生一个代理对象 ，三个参数
        //1.classloader 代理对象和被代理对象应该处于同一个 classloader
        //2.接口 产生的代理对象应该实现那些接口
        //3.handel 执行代理对象方法时，应用那个handel 处理。
        //(接口中有什么方法，代理中就有什么方法 代理中的每个方法在调用的时候都会把 方法自身传给 handel, 并把 代理对象和参数都传递过去 )
        
//      UserDAO userDAOProxy = (UserDAO) Proxy.newProxyInstance(userDAO.getClass().getClassLoader(), userDAO.getClass().getInterfaces(), li);
        Class[] cl =new Class[1];
        cl[0] = UserDAO.class;
        UserDAO userDAOProxy = (UserDAO) Proxy.newProxyInstance(userDAO.getClass().getClassLoader(),cl,li);
        System.out.println(userDAOProxy.getClass());
        userDAOProxy.detele();
        userDAOProxy.save(new User());
    }
}
