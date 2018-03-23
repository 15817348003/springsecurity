package com.sanmina.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)   //validatedBy指定一个类，表示被这个注解标识的字段或方法用这个类的校验逻辑来校验
public @interface MyConstraint {
    //自定义校验注解中，这三个属性是必须要声明的
    String message();

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
