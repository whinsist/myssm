package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.aop;


public class Main {
	
	public static void main(String[] args) {
		//直接使用
		ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
		System.out.println("1--->"+arithmeticCalculator.add(1, 2));
		
		
		//动态代理
		ArithmeticCalculator ariImpl = new ArithmeticCalculatorImpl();
		ArithmeticCalculator target = new ArithmeticCalculatorLoggingProxy(ariImpl).getLoggingProxy();
		int result = target.add(11, 12);
		System.out.println("result:" + result);
		 
		
		
 
	}
	
}
