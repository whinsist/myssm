package cn.com.cloudstar.rightcloud.framework.common.pattern.refect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectDemo3 {

	public static void main(String[] args) throws Exception {
		
		Class clazz = Car.class;
		
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{});
		//根据类的默认构造器 来获得一个对象
		Object instance = constructor.newInstance(new Object[]{});
		System.out.println(instance);
		
		Method method = clazz.getDeclaredMethod("setBrand", new Class[]{String.class});
		Object invoke = method.invoke(instance, "xxxx");
		 
		
		System.out.println(instance);
		
	}

}
