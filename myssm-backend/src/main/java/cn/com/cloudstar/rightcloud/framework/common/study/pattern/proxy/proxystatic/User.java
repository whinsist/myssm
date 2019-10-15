package cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.proxystatic;

/**
 * @author Hong.Wu
 * @date: 21:19 2019/10/10
 */
public class User implements WorkInface {

    @Override
    public String work() {
//        不能直接添加
//        System.out.println("startTime" + new Date());

        System.out.println("User  working");

//        System.out.println("entTime" + new Date());
        return "User -> work 返回值";
    }

    @Override
    public void sum() {

    }
}
