package com.sanmina.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component

/**
 * 定义过滤器拦截的弊端：1.请求参数只能通过request对象一个一个获取出来；2.不知道是那个控制器方法调用了过滤器
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TimeFilter start");
        Date startDate = new Date();
        chain.doFilter(request, response);
        System.out.println("spenk time is :" + (System.currentTimeMillis() - startDate.getTime()));
        System.out.println("TimeFilter finish");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter end");
    }
}
