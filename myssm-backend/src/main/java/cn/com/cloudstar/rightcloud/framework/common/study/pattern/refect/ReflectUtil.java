package cn.com.cloudstar.rightcloud.framework.common.study.pattern.refect;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static void main(String[] args) throws Exception {
		CarVoTest car = new CarVoTest();
		car.setName("测试");
		car.setBrand("audi");
		car.setPrice(400000);

		CarVoTest obj = (CarVoTest) copyObj(car);
		System.out.println(obj);
	}

	private static Object copyObj(Object obj) throws Exception {
		Class clazz  = obj.getClass();
		Constructor[] constructors = clazz.getDeclaredConstructors();
		Object instance = constructors[0].newInstance(null);
		//得到字段
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			//用get方法获取值    用set方法设置值
			String fieldName = field.getName();
			String getMethodName = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			String setMethodName = "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			Method gmethod = clazz.getDeclaredMethod(getMethodName, null);
			Object invokeVal = gmethod.invoke(obj, null);
			//Method smethod = clazz.getDeclaredMethod(setMethodName, new Class[]{gmethod.getReturnType()});
			Method smethod = clazz.getDeclaredMethod(setMethodName, new Class[]{field.getType()});
			smethod.invoke(instance, invokeVal);
		}
		return instance;
	}

}
