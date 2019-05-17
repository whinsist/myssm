/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Nets;
import com.google.common.base.Strings;
import sun.net.util.IPAddressUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IP相关信息计算工具类
 *
 * @author 刘洋
 */
public class IPUtil {
    public static void main(String[] args) {
        System.out.println(getNetMask("255.255.255.0"));

        System.out.println(getPoolMax(getNetMask("255.255.255.128")));

        Nets aaa = getNets("10.229.1.1", 24);
        System.out.println(aaa.getStartIP());
        System.out.println(aaa.getEndIP());

        Nets bbb = getNets("10.229.1.1", "255.255.255.0");
        System.out.println(bbb.getStartIP());
        System.out.println(bbb.getEndIP());
    }

    /**
     * 根据起始IP地址和子网掩码计算终止IP
     */
    public static Nets getNets(String startIP, int netmask) {
        return getNets(startIP, getMask(netmask));
    }

    /**
     * 根据起始IP地址和子网掩码计算终止IP
     */
    public static Nets getNets(String startIP, String netmask) {
        Nets nets = new Nets();
        String[] start = Negation(startIP, netmask).split("\\.");
        nets.setStartIP(start[0] + "." + start[1] + "." + start[2] + "." + (Integer.valueOf(start[3]) + 1));
        nets.setEndIP(TaskOR(Negation(startIP, netmask), netmask));
        nets.setNetMask(netmask);
        return nets;
    }

    /**
     * 根据掩码位计算掩码
     */
    public static String getMask(int masks) {
        if (masks == 1) {
            return "128.0.0.0";
        } else if (masks == 2) {
            return "192.0.0.0";
        } else if (masks == 3) {
            return "224.0.0.0";
        } else if (masks == 4) {
            return "240.0.0.0";
        } else if (masks == 5) {
            return "248.0.0.0";
        } else if (masks == 6) {
            return "252.0.0.0";
        } else if (masks == 7) {
            return "254.0.0.0";
        } else if (masks == 8) {
            return "255.0.0.0";
        } else if (masks == 9) {
            return "255.128.0.0";
        } else if (masks == 10) {
            return "255.192.0.0";
        } else if (masks == 11) {
            return "255.224.0.0";
        } else if (masks == 12) {
            return "255.240.0.0";
        } else if (masks == 13) {
            return "255.248.0.0";
        } else if (masks == 14) {
            return "255.252.0.0";
        } else if (masks == 15) {
            return "255.254.0.0";
        } else if (masks == 16) {
            return "255.255.0.0";
        } else if (masks == 17) {
            return "255.255.128.0";
        } else if (masks == 18) {
            return "255.255.192.0";
        } else if (masks == 19) {
            return "255.255.224.0";
        } else if (masks == 20) {
            return "255.255.240.0";
        } else if (masks == 21) {
            return "255.255.248.0";
        } else if (masks == 22) {
            return "255.255.252.0";
        } else if (masks == 23) {
            return "255.255.254.0";
        } else if (masks == 24) {
            return "255.255.255.0";
        } else if (masks == 25) {
            return "255.255.255.128";
        } else if (masks == 26) {
            return "255.255.255.192";
        } else if (masks == 27) {
            return "255.255.255.224";
        } else if (masks == 28) {
            return "255.255.255.240";
        } else if (masks == 29) {
            return "255.255.255.248";
        } else if (masks == 30) {
            return "255.255.255.252";
        } else if (masks == 31) {
            return "255.255.255.254";
        } else if (masks == 32) {
            return "255.255.255.255";
        }
        return "";
    }

