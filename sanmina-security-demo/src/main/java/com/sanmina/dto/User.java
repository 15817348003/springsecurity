package com.sanmina.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sanmina.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * 用户实体
 */
public class User {



    public interface UserSimpleView {}; //用户的简单视图
    public interface UserDetailView extends UserSimpleView {}; //用户详情视图，继承简单视图，表示用户使用详情视图时也能使用简单视图的内容

    private String id;

    @MyConstraint(message = "这是一个测试")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日必须为过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
