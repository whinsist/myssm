package cn.com.cloudstar.rightcloud.framework.test.t001study.reflect;

import java.lang.reflect.Method;

import cn.com.cloudstar.rightcloud.framework.common.util.PropertiesUtil;

/**
 * @author Hong.Wu
 * @date: 8:12 2019/10/27
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws Exception {
//        ReflectDemo2.class.getClassLoader().getResourceAsStream("config.properties");


        String className = PropertiesUtil.getProperty("className");
        String methodName = PropertiesUtil.getProperty("methodName");

        // 加载该类进入内容
        Class<?> clazz = Class.forName(className);
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod(methodName, String.class);
        method.invoke(instance, "param");
    }

}
