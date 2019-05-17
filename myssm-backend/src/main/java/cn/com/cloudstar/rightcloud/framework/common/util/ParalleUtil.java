package cn.com.cloudstar.rightcloud.framework.common.util;

import io.parallec.core.ParallelClient;
import io.parallec.core.ParallelTask;
import io.parallec.core.bean.ping.PingMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu.Hong
 * @date: 18:16 2018/3/19
 */
public class ParalleUtil {


    /**
     * 通过ping的方式获取 能ping通的ip
     * @param ipList
     * @return
     */
    public static List<String> getLiveIpList(List<String> ipList) {
        List<String> liveIpList = new ArrayList<>();
        ParallelClient pc = new ParallelClient();
        ParallelTask task = pc.preparePing().setConcurrency(1500).setTargetHostsFromList(ipList)
                .setPingMode(PingMode.INET_ADDRESS_REACHABLE_NEED_ROOT).setPingNumRetries(1).setPingTimeoutMillis(500)
                .execute((res, responseContext) -> {
                    if ("LIVE".equals(res.getStatusCode()) && StringUtil.isNotBlank(res.getHost())) {
                        liveIpList.add(res.getHost());
                    }
                });
        pc.releaseExternalResources();
        return liveIpList;
    }
    public static List<String> getLiveIpList(String ip) {
        if (StringUtil.isBlank(ip)) {
            return null;
        }
        List<String> ipList = new ArrayList<>();
        ipList.add(ip);
        return getLiveIpList(ipList);
    }




}
