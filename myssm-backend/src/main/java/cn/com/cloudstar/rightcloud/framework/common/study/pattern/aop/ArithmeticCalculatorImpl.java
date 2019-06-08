package cn.com.cloudstar.rightcloud.framework.common.study.pattern.aop;

import org.springframework.stereotype.Component;

@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
	
	private String name;
	
	public void setName(String name) {
		System.out.println("ArithmeticCalculatorImpl setName..");
		this.name = name;
	}
	 
	public String getName() {
		return name;
	}

	public int add(int i, int j) {
		int result = i + j;
		return result;
	}

	
	public int sub(int i, int j) {
		int result = i - j;
		return result;
	}

	
	public int mul(int i, int j) {
		int result = i * j;
		return result;
	}

	
	public int div(int i, int j) {
		int result = i / j;
		return result;
	}
	
	public ArithmeticCalculatorImpl(){
		System.out.println("ArithmeticCalculatorImpl 构造方法..");
	}
}
