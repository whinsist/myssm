package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.strategy;

public class OperationMultiply implements Strategy{
   @Override
   public int doOperation(int num1, int num2) {
      return num1 * num2;
   }
}