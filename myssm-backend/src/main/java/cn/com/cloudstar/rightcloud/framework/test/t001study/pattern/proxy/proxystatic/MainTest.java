package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxystatic;

/**
 * @author Hong.Wu
 * @date: 21:20 2019/10/10
 */
public class MainTest {
    public static void main(String[] args) {
//        User user = new User();
//        user.work();
        // 出现新需求： 开始工作时要记录开始时间 结束时间   (设计原则不要随便修改原始方法 所有不能在work方法中直接记录)

        // -> 增加一个代理类
//        ProxyUser proxyUser = new ProxyUser();
//        proxyUser.proxyWork();

        // 如果有一个Employee类 也要写一个EmployeeProxy
//        EmployeeProxy proxyEmployee = new EmployeeProxy();
//        proxyUser.proxyWork();


        // 只能代理User
//        ProxyLog proxyLog = new ProxyLog(new User());
//        proxyLog.proxyWork();

        // 新增一个接口 WorkInface (可以代理被实现WorkInface的对象  也是面向接口变成)
        ProxyLog proxyLog1 = new ProxyLog(new User());
        proxyLog1.proxyWork();
        ProxyLog proxyLog2 = new ProxyLog(new Employee());
        proxyLog2.proxyWork();


        // 总结：
        //  可在不修改目标对象的功能前提下 对目标进行扩展
        // 一旦目标接口WorkInface新增了方法目标对象需要维护
        // 只能代理某一类型接口的实例  （所以要出现多个代理类  所以不合适  -> 编译时就指定代理对象 是否可在运行指定代理对象 =动态代理）
        // 只能代理对象的某一个方法

    }
}
