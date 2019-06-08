package cn.com.cloudstar.rightcloud.framework.common.study.pattern.refect;

public class CarVoTest {
	private String brand;
	private double price;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		//System.out.println("setBrand..");
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CarVoTest(){
		//System.out.println("construct..");
	}
	
	public CarVoTest(String brand, double price) {
		super();
		this.brand = brand;
		this.price = price;
	}
	public void test(String s){
		
	}
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", price=" + price + ", name=" + name
				+ "]";
	}
	
	 
	 
}
