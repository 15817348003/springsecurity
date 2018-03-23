package com.sanmina.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 和filter不一样，光把interceptor声明成component是不起作用的，还需要一些额外的配置，如：webconfig中配置
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 控制器方法执行之前执行的方法
     *
     * 拦截器是不能拿到控制器参数的值的，只能拿到那个方法，也就是handler是不知道controller方法中的参数的值的
     * 如果要在拦截请求的时候也拿到请求参数，就要使用切片的方式对controller进行拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        //rehandle要传参数给postHandle只能通过request传递参数
        request.setAttribute("startTime", new Date().getTime());

        //返回true表示放行
        return true;
    }

    /**
     * 控制器方法正常完成后执行的方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        System.out.println("time interceptor 耗时：" + (new Date().getTime() - (long)request.getAttribute("startTime")));
    }

    /**
     * 不管是否抛出异常都会在最后执行这个方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
        System.out.println("time interceptor 耗时：" + (new Date().getTime() - (long)request.getAttribute("startTime")));
        //ExceptionHandler 异常处理器是在afterCompmletion之前执行的，会在这个方法之前把异常处理掉,
        // 所以此时ex为null，除非抛出的异常不是异常处理器处理的异常
        System.out.println("ex is : " + ex);
    }
}
