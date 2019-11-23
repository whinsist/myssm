package cn.com.cloudstar.rightcloud.framework.test.t001study.common;

/**
 * @author Hong.Wu
 * @date: 9:14 2019/09/20
 */
public class WhyEnum {

    public static void main(String[] args) {

        // 遍历、switch 等常用操作
        for (EnumTest e : EnumTest.values()) {
            System.out.println("eee:" + e.toString());
            System.out.println("回与此枚举常量的枚举类型相对应的Class对象：" + e.getDeclaringClass());
            System.out.println("此枚举常量的名称：" + e.name());
            System.out.println("返回带指定名称的指定枚举类型的枚举常量：" + EnumTest.valueOf("MON"));
            System.out.println("------------------------------------------------------------------");
        }

        // String -> Enum
        //EnumnNumber enumnNumber = EnumnNumber.valueOf("aa");
        // Enum -> String
        //String name = EnumnNumber.ONE.name();




        EnumTest test = EnumTest.TUE;
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }

        EnumnNumber one = EnumnNumber.ONE;
        // 重写equals可以直接判断
        System.out.println(one.equals("ONE"));

        System.out.println(one.name().equals("ONE"));

        System.out.println(one.equals(EnumnNumber.ONE));
        System.out.println(one.getValue());
    }

}


enum EnumTest {
    MON, TUE, WED, THU, FRI, SAT, SUN;
    // 这段代码实际上调用了7次 Enum(String name, int ordinal)：new Enum<EnumTest>("MON",0); ...
}

enum EnumnNumber {
    ONE(1), TWO(2), THREE(3);

    private int value;

    private EnumnNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isRest() {
        return false;
    }
//    public boolean equals(String other) {
//        return this.name().equals(other);
//    }

}

// 原始的接口定义常量
interface TestConstants {

    String MON = "Mon";
    String TUE = "Tue";
    String WED = "Wed";
    String THU = "Thu";
    String FRI = "Fri";
    String SAT = "Sat";
    String SUN = "Sun";

    interface JobExecutionMode {
        String SINGLE = "single";
        String CYCLE = "cycle";
    }

}
