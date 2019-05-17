package cn.com.cloudstar.rightcloud.framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.cloudstar.rightcloud.framework.interceptor.helper.UserThreadLocal;
import cn.com.cloudstar.rightcloud.system.pojo.User;
import cn.com.cloudstar.rightcloud.system.service.UserService;

public class UserLoginHandlerInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;


    /**
     * 问题：
     我们的拦截器是单例，因此不管用户请求多少次都只有一个拦截器实现，即线程不安全，那我们应该怎么记录时间呢？
     解决方案是使用ThreadLocal，它是线程绑定的变量，提供线程局部变量（一个线程一个ThreadLocal，A线程的ThreadLocal只能看到A线程的ThreadLocal，不能看到B线程的ThreadLocal）。
     */
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //将user对象放置到本地线程中，方便在Controller和Service中获取
        User user = new User();
        user.setRealName("threadusername");
        UserThreadLocal.set(user);

        //线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(System.currentTimeMillis());

        request.setAttribute("startTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {

        //清空本地线程中的User对象数据
        UserThreadLocal.set(null);

        long beginTime = startTimeThreadLocal.get();
        long consumeTime = System.currentTimeMillis() - beginTime;
        // 记录到日志文件
        System.out.println(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));

        long startTime = (Long)request.getAttribute("startTime");
        long executeTime = System.currentTimeMillis() - startTime;
        System.out.println("executeTime--"+executeTime);
    }

}
