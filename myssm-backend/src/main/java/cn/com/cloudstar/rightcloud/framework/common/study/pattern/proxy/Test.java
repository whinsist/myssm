
package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
	
	public static void main(String[] args) throws Exception {
		HireHouseImpl hhi = new HireHouseImpl();
		HireHouse house = (HireHouse) Proxy.newProxyInstance(hhi.getClass().getClassLoader(), hhi.getClass().getInterfaces(),  new ProxyHandler(hhi));
		house.hire();
		
		
		//测试annotation
		Class clazz = HireHouseImpl.class;
		Object instance = clazz.newInstance();
		Method method = clazz.getMethod("hire", null);
		boolean hasAnno = method.isAnnotationPresent(AnnoTest.class);
		System.out.println("hasAnno="+hasAnno);
		if(hasAnno){
			AnnoTest annotation = method.getAnnotation(AnnoTest.class);
			String value = annotation.value();
			System.out.println(value);
		}
		
		
		Annotation[] annotations = method.getAnnotations();
		for(Annotation annotation : annotations){
			//System.out.println("annotation="+annotation+"  "+annotation);
		}
		
		
		
	}
	
	 
}
