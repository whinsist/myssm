package cn.com.cloudstar.rightcloud.framework.test.t001study.jdbctest.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Set;

public class MyInvocaHandler {

    /**
     * 代理Connection对象 因为关闭时有可能不是真的关闭 只是把连接放入连接池中
     * @param connection 代理的对象
     * @param maxIdle 最大可保存数
     * @return  返回代理对象
     */
    public Object bind(Connection connection, int maxIdle) {
        return Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                                      connection.getClass().getInterfaces(),
                                      new InvocationHandler() {

                                          @Override
                                          public Object invoke(Object proxy, Method method, Object[] args)
                                                  throws Throwable {
                                              if (method.getName().equals("close")) {
                                                  Set<Connection> set = MyPool.getSet();
                                                  Object result = null;
                                                  if (set.size() < maxIdle) {
                                                      // 如没有达到最大可保存数，将连接放入连接池，也就是集合中。不执行close方法，可重复使用
                                                      set.add(connection);
                                                      System.out.println("=========close   回收 conn:" + connection);
                                                  } else {
                                                      // 如已达到最大可保存数，直接调用close方法关闭连接，该连接不能被重复使用
                                                      result = method.invoke(connection, args);
                                                      System.out.println("=========close   关闭 conn:" + connection);
                                                  }
                                                  // 当前活动数减1
                                                  int active = MyPool.getActive();
                                                  active --;
                                                  MyPool.setActive(active);
                                                  System.out.println("active:" + active);
                                                  return result;
                                              } else {
                                                  // 不是close直接返回
                                                  Object result = method.invoke(connection, args);
                                                  return result;
                                              }
                                          }
                                      });
    }
}
