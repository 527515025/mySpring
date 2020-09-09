package com.abel.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yangyibo on 17/7/6.
 */
public class LogInterceptor implements InvocationHandler {
    /**
     * target 被代理对象，可以通过参数传进来
     */
    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * 在执行被代理对象的方法前执行的方法，我门要切进去的逻辑
     */
    public void beforeMethod(Method m) {
        System.out.println(m.getName() + "  begin ..........");
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行我门的逻辑
        beforeMethod(method);
        //调用被代理对象的方法
        method.invoke(target, args);
        return null;
    }
}
