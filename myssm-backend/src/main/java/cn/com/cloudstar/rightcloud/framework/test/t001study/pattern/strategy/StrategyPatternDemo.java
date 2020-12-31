package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.strategy;

/**
 * https://www.runoob.com/design-pattern/strategy-pattern.html
 */
public class StrategyPatternDemo {
   public static void main(String[] args) {
      //使用 Context 来查看当它改变策略 Strategy 时的行为变化。
      Context context1 = new Context(new OperationAdd());
      System.out.println("10 + 5 = " + context1.executeStrategy(10, 5));

      Context context2 = new Context(new OperationSubtract());
      System.out.println("10 - 5 = " + context2.executeStrategy(10, 5));

      Context context3 = new Context(new OperationMultiply());
      System.out.println("10 * 5 = " + context3.executeStrategy(10, 5));
   }
}