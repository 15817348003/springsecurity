package com.sanmina.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


//@Aspect
//@Component
public class TimeAspect {

    /**
     * 第一个*表示任何的返回值
     * 后面的*(..):表示任何方法名的任何参数，也就是所有的方法
     * 这里是环绕的切片，也可以先声明一个@Pointcut切入点，然后再定义对应的@before和@after等
     * 因为是一个包围的方法，所以要把返回结果返回回去，而这个返回结果可能是任何类型，所以要定义为object
     */
    @Around("execution(* com.sanmina.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for(Object arg : args){
            System.out.println("arg id " + arg);
        }
        long start = System.currentTimeMillis();

        Object object = pjp.proceed();

        System.out.println("time aspect 耗时 :" + (System.currentTimeMillis() - start));

        System.out.println("time aspect end");
        return object;
    }

}
