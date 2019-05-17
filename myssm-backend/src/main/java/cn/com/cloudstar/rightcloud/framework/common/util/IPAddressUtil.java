package cn.com.cloudstar.rightcloud.framework.common.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author Hong.Wu
 * @date: 19:23 2018/06/12
 */
public class IPAddressUtil {

    /**
     * 获取远程主机的ip
     *
     * @param request
     * @return
     */
    public static String getRemoteHostIp(HttpServletRequest request){
        try {
            String ipAddress = request.getHeader("x-forwarded-for");
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                    //根据网卡取本机配置的IP
                    ipAddress= InetAddress.getLocalHost().getHostAddress();
                }
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if(ipAddress != null && ipAddress.length() > 15){
                if(ipAddress.indexOf(",")>0){
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                }
            }
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
