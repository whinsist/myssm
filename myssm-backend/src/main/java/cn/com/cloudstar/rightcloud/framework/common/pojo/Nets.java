/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

public class Nets {

    /**
     * 网络开始IP
     */
    private String startIP;
    /**
     * 网络结果IP
     */
    private String endIP;
    /**
     * 子网掩码
     */
    private String netMask;

    /**
     * 取得startIP
     *
     * @return startIP
     */
    public String getStartIP() {
        return startIP;
    }

    /**
     * 设置startIP
     *
     * @param startIP startIP
     */
    public void setStartIP(String startIP) {
        this.startIP = startIP;
    }

    /**
     * 取得endIP
     *
     * @return endIP
     */
    public String getEndIP() {
        return endIP;
    }

    /**
     * 设置endIP
     *
     * @param endIP endIP
     */
    public void setEndIP(String endIP) {
        this.endIP = endIP;
    }

    /**
     * 取得netMask
     *
     * @return netMask
     */
    public String getNetMask() {
        return netMask;
    }

    /**
     * 设置netMask
     *
     * @param netMask netMask
     */
    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }


}
