
package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
	
	public static void main(String[] args) throws Exception {
		HireHouseImpl hhi = new HireHouseImpl();
		HireHouse house = (HireHouse) Proxy.newProxyInstance(hhi.getClass().getClassLoader(), hhi.getClass().getInterfaces(),  new ProxyHandler(hhi));
		house.hire();
		
		

		
		
		
	}
	
	 
}
