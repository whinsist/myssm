package cn.com.cloudstar.rightcloud.framework.test.t001study.jedispubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Hong.Wu
 * @date: 23:35 2020/01/20
 */
public class Subscriber extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(channel + "--------->" + message);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.93.100");
        jedis.auth("123456");
        System.out.println("订阅消息..");
        jedis.subscribe(new Subscriber(), "cctv");
    }
}
