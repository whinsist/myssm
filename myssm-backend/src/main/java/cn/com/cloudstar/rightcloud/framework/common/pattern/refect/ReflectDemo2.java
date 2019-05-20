package cn.com.cloudstar.rightcloud.framework.common.pattern.refect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo2 {

	public static void main(String[] args) throws Exception {
		
		Class clazz = CarVoTest.class;
		
		//Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{});
		//根据类的默认构造器 来获得一个对象
		//Object instance = constructor.newInstance(new Object[]{});
		//System.out.println(instance);
		
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{String.class,double.class});
		Object instance = constructor.newInstance(new Object[]{"1",123});
		System.out.println(instance);
		
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m : methods){
			String name = m.getName();
			if(m.getName().startsWith("set")){
				//Class<?>[] types = m.getParameterTypes();
				String fieldName = name.substring(3);
				fieldName = fieldName.substring(0, 1).toLowerCase()+fieldName.substring(1);
				Field field = clazz.getDeclaredField(fieldName);
				Class<?> type = field.getType();
				if(type == String.class  ){
					m.invoke(instance, new Object[]{"faLaLi"});
				}
				if(type == double.class){
					m.invoke(instance, 1234d);
				}
				
				
			}
		}
		
		System.out.println(instance);
		
	}

}
