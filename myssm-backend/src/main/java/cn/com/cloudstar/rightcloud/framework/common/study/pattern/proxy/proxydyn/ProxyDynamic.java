package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.proxydyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 22:06 2019/10/10
 */
public class ProxyDynamic {

    // 获取代理对象
    public Object bind (Object object){
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("invoke-----"+proxy.getClass());
                System.out.println("startTime ; " + new Date());

                // 目标对象的方法
                Object result = method.invoke(object, args);

                System.out.println("entTime ; " + new Date());

                return result;
            }
        });
    }
}
