package com.abel.spring;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyibo on 17/7/5.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext() throws Exception {
        //解析xml 利用反射生成 bean
        SAXBuilder sb = new SAXBuilder();
        //构造文档对象
        Document doc = sb.build("src/main/resources/bean.xml");
        //获取根元素
        Element root = doc.getRootElement();
        //取名字为disk的所有元素
        List list = root.getChildren("bean");
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            String id = element.getAttributeValue("id");
            //取disk子元素capacity的内容
            String className = element.getAttributeValue("class");
            System.out.println("id : " + id + " className : " + className);
            Object o = Class.forName(className).newInstance();
            beans.put(id, o);

            //依赖注入，自动装配 xml 的第二层
            for (Element propertyElement : (List<Element>) element.getChildren("property")) {
                //userDAO
                String name = propertyElement.getAttributeValue("name");
                //u
                String bean = propertyElement.getAttributeValue("bean");
                //UserDAOImpl instance
                Object beanObj = beans.get(bean);

                //拼出setUserDAO方法名字
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                System.out.println("methodName : " + methodName);

                // methodName 为方法名  beanObj.getClass().getInterfaces()[0]  为方法的参数 反射取出方法
                // beanObj.getClass().getInterfaces()[0] 为 beanObj  实现的第一个接口 也就是 UserDao 为方法的参数
                // beanObj 为 UserDAOImpl.class 因为xml 中 bean u 配置的 class 为 UserDAOImpl
                Method m = o.getClass().getMethod(methodName, beanObj.getClass().getInterfaces()[0]);

                // 进行注入
                // 代理执行 m 方法， 也就是用 o 这个对象 调用 m 方法 参数 为 beanObj
                // o.m(beanObj) 等价于 userService.setUserDao(userDao)
                m.invoke(o, beanObj);
            }
        }
    }


    public Object getBean(String name) {
        return beans.get(name);
    }
}
