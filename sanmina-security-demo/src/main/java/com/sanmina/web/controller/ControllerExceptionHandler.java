package com.sanmina.web.controller;


import com.sanmina.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器的错误处理器
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 当controller抛出UserNotExistException异常的时候就会交由这个方法处理
     * 这个方法的参数为抛出的异常实例
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)    //自定义返回http状态码，这里返回500
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex){
        Map<String, Object> result = new HashMap<>();
        //将需要记录的信息记录到map，这里记录了出异常的用户id
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }

}
