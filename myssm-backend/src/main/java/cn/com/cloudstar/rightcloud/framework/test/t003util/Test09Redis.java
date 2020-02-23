package cn.com.cloudstar.rightcloud.framework.test.t003util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;

/**
 * @author Hong.Wu
 * @date: 19:01 2019/06/09
 */
public class Test09Redis {

    public static void main(String[] args) {
//        testString();
//          testMap();
//        testList();
//        testSet();


//        testJedis();
        testSentinel();


    }

    private static void testJedis() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.93.100");
        jedis.auth("123456");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }


    private static void testSentinel() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList("192.168.93.100:26379",
                                                            "192.168.93.100:26380",
                                                            "192.168.93.100:26381"));
        // 创建连接池
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels, jedisPoolConfig, "123456");
        // 获取客户端
        Jedis jedis = pool.getResource();
        // 执行两个命令
        jedis.set("mykey", "你好啊2!");
        String value = jedis.get("mykey");
        System.out.println(value);
    }

    private static void testMap() {
        Map<String, String> userInfo = JedisUtil.instance().hgetall("portal:user:abc");

        JedisUtil.instance().hset("vra.catalog.icons".getBytes(), "iconId".getBytes(), "内容".getBytes(), 30 * 60);
        byte[] icon = JedisUtil.instance().hget("vra.catalog.icons".getBytes(), "iconId".getBytes());
        System.out.println(new String(icon));

        // 2hour
        JedisUtil.instance().hset("monitor:agent:host", "cloudHostId123", "{jsonstr}", 7200);
        String monitorAgent = JedisUtil.instance().hget("monitor:agent:host", "cloudHostId123");
        System.out.println("monitorAgent=" + monitorAgent);
        JedisUtil.instance().delHSet("monitor:agent:host", "cloudHostId123");
        System.out.println("monitorAgent=" + monitorAgent);
    }

    private static void testList() {
        // 存list
        Long hostId = 0L;
        JedisUtil.instance().addList("loglist:host:" + hostId, "11111111111");
        JedisUtil.instance().addList("loglist:host:" + hostId, "22222222222");
        JedisUtil.instance().addList("test:key:list", "aaaaaa");
        JedisUtil.instance().addList("test:key:list", "bbbbbb");

        List<String> listPort = JedisUtil.instance().getList("loglist:host:" + hostId);
        System.out.println(listPort);

    }

    private static void testSet() {
        // 对象 属性 值
        JedisUtil.instance().hset("test:key:setObject", "field", "value", 3600 * 24);
        JedisUtil.instance().hset("test:key:setObject", "name", "你好", 3600 * 24);
        JedisUtil.instance().hset("test:key:setObject", "age", "30", 3600 * 24);
        String testSetValue = JedisUtil.instance().hget("test:key:setObject", "name");
        System.out.println("testSetValue=" + testSetValue);

        JedisUtil.instance().addSet("setkey", "v1", "v2", "v3", "v2");
        Set<String> cachePortSet = JedisUtil.instance().getSet("setkey");
        System.out.println(cachePortSet);

        //JedisUtil.instance().hdel("portal:account:id", "aa");

    }

    private static void testString() {
        JedisUtil jedisUtil = JedisUtil.instance();
        jedisUtil.set("test:key:string", "stringvalue");
        String testStringVal = jedisUtil.get("test:key:string");
        System.out.println("test:key:string=" + testStringVal);

        // 存string
        JedisUtil.instance().set("key_set", "字符串");
        String str = JedisUtil.instance().get("key_set");
        System.out.println(str);

    }


}