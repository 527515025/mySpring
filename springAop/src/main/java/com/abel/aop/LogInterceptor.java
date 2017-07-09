package com.abel.aop;

import com.abel.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * Created by yangyibo on 17/7/6.
 */

@Aspect
@Component
public class LogInterceptor {

    //定义一个切入面，也就是一个切入点的集合
    @Pointcut("execution(public * com.abel.service..*.add(..))")
    public void myMethod() {
    }

    ;

    //定义一个注解方式的
    @Pointcut("@annotation(com.abel.aop.LogRecord)")
    public void annotationPointCut() {
    }

    ;

    // 方法执行之前执行，也就是在spring管理的对象的方法前执行前执行此方法
    // execution 切入点语法
//    @Before("execution(public void com.abel.dao.impl.UserDAOImpl.save(com.abel.model.User ))")
    @Before("annotationPointCut() && args(user,..)")
    public void beforeMethod(JoinPoint point, User user) {
        //切入点是加注释的那个方法
        System.out.println("Method : " + point.getSignature().getName());
        //方法参数集合
        Object[] s = point.getArgs();
        System.out.println("Method args : " + s[0].toString());
        System.out.println("start begin .........." + "user :" + user.getUsername());
    }

    //    @AfterReturning("execution(public void com.abel.dao..*.*(..))")
    //AfterReturning 是目标方法执行且返回后执行， rvt 方法的返回值，
    @AfterReturning(value = "annotationPointCut()", returning = "rvt")
    public void afterMethod(Object rvt) {
        //此处获取返回值为null，因为 @Around 的 advice 执行方法后没有把方法的返回值 返回，所以在AfterReturning 中获取的返回值为null
        System.out.println("start AfterReturning ....... and return : " + rvt);
    }

    //@annotation(log) 获取注解中的参数  @LogRecord(key="add is user")
    @Around("annotationPointCut()&& @annotation(log)")
    public void aroundMethod(ProceedingJoinPoint pjp, LogRecord log) throws Throwable {
        System.out.println("method around start ... LogRecord  key : " + log.key());
        //获取目标对象的返回值,打印返回值，但是我不return ，AfterReturning 拿不到 返回值，哈哈哈
        Integer i = (Integer) pjp.proceed();

        System.out.println("method around end ... and method return :" + i);
    }

}
