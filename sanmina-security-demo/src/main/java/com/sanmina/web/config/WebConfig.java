package com.sanmina.web.config;

import com.sanmina.web.filter.TimeFilter;
import com.sanmina.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 要注册拦截器需要继承WebMvcConfigurerAdapter类，并覆盖addInterceptors 方法
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //因为timeinterceptor已经声明成一个component，所以可以直接注入
    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将timeInterceptor注册到拦截器中
        registry.addInterceptor(timeInterceptor);
    }

//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        //设置被过滤的url地址，这里配置所有地址
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
/*
    @Bean
    public TimeFilter getTimeFilter(){
        return new TimeFilter();
    }*/
}
