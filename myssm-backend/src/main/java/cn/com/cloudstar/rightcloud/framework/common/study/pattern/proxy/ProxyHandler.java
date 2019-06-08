package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 可以代理任何一个类
 * <功能简述>
 * <功能详细描述>
 */
public class ProxyHandler implements InvocationHandler{
	
	private Object  target;
	
	public ProxyHandler(Object  target){
		this.target = target;
	}
 

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		String methodName = method.getName();
		//打印日志
		System.out.println("[@before] The method " + methodName + " begins with ");
		
		//调用目标方法
		Object result = null;
		
		try {
			//前置通知
			result = method.invoke(target, args);
			//返回通知, 可以访问到方法的返回值
		} catch (NullPointerException e) {
			e.printStackTrace();
			//异常通知, 可以访问到方法出现的异常
		}
		
		//后置通知. 因为方法可以能会出异常, 所以访问不到方法的返回值
		
		//打印日志
		System.out.println("[@after] The method ends with " + result);
		 
		return result;
	}
	
	
}
