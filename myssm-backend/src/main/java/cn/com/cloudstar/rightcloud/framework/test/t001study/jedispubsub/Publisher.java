package cn.com.cloudstar.rightcloud.framework.test.t001study.jedispubsub;

import redis.clients.jedis.Jedis;

/**
 * @author Hong.Wu
 * @date: 23:45 2020/01/20
 */
public class Publisher {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.93.100");
        jedis.auth("123456");
        System.out.println("发布消息.. ");
        jedis.publish("cctv", "在吗");
    }
}