    /**
     * 根据掩码位计算掩码
     */
    public static int getStandardMask(String masks) {
        if ("128.0.0.0".equals(masks)) {
            return 1;
        } else if ("192.0.0.0".equals(masks)) {
            return 2;
        } else if ("224.0.0.0".equals(masks)) {
            return 3;
        } else if ("240.0.0.0".equals(masks)) {
            return 4;
        } else if ("248.0.0.0".equals(masks)) {
            return 5;
        } else if ("252.0.0.0".equals(masks)) {
            return 6;
        } else if ("254.0.0.0".equals(masks)) {
            return 7;
        } else if ("255.0.0.0".equals(masks)) {
            return 8;
        } else if ("255.128.0.0".equals(masks)) {
            return 9;
        } else if ("255.192.0.0".equals(masks)) {
            return 10;
        } else if ("255.224.0.0".equals(masks)) {
            return 11;
        } else if ("255.240.0.0".equals(masks)) {
            return 12;
        } else if ("255.248.0.0".equals(masks)) {
            return 13;
        } else if ("255.252.0.0".equals(masks)) {
            return 14;
        } else if ("255.254.0.0".equals(masks)) {
            return 15;
        } else if ("255.255.0.0".equals(masks)) {
            return 16;
        } else if ("255.255.128.0".equals(masks)) {
            return 17;
        } else if ("255.255.192.0".equals(masks)) {
            return 18;
        } else if ("255.255.224.0".equals(masks)) {
            return 19;
        } else if ("255.255.240.0".equals(masks)) {
            return 20;
        } else if ("255.255.248.0".equals(masks)) {
            return 21;
        } else if ("255.255.252.0".equals(masks)) {
            return 22;
        } else if ("255.255.254.0".equals(masks)) {
            return 23;
        } else if ("255.255.255.0".equals(masks)) {
            return 24;
        } else if ("255.255.255.128".equals(masks)) {
            return 25;
        } else if ("255.255.255.192".equals(masks)) {
            return 26;
        } else if ("255.255.255.224".equals(masks)) {
            return 27;
        } else if ("255.255.255.240".equals(masks)) {
            return 28;
        } else if ("255.255.255.248".equals(masks)) {
            return 29;
        } else if ("255.255.255.252".equals(masks)) {
            return 30;
        } else if ("255.255.255.254".equals(masks)) {
            return 31;
        } else if ("255.255.255.255".equals(masks)) {
            return 32;
        }
        return 0;
    }

    /**
     * temp1根据temp2取反
     */
    private static String Negation(String StartIP, String netmask) {
        String[] temp1 = StartIP.trim().split("\\.");
        String[] temp2 = netmask.trim().split("\\.");
        int[] rets = new int[4];
        for (int i = 0; i < 4; i++) {
            rets[i] = Integer.parseInt(temp1[i]) & Integer.parseInt(temp2[i]);
        }
        return rets[0] + "." + rets[1] + "." + rets[2] + "." + rets[3];
    }

    /**
     * temp1根据temp2取或
     */
    private static String TaskOR(String StartIP, String netmask) {
        String[] temp1 = StartIP.trim().split("\\.");
        String[] temp2 = netmask.trim().split("\\.");
        int[] rets = new int[4];
        for (int i = 0; i < 4; i++) {
            rets[i] = 255 - (Integer.parseInt(temp1[i]) ^ Integer.parseInt(temp2[i]));
        }
        return rets[0] + "." + rets[1] + "." + rets[2] + "." + (rets[3] - 1);
    }

    /**
     * 计算子网大小
     */
    public static int getPoolMax(int netmask) {
        if (netmask <= 0 || netmask >= 32) {
            return 0;
        }
        int bits = 32 - netmask;
        return (int) Math.pow(2, bits) - 2;
    }

