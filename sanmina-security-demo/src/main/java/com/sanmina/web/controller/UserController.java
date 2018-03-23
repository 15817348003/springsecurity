package com.sanmina.web.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.sanmina.dto.User;
import com.sanmina.dto.UserQueryCondition;
import com.sanmina.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @JsonView(User.UserSimpleView.class)  //限制返回的内容， 使用方式：1，为实体声明两个视图接口；2.在对应的属性的get方法上引用对应的视图；3.在控制器方法上使用对应的视图
    @GetMapping
    /**
     * condition ： 直接传对象参数的字段名称为参数名的参数，spring会自动做数据绑定
     * pageable ： spring自带的分页封装类
     */
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "userName,desc") Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE ));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")  //正则表达式限制url传递的参数格式：只能是数字
    public User getInfo(@PathVariable("id") String id){
        //throw new UserNotExistException(id);
        User user = new User();
        user.setUserName("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){   //@Valid注解会去根据注入的实体类的字段要求去校验，如果不满足实体类相关注解如：@NotBlank的约束则会被放到BindingResult,如果没有BindingResult接收错误则会抛出400异常

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){   //@Valid注解会去根据注入的实体类的字段要求去校验，如果不满足实体类相关注解如：@NotBlank的约束则会被放到BindingResult,如果没有BindingResult接收错误则会抛出400异常

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error ->{
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }
}
