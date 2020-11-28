package cn.com.cloudstar.rightcloud.framework.test.t001study.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author Hong.Wu
 * @date: 17:45 2020/09/21
 */
public class RedisCluster {

    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.67.7.89", 7001));
        nodes.add(new HostAndPort("10.67.7.89", 7002));
        nodes.add(new HostAndPort("10.67.7.89", 7003));
        nodes.add(new HostAndPort("10.67.7.89", 7004));
        nodes.add(new HostAndPort("10.67.7.89", 7005));
        nodes.add(new HostAndPort("10.67.7.89", 7006));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("jedisClusterKey", "测试");
        System.out.println(jedisCluster.get("jedisClusterKey"));


    }
}
