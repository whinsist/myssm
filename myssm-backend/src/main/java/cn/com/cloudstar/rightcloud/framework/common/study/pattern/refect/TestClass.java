package cn.com.cloudstar.rightcloud.framework.common.study.pattern.refect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestClass {
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException {

		CarVoTest car = new CarVoTest();
		
		//获得Class的三种方法
		//Class clazz = Class.forName("org.springframework.context.ApplicationContext");
		//Class clazz = car.getClass();
		Class clazz = CarVoTest.class;
		 
		System.out.println("clazz--->"+clazz);
		
		
		System.out.println("方法：---------------");
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m : methods){
			System.out.println(m);
		}
		Method declaredMethod = clazz.getDeclaredMethod("test", String.class);
		System.out.println("获取test方法："+declaredMethod);
		
		
		System.out.println("属性：---------------");
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields){
			System.out.println(field);
		}
		System.out.println("获取brand属性："+clazz.getDeclaredField("brand"));
		
		
		System.out.println("构造方法：---------------");
		Constructor[] constructors = clazz.getConstructors();
		for(Constructor constructor : constructors){
			System.out.println(constructor);
		}
		//Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{});
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{String.class,double.class});
		System.out.println("获取构造方法："+constructor);

		//主线修改
		//主线1
		//主线2
	}
		
}
