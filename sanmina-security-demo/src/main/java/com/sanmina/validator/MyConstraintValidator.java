package com.sanmina.validator;

import com.sanmina.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验类
 * 实现ConstraintValidator接口，该接口有两个泛型： 1.标识校验的注解；2.要校验的字段类型，这里要校验Object也就是所有类型都可以
 * 这个类不需要使用component等注解来声明成spring的bean，spring看到constraintvalidator就会自动加载到bean容器里边
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {


    //这里可注入任何spring ioc容器里的类
    @Autowired
    private HelloService helloService;

    /**
     * 校验器初始化做的事情
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    /**
     * 校验逻辑
     * @param value ： 校验的值
     * @param context ： 校验的上下文
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        //false 表示校验不通过
        return false;
    }
}
