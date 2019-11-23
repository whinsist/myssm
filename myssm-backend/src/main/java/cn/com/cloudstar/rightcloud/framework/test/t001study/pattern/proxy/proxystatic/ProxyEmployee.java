package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxystatic;

import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/10/10
 */
public class ProxyEmployee {
    public String proxyWork() {
        System.out.println("startTime" + new Date());

        Employee user = new Employee();
        user.work();

        System.out.println("entTime" + new Date());
        return null;
    }
}
