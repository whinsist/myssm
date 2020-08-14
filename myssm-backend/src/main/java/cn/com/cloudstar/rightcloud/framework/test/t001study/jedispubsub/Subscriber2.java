package cn.com.cloudstar.rightcloud.framework.test.t001study.jedispubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Hong.Wu
 * @date: 23:35 2020/01/20
 */
public class Subscriber2 extends JedisPubSub {
    public static final String WEBSOCKET_CHANNEL = "push-message-channel";

    @Override
    public void onMessage(String channel, String message) {
        // 如果有多个订阅者 多个订阅者都能收到
        System.out.println(channel + "@2--------->" + message);
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.93.100");
//        jedis.auth("123456");
        Jedis jedis = new Jedis("192.168.93.132");
        //jedis.auth("");
        System.out.println("订阅消息..");
        jedis.subscribe(new Subscriber2(), WEBSOCKET_CHANNEL);
    }
}
