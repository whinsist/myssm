package cn.com.cloudstar.rightcloud.framework.common.study.jdbctest.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Set;

public class MyInvocaHandler {

    public Object bind(Connection connection, int maxIdle) {
        return Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                                      connection.getClass().getInterfaces(),
                                      new InvocationHandler() {

                                          @Override
                                          public Object invoke(Object proxy, Method method, Object[] args)
                                                  throws Throwable {
                                              if (method.getName().equals("close")) {
                                                  //连接池
                                                  Set<Connection> set = MyPool.getSet();
                                                  Object result = null;
                                                  if (set.size() < maxIdle) {
                                                      //检查连接池是否已经达到最大可保存数，
                                                      //如没有达到最大可保存数，将连接放入连接池，也就是集合中。不执行close方法，可重复使用
                                                      set.add(connection);
                                                      System.out.println("=========close   回收 conn:" + connection);
                                                  } else {
                                                      //	如已达到最大可保存数，直接调用close方法关闭连接，该连接不能被重复使用
                                                      if (connection != null) {
                                                          result = method.invoke(connection, args);
                                                      }
                                                      System.out.println("=========close   关闭 conn:" + connection);
                                                  }
                                                  //获取当前连接数
                                                  int active = MyPool.getActive();
                                                  active--;
                                                  MyPool.setActive(active);
                                                  System.out.println("active:" + active);
                                                  return result;
                                              } else {
                                                  Object result = method.invoke(connection, args);
                                                  return result;
                                              }

                                          }
                                      });
    }
}
