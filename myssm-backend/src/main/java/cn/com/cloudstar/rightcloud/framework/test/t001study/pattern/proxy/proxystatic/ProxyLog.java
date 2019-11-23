package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxystatic;

import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 21:34 2019/10/10
 */
public class ProxyLog {

    private WorkInface workInface;
    // 只能代理某一个类不科学，  可以定义一个父类或接口
//    private User workInface;
//    private Employee workInface;

    public ProxyLog(WorkInface workInface) {
        this.workInface = workInface;
    }

    public String proxyWork() {
        System.out.println("startTime" + new Date());

        workInface.work();

        System.out.println("entTime" + new Date());
        return null;
    }

    public String proxySum() {
        System.out.println("startTime" + new Date());

        workInface.sum();

        System.out.println("entTime" + new Date());
        return null;
    }

}
