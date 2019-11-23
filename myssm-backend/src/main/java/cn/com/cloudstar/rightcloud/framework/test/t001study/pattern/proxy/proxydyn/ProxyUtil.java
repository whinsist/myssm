package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxydyn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Hong.Wu
 * @date: 9:24 2019/10/12
 */
public class ProxyUtil {

    public static Object proxyUserInterface(Object targetObject) {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
                                      new InvocationHandler() {
                                          @Override
                                          public Object invoke(Object proxy, Method method, Object[] args)
                                                  throws Throwable {

                                              System.out.println("---------start--------");
                                              Object result = method.invoke(targetObject, args);
                                              System.out.println("---------end--------");

                                              return result;
                                          }
                                      });
    }
    public static Object proxyConnectionInterface(Object targetObject) {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
                                      new InvocationHandler() {
                                          @Override
                                          public Object invoke(Object proxy, Method method, Object[] args)
                                                  throws Throwable {

                                              System.out.println("---------start--------");
                                              Object result = method.invoke(targetObject, args);
                                              System.out.println("---------end--------");

                                              return result;
                                          }
                                      });
    }
}
