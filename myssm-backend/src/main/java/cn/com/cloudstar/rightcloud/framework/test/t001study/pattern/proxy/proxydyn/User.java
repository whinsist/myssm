package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxydyn;

/**
 * @author Hong.Wu
 * @date: 21:19 2019/10/10
 */
public class User implements WorkInface,Work2 {

    @Override
    public String work() {
//        不能直接添加
//        System.out.println("startTime" + new Date());

        System.out.println("User  working 。。。。。。。。");

//        System.out.println("entTime" + new Date());
        return "User -> work 返回值";
    }

    @Override
    public void sum() {

    }

    @Override
    public String wotest() {
        return null;
    }
}
