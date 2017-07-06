package com.abel.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangyibo on 17/7/6.
 */
public class LogInterceptor implements InvocationHandler {
    /**
     * 被代理对象，可以通过参数传进来
     */
    private Object target ;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }


    public void beforeMethod() {
        System.out.println("action  begin ..........");
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        beforeMethod();
        m.invoke(target, args);
        return null;
    }
}
