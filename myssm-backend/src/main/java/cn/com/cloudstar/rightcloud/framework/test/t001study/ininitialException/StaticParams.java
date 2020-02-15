package cn.com.cloudstar.rightcloud.framework.test.t001study.ininitialException;

import java.util.ArrayList;
import java.util.List;

public class StaticParams {

  private static int NUM_A = getA();
  private static int NUM_B = getB();
  private static List<String> LIST_A = getListA();
  
  
  private StaticParams() {
    System.out.println("初始构造方法");
  }
  
  public static StaticParams getInstance() {
    return new StaticParams();
  }
  
  private static int getA(){
    System.out.println("初始化A");
    return 5;
  }
  
  private static int getB() {
    System.out.println("初始化B");
    return 10;
  }
  
  private static List<String> getListA() {
    System.out.println("初始化List");
    return new ArrayList<String>();
  }
  
  public static void main(String args[]){
    StaticParams.getInstance();
    // 如果交换NUM_A和NUM_B的位置，那么结果就是：
    // 即：在某个静态变量的初始化还未执行之前就使用该静态变量。
  }
}