package cn.com.cloudstar.rightcloud.framework.test.t001study.pattern.proxy.proxystatic;

import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 21:23 2019/10/10
 */
public class ProxyUser {

    public String proxyWork() {
        System.out.println("startTime" + new Date());

        User user = new User();
        user.work();

        System.out.println("entTime" + new Date());
        return null;
    }
}