    /**
     * 转换为掩码位数
     */
    public static int getNetMask(String netmarks) {
        StringBuffer sbf;
        String str;
        int inetmask = 0, count = 0;
        String[] ipList = netmarks.split("\\.");
        for (int n = 0; n < ipList.length; n++) {
            sbf = toBin(Integer.parseInt(ipList[n]));
            str = sbf.reverse().toString();
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                i = str.indexOf('1', i);
                if (i == -1) {
                    break;
                }
                count++;
            }
            inetmask += count;
        }
        return inetmask;
    }

    /**
     * 判断是否是内网ip.
     *
     * @param ip the ip
     * @return the boolean
     */
    public static boolean internalIp(String ip) {
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        return internalIp(addr);
    }


    /**
     * 判断是否是内网ip.
     *
     * @param addr the addr
     * @return the boolean
     */
    public static boolean internalIp(byte[] addr) {
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;

        }
    }

    private static StringBuffer toBin(int x) {
        StringBuffer result = new StringBuffer();
        result.append(x % 2);
        x /= 2;
        while (x > 0) {
            result.append(x % 2);
            x /= 2;
        }
        return result;
    }

    /**
     * 获取两个ip地址之间所有的ip
     */
    public static List<String> getNetWorkList(Nets nets) {
        String startIp = nets.getStartIP();
        String endIp = nets.getEndIP();
        return getNetWorkList(startIp, endIp);
    }

    /**
     * 获取两个ip地址之间所有的ip
     *
     * @param startIp
     * @param endIp
     * @return
     */
    public static List<String> getNetWorkList(String startIp, String endIp) {
        ArrayList<String> ips = new ArrayList<String>();
        String[] ipFrom = startIp.split("\\.");
        String[] ipTo = endIp.split("\\.");
        int[] int_ipf = new int[4];
        int[] int_ipt = new int[4];
        for (int i = 0; i < 4; i++) {
            int_ipf[i] = Integer.parseInt(ipFrom[i]);
            int_ipt[i] = Integer.parseInt(ipTo[i]);
        }
        for (int A = int_ipf[0]; A <= int_ipt[0]; A++) {
            for (int B = (A == int_ipf[0] ? int_ipf[1] : 0); B <= (A == int_ipt[0] ? int_ipt[1]
                    : 255); B++) {
                for (int C = (B == int_ipf[1] ? int_ipf[2] : 0); C <= (B == int_ipt[1] ? int_ipt[2]
                        : 255); C++) {
                    for (int D = (C == int_ipf[2] ? int_ipf[3] : 0); D <= (C == int_ipt[2] ? int_ipt[3]
                            : 255); D++) {
                        ips.add(new String(A + "." + B + "." + C + "." + D));
                    }
                }
            }
        }
        return ips;
    }

    public static List<String> getIpList(List<String> ipArray, String ip) {
        if (ipArray == null) {
            ipArray = new ArrayList<>();
        }
        if (!Strings.isNullOrEmpty(ip) && ip.contains(",")) {
            Collections.addAll(ipArray, ip.split(","));
        } else if (!Strings.isNullOrEmpty(ip)) {
            ipArray.add(ip);
        }
        return ipArray;
    }

    /**
     * 从URL中获取IP地址
     */
    public static String getIpFromUrl(String url) {
        if (StringUtil.isNullOrEmpty(url)) {
            return url;
        }

        /**
         * 示例 1：https://xx.xx.xx.xx:443/sdk/vimService
         * 示例 2：https://xx.xx.xx.xx/sdk/vimService
         */
        String[] split = url.split("/");
        if (split.length > 3) {
            return split[2].split(":")[0];
        }
        return url;
    }


    /**
     * 根据子网和掩码计算ip段
     *
     * @param startIP
     * @param netmask
     * @returns {Array}
     */
    public static List<String> computeAllIpAddress(String startIP, String netmask) {
        Nets nets = getNets(startIP, netmask);

        return getNetWorkList(nets);
    }

    /**
     * 判断IP是否在网段内
     * @param ip
     * @param cidr
     * @return
     */
    public static boolean checkIpInCidr (String ip, String cidr) {
        List<String> allIpAddress = computeAllIpAddress(cidr);

        return allIpAddress.contains(ip);
    }

    /**
     * 计算网段内ip的个数
     *
     * @param cidr
     * @return
     */
    public static Integer computeTotalIpCount(String cidr) {
        List<String> allIpAddress = computeAllIpAddress(cidr);

        return allIpAddress.size();
    }


    /**
     * 根据cidr计算ip段
     *
     * @param cidr
     * @returns {Array}
     */
    public static List<String> computeAllIpAddress(String cidr) {
        if (Strings.isNullOrEmpty(cidr)) {
            return new ArrayList<>();
        }
        String[] split = cidr.split("/");
        String startIp = split[0];
        String mask = getMask(Integer.parseInt(split[1]));

        return computeAllIpAddress(startIp, mask);
    }
}
