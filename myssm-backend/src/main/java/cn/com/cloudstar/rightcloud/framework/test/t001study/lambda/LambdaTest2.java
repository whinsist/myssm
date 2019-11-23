package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        // lambda表达式是对象 是一个函数式接口的实例
        // lambda表达式的语法  LambdaParamters -> LambdaBody
        // Runnable r = (参数) -> {body 函数式接口抽象方法的实现逻辑}
        // 参数是根据抽象方法参数的个数决定的 当只有一个参数时括号里的参数可以省略
        // () -> {}
        // () -> {return 100;} 无参有返回值
        // (x) -> {return 100 +1;} 有参有返回值 只有一个参数可以不要括号
        // 总结 参数可以进行类型推导即参数类型不用指定 大括号也可以取消 return可以取消

        // 无参无返回值
        Runnable r = () -> {System.out.println("run");};
        Runnable r2 = () -> System.out.println("run");
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };

        // 无参有返回值
        Callable<String> c1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "test";
            }
        };
        Callable<String> c2 = () -> {return "test"+123;};
        Callable<String> c3 = () -> "test"+123;

        // 有参有返回值
        Function<Integer, Integer> f1 = (a) -> {
          int sum = 0;
          for (int i=0; i< a; i++) {
              sum += i;
          }
          return sum;
        };
        Integer apply = f1.apply(12);
        System.out.println("apply="+apply);

//        Preconditions.checkNotNull(apply)


    }

}
