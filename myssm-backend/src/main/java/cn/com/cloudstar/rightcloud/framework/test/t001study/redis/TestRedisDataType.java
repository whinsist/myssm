package cn.com.cloudstar.rightcloud.framework.test.t001study.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.test.t003util.excel.bean.ExBean;

/**
 * @author Hong.Wu
 * @date: 19:01 2019/06/09
 */
public class TestRedisDataType {

    public static void main(String[] args) {
//        testString();
//        testHash();
//        testList();
//        testSet();

//        testJedis();


        System.out.println(new String("123").hashCode());
        System.out.println("123".hashCode());

        System.out.println(new ExBean().hashCode());
        System.out.println(new ExBean().hashCode());
    }



    private static void testJedis() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.93.100");
        jedis.auth("123456");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
    }




    private static void testHash() {
        //Redis hash 是一个键值(key=>value)对集合。
        //Redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象。
        // HMSET "test:Hash:user" username "abc"
        // HGET "test:Hash:user" username
        JedisUtil.instance().hset("test:Hash:user", "username", "abc");
        JedisUtil.instance().hset("test:Hash:user", "age", "20");

        Map<String, String> userInfo = JedisUtil.instance().hgetall("test:Hash:user");
        System.out.println(userInfo);
        System.out.println(JedisUtil.instance().hget("test:Hash:user", "username"));
        System.out.println(JedisUtil.instance().hget("test:Hash:user", "aget"));
    }

    private static void testList() {
        // lpush test:List:runoobBooks mongodb
        // lrange test:List:runoobBooks 0 10
        JedisUtil.instance().addList("test:List:runoobBooks", "redis");
        JedisUtil.instance().addList("test:List:runoobBooks", "mongodb");
        JedisUtil.instance().addList("test:List:runoobBooks", "rabitmq");

        List<String> listPort = JedisUtil.instance().getList("test:List:runoobBooks");
        System.out.println(listPort);

    }

    private static void testSet() {
        // sadd test:Set:setObject redis
        // DEL test:Set:setObject
        // smembers test:Set:setObject
        JedisUtil.instance().addSet("test:Set:setObject", "name", "你好");
        JedisUtil.instance().addSet("test:Set:setObject", "age", "30");
        Set<String> sets = JedisUtil.instance().getSet("test:Set:setObject");
        System.out.println("sets=" + sets);
        JedisUtil.instance().containsInSet("test:Set:setObject", "你好");
    }

    private static void testString() {
        JedisUtil jedisUtil = JedisUtil.instance();
        jedisUtil.set("test:String:abc", "stringvalue");
        String testStringVal = jedisUtil.get("test:String:abc");
        System.out.println("test:String:abc=" + testStringVal);

        // 存string
        JedisUtil.instance().set("key_set", "字符串");
        String str = JedisUtil.instance().get("key_set");
        System.out.println(str);
    }


}
