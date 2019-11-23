package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxystatic;

/**
 * @author Hong.Wu
 * @date: 21:27 2019/10/10
 */
public class Employee implements WorkInface {
    @Override
    public String work() {
//        不能直接添加
//        System.out.println("startTime" + new Date());

        System.out.println("Employee  working");

//        System.out.println("entTime" + new Date());
        return "Employee -> work 返回值";
    }

    @Override
    public void sum() {

    }
}
