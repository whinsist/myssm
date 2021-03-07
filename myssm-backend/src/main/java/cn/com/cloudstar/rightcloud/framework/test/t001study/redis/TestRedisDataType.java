package cn.com.cloudstar.rightcloud.framework.test.t001study.redis;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.collection.CollectionUtil;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
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
        testList();
//        testSet();

//        testJedis();
//        testCallLua();


//        System.out.println(new String("123").hashCode());
//        System.out.println("123".hashCode());
//
//        System.out.println(new ExBean().hashCode());
//        System.out.println(new ExBean().hashCode());


//
    }


    private static void testJedis() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.93.133", 6379);
        jedis.auth("123456");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));

        Long del = jedis.del("mytext.incr");
        System.out.println("del=========" + del);
        for (int i = 0; i < 10; i++) {
            long incr = jedis.incr("mytext.incr");
            System.out.println(incr);
        }

        System.out.println("IsvProductQuotauuid_b3b9o2ln的值：" + "1"+jedis.get("IsvProductQuotauuid_b3b9o2ln_1"));


    }

    public static void testCallLua(){
        Jedis jedis = new Jedis("192.168.93.133", 6379);
        jedis.auth("123456");
        System.out.println("连接成功");
        String luaStr = "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}";
        Object result = jedis.eval(luaStr, Lists.newArrayList("userName","age"), Lists.newArrayList("Jack","20"));
        System.out.println(result);


        String lua = "local num = redis.call('incr', KEYS[1])\n" +
                "if tonumber(num) == 1 then\n" +
                "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
                "\treturn 1\n" +
                "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                "\treturn 0\n" +
                "else \n" +
                "\treturn 1\n" +
                "end\n";
        Object result2 = jedis.evalsha(jedis.scriptLoad(lua), Arrays.asList("localhost"), Arrays.asList("10", "2"));
        System.out.println(result2);

        String lua3 = "local num = redis.call('incr', KEYS[1])";
        Object result3 = jedis.evalsha(jedis.scriptLoad(lua3));
        System.out.println(result2);
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
//        JedisUtil.instance().addList("test:List:runoobBooks", "redis");
//        JedisUtil.instance().addList("test:List:runoobBooks", "mongodb");
//        JedisUtil.instance().addList("test:List:runoobBooks", "rabitmq");
//        List<String> listPort = JedisUtil.instance().getList("test:List:runoobBooks");
//        System.out.println(listPort);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "user");
        map.put("id", 222);
        map.put("name", "test2");

        Jedis jedis = new Jedis("192.168.93.100", 6380);
        jedis.auth("123456");
        jedis.rpush("logstash-list", JSON.toJSONString(map));


        List<String> list = jedis.lrange("logstash-list", 0, -1);
        System.out.println(list);

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
