/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.cache;


import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import cn.com.cloudstar.rightcloud.framework.common.util.ClassLoaderUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.StringUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * 与spring结合版本 Created by qct on 2015/7/7.
 */
public class JedisUtil {
    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private ShardedJedisPool shardedJedisPool;

    private JedisUtil() {
        this.init();
    }

    /**
     * Instance jedis util.
     *
     * @return the jedis util
     */
    public static JedisUtil instance() {
        return SingletonHolder.JEDIS_UTIL;
    }

    public static Map<String, String> transMap4Jedis(Map<String, Object> param) {
        Map<String, String> result = Maps.newHashMap();
        param.forEach((s, o) -> {
            if (o == null) {
                result.put(s, null);
            } else {
                if (o instanceof Date) {
                    result.put(s, StringUtil.dateFormat((Date) o, StringUtil.DF_YMD_24));
                } else {
                    result.put(s, o.toString());
                }
            }
        });
        return result;
    }

    /**
     * Init.
     */
    private void init() {
        JedisPoolConfig config = new JedisPoolConfig();
        List<JedisShardInfo> shardInfos = new ArrayList<>();

        // 如果环境变量中有配置信息，优先使用环境变量中的配置信息；反之，则使用配置文件中的信息
        Properties sysProps = System.getProperties();
        if (Strings.isNullOrEmpty(sysProps.getProperty("cloudstar.redis.host"))) {
            Properties p = new Properties();
            InputStream is = ClassLoaderUtil.getResourceAsStream("redis.properties", JedisUtil.class);
            if (is != null) {
                try (InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {
                    p.load(reader);
                } catch (IOException e) {
                    logger.error("Load redis.properties failed.", e);
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        logger.warn("redis.properties stream close failed.", e);
                    }
                }
            }
            config.setMaxTotal(Integer.parseInt(p.getProperty("redis.pool.max.total")));
            config.setMaxIdle(Integer.parseInt(p.getProperty("redis.pool.max.idle")));
            config.setMaxWaitMillis(Integer.parseInt(p.getProperty("redis.pool.max.wait.millis")));
            config.setTestOnBorrow(Boolean.parseBoolean(p.getProperty("redis.pool.test.on.borrow")));
            config.setTestOnReturn(Boolean.parseBoolean(p.getProperty("redis.pool.test.on.return")));
            config.setTestWhileIdle(Boolean.parseBoolean(p.getProperty("redis.pool.test.while.idle")));
            config.setNumTestsPerEvictionRun(Integer.parseInt(p.getProperty("redis.pool.num.tests.per.eviction.run")));
            config.setTimeBetweenEvictionRunsMillis(Integer.parseInt(p.getProperty(
                    "redis.pool.time.between.eviction.runs.millis")));
            config.setMinEvictableIdleTimeMillis(Integer.parseInt(p.getProperty("redis.pool.min.evictable.idle.time.millis")));
            config.setSoftMinEvictableIdleTimeMillis(Integer.parseInt(p.getProperty(
                    "redis.pool.soft.min.evictable.idle.time.millis")));
            config.setJmxEnabled(Boolean.parseBoolean(p.getProperty("redis.pool.jmx.enabled")));
            config.setJmxNamePrefix(p.getProperty("redis.pool.jmx.name.prefix"));
            config.setBlockWhenExhausted(Boolean.parseBoolean(p.getProperty("redis.pool.block.when.exhausted")));
            // Node
            Enumeration<?> names = p.propertyNames();
            while (names.hasMoreElements()) {
                String key = names.nextElement().toString();
                if (key.startsWith("redis.node.")) {
                    String[] host = p.getProperty(key).split(":");
                    shardInfos.add(new JedisShardInfo(host[0], Integer.parseInt(host[1])));
                }
            }
        } else {
            shardInfos.add(new JedisShardInfo(sysProps.getProperty("cloudstar.redis.host"),
                    Integer.parseInt(sysProps.getProperty("cloudstar.redis.port"))));
        }

        shardedJedisPool = new ShardedJedisPool(config, shardInfos);
    }

    /**
     * 设置一个key在某个时间点过期
     *
     * @param key           key值
     * @param unixTimestamp unix时间戳，从1970-01-01 00:00:00开始到现在的秒数
     * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
     */
    public long expireAt(String key, int unixTimestamp) {
        if (key == null || key.equals("")) {
            return 0;
        }

        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.expireAt(key, unixTimestamp);
        } catch (Exception ex) {
            logger.error("EXPIRE error[key=" + key + " unixTimestamp=" + unixTimestamp + "]" + ex.getMessage(), ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    private void returnBrokenResource(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            logger.error("returnBrokenResource error.", e);
        }
    }

    private void returnResource(ShardedJedis shardedJedis) {
        try {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        } catch (Exception e) {
            logger.error("returnResource error.", e);
        }
    }

    /**
     * 截断一个List
     *
     * @param key   列表key
     * @param start 开始位置 从0开始
     * @param end   结束位置
     * @return 状态码
     */
    public String trimList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return "-";
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.ltrim(key, start, end);
        } catch (Exception ex) {
            logger.error("LTRIM 出错[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage(), ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return "-";
    }

    /**
     * 检查Set长度
     */
    public long countSet(String key) {
        if (key == null) {
            return 0;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.scard(key);
        } catch (Exception ex) {
            logger.error("countSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 添加到Set中（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     */
    public boolean addSet(String key, int seconds, String... value) {
        boolean result = addSet(key, value);
        if (result) {
            long i = expire(key, seconds);
            return i == 1;
        }
        return false;
    }

    /**
     * 添加到Set中
     */
    public boolean addSet(String key, String... value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.sadd(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("addSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }
    /**
     * 设置一个key的过期时间（单位：秒）
     *
     * @param key     key值
     * @param seconds 多少秒后过期
     * @return 1：设置了过期时间 0：没有设置过期时间/不能设置过期时间
     */
    public long expire(String key, int seconds) {
        if (key == null || key.equals("")) {
            return 0;
        }

        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.expire(key, seconds);
        } catch (Exception ex) {
            logger.error("EXPIRE error[key=" + key + " seconds=" + seconds + "]" + ex.getMessage(), ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * @return 判断值是否包含在set中
     */
    public boolean containsInSet(String key, String value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.sismember(key, value);
        } catch (Exception ex) {
            logger.error("containsInSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获取Set
     */
    public Set<String> getSet(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.smembers(key);
        } catch (Exception ex) {
            logger.error("getSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptySet();
    }

    /**
     * 从set中删除value
     */
    public boolean removeSetValue(String key, String... value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.srem(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("removeSetValue error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 从list中删除value 默认count 1
     *
     * @param values 值list
     */
    public int removeListValue(String key, List<String> values) {
        return removeListValue(key, 1, values);
    }

    /**
     * 从list中删除value
     *
     * @param values 值list
     */
    public int removeListValue(String key, long count, List<String> values) {
        int result = 0;
        if (!CollectionUtils.isEmpty(values)) {
            for (String value : values) {
                if (removeListValue(key, count, value)) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 从list中删除value
     *
     * @param count 要删除个数
     */
    public boolean removeListValue(String key, long count, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.lrem(key, count, value);
            return true;
        } catch (Exception ex) {
            logger.error("removeListValue error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 截取List
     *
     * @param start 起始位置
     * @param end   结束位置
     */
    public List<String> rangeList(String key, long start, long end) {
        if (key == null || key.equals("")) {
            return Collections.emptyList();
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lrange(key, start, end);
        } catch (Exception ex) {
            logger.error("rangeList 出错[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage(), ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    /**
     * 检查List长度
     */
    public long countList(String key) {
        if (key == null) {
            return 0;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.llen(key);
        } catch (Exception ex) {
            logger.error("countList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    /**
     * 添加到List中（同时设置过期时间）
     *
     * @param key     key值
     * @param seconds 过期时间 单位s
     */
    public boolean addList(String key, int seconds, String... value) {
        boolean result = addList(key, value);
        if (result) {
            long i = expire(key, seconds);
            return i == 1;
        }
        return false;
    }

    /**
     * 添加到List
     */
    public boolean addList(String key, String... value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.rpush(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("setList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    public boolean addList(byte[] key, byte[] value) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.rpush(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("addList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 添加到List
     */
    public boolean addList(String key, String value, int expire) {
        if (key == null || value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.rpush(key, value);
            if (expire != 0) {
                shardedJedis.expire(key, expire);
            }
            return true;
        } catch (Exception ex) {
            logger.error("setList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 添加到List(只新增)
     */
    public boolean addList(String key, List<String> list) {
        if (Strings.isNullOrEmpty(key) || CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (String value : list) {
            addList(key, value);
        }
        return true;
    }

    /**
     * 获取List
     */
    public List<String> getList(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lrange(key, 0, -1);
        } catch (Exception ex) {
            logger.error("getList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    public List<byte[]> getList(byte[] key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.lrange(key, 0, -1);
        } catch (Exception ex) {
            logger.error("getList error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    /**
     * 设置HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @param value  Json String or String value
     */
    public boolean setHSet(String domain, String key, String value) {
        if (value == null) {
            return false;
        }
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.hset(domain, key, value);
            return true;
        } catch (Exception ex) {
            logger.error("setHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获得HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return Json String or String value
     */
    public String getHSet(String domain, String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.hget(domain, key);
        } catch (Exception ex) {
            logger.error("getHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 删除HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return 删除的记录数
     */
    public long delHSet(String domain, String key) {
        ShardedJedis shardedJedis = null;
        long count = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            count = shardedJedis.hdel(domain, key);
        } catch (Exception ex) {
            logger.error("delHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return count;
    }

    /**
     * 删除HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return 删除的记录数
     */
    public long delHSet(String domain, String... key) {
        ShardedJedis shardedJedis = null;
        long count = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            count = shardedJedis.hdel(domain, key);
        } catch (Exception ex) {
            logger.error("delHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return count;
    }

    /**
     * 判断key是否存在
     *
     * @param domain 域名
     * @param key    键值
     */
    public boolean existsHSet(String domain, String key) {
        ShardedJedis shardedJedis = null;
        boolean isExist = false;
        try {
            shardedJedis = shardedJedisPool.getResource();
            isExist = shardedJedis.hexists(domain, key);
        } catch (Exception ex) {
            logger.error("existsHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return isExist;
    }

    /**
     * 全局扫描hset
     *
     * @param match field匹配模式
     */
    public List<Map.Entry<String, String>> scanHSet(String domain, String match) {
        ShardedJedis shardedJedis = null;
        try {
            int cursor = 0;
            shardedJedis = shardedJedisPool.getResource();
            ScanParams scanParams = new ScanParams();
            scanParams.match(match);
            Jedis jedis = shardedJedis.getShard(domain);
            ScanResult<Map.Entry<String, String>> scanResult;
            List<Map.Entry<String, String>> list = new ArrayList<>();
            do {
                scanResult = jedis.hscan(domain, String.valueOf(cursor), scanParams);
                list.addAll(scanResult.getResult());
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);
            return list;
        } catch (Exception ex) {
            logger.error("scanHSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    /**
     * 返回 domain 指定的哈希集中所有字段的value值
     */
    public List<String> hvals(String domain) {
        ShardedJedis shardedJedis = null;
        List<String> retList = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            retList = shardedJedis.hvals(domain);
        } catch (Exception ex) {
            logger.error("hvals error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return retList;
    }

    /**
     * 返回对应key的所有hash字段的值
     *
     * @param key 指定的key
     */
    public Map<String, String> hgetall(String key) {
        ShardedJedis shardedJedis = null;
        Map<String, String> resultMap = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            resultMap = shardedJedis.hgetAll(key);
        } catch (Exception ex) {
            logger.error("hgetall error. {}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return resultMap;
    }

    /**
     * 返回对应key的字段值
     *
     * @param key   键
     * @param field 字段
     * @return 对应key的字段值
     */
    public String hget(String key, String field) {
        ShardedJedis shardedJedis = null;
        String result = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hget(key, field);
        } catch (Exception ex) {
            logger.error("hget(String, String) error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 返回对应key的字段值
     *
     * @param key   键
     * @param field 字段
     * @return 对应key的字段值
     */
    public byte[] hget(byte[] key, byte[] field) {
        ShardedJedis shardedJedis = null;
        byte[] result = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hget(key, field);
        } catch (Exception ex) {
            logger.error("hget(byte[], byte[]) error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 设置hash中对应字段的值
     *
     * @param key   键
     * @param field 字段
     * @return <p>-1:操作失败 <p>0:如果field原来在map里面已经存在 <p>1:如果field是一个新的字段
     */
    public long hdel(String key, String field) {
        ShardedJedis shardedJedis = null;
        long result = -1;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hdel(key, field);
        } catch (Exception ex) {
            logger.error("hdel error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 设置hash中对应字段的值
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     * @return <p>-1:操作失败 <p>0:如果field原来在map里面已经存在 <p>1:如果field是一个新的字段
     */
    public long hset(byte[] key, byte[] field, byte[] value, int expire) {
        ShardedJedis shardedJedis = null;
        long result = -1;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hset(key, field, value);
            if (expire > 0) {
                shardedJedis.expire(key, expire);
            }
        } catch (Exception ex) {
            logger.error("hget error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 设置hash中对应字段的值
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     * @return <p>-1:操作失败 <p>0:如果field原来在map里面已经存在 <p>1:如果field是一个新的字段
     */
    public long hset(String key, String field, String value) {
        return hset(key, field, value, -1);
    }

    /**
     * 设置hash中对应字段的值
     *
     * @param key   键
     * @param field 字段
     * @param value 值
     * @return <p>-1:操作失败 <p>0:如果field原来在map里面已经存在 <p>1:如果field是一个新的字段
     */
    public long hset(String key, String field, String value, int expire) {
        ShardedJedis shardedJedis = null;
        long result = -1;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hset(key, field, value);
            if (expire > 0) {
                shardedJedis.expire(key, expire);
            }
        } catch (Exception ex) {
            logger.error("hget(String key, String field, String value, int expire) error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    public String hmset(String key, Map<String, String> value) {
        return hmset(key, value, -1);
    }

    public String hmset(String key, Map<String, String> value, int expire) {
        ShardedJedis shardedJedis = null;
        String result = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hmset(key, value);
            if (expire > 0) {
                shardedJedis.expire(key, expire);
            }
        } catch (Exception ex) {
            logger.error("hmset error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> hmget(String key, String field) {
        ShardedJedis shardedJedis = null;
        List<String> result = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.hmget(key, field);
        } catch (Exception ex) {
            logger.error("hmget error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 返回 domain 指定的哈希集中所有字段的key值
     */
    public Set<String> hkeys(String domain) {
        ShardedJedis shardedJedis = null;
        Set<String> retList = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            retList = shardedJedis.hkeys(domain);
        } catch (Exception ex) {
            logger.error("hkeys error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return retList;
    }

    /**
     * 返回 domain 指定的哈希key值总数
     */
    public long lenHset(String domain) {
        ShardedJedis shardedJedis = null;
        long retList = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            retList = shardedJedis.hlen(domain);
        } catch (Exception ex) {
            logger.error("hkeys error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return retList;
    }

    /**
     * 设置排序集合
     */
    public boolean setSortedSet(String key, long score, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.zadd(key, score, value);
            return true;
        } catch (Exception ex) {
            logger.error("setSortedSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获得排序集合
     */
    public Set<String> getSoredSet(String key, long startScore, long endScore, boolean orderByDesc) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            if (orderByDesc) {
                return shardedJedis.zrevrangeByScore(key, endScore, startScore);
            } else {
                return shardedJedis.zrangeByScore(key, startScore, endScore);
            }
        } catch (Exception ex) {
            logger.error("getSoredSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptySet();
    }

    /**
     * 计算排序长度
     */
    public long countSoredSet(String key, long startScore, long endScore) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            Long count = shardedJedis.zcount(key, startScore, endScore);
            return count == null ? 0L : count;
        } catch (Exception ex) {
            logger.error("countSoredSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0L;
    }

    /**
     * 删除排序集合
     */
    public boolean delSortedSet(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            long count = shardedJedis.zrem(key, value);
            return count > 0;
        } catch (Exception ex) {
            logger.error("delSortedSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    /**
     * 获得排序集合
     */
    public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            if (orderByDesc) {
                return shardedJedis.zrevrange(key, startRange, endRange);
            } else {
                return shardedJedis.zrange(key, startRange, endRange);
            }
        } catch (Exception ex) {
            logger.error("getSoredSetByRange error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptySet();
    }

    /**
     * 获得排序打分
     */
    public Double getScore(String key, String member) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.zscore(key, member);
        } catch (Exception ex) {
            logger.error("getSoredSet error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    public boolean set(String key, String value, int second) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.setex(key, second, value);
            return true;
        } catch (Exception ex) {
            logger.error("setex error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    public boolean set(String key, String value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.set(key, value);
            return true;
        } catch (Exception ex) {
            logger.error("set error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    public boolean set(byte[] key, byte[] value, int expire) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.set(key, value);
            if (expire > 0) {
                shardedJedis.expire(key, expire);
            }
            return true;
        } catch (Exception ex) {
            logger.error("set error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    public byte[] get(byte[] key) {
        ShardedJedis shardedJedis = null;
        byte[] result = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            result = shardedJedis.get(key);
        } catch (Exception ex) {
            logger.error("get error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    public String get(String key) {
        return get(key, null);
    }

    public String get(String key, String defaultValue) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key) == null ? defaultValue : shardedJedis.get(key);
        } catch (Exception ex) {
            logger.error("get error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return defaultValue;
    }

    public boolean del(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.del(key);
            return true;
        } catch (Exception ex) {
            logger.error("del error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return false;
    }

    public long incr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.incr(key);
        } catch (Exception ex) {
            logger.error("incr error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    public long decr(String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.decr(key);
        } catch (Exception ex) {
            logger.error("incr error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return 0;
    }

    public List<String> blpop(int timeout, String queue) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.blpop(timeout, queue);
        } catch (Exception ex) {
            logger.error("blpop error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    public List<String> brpop(int timeout, String queue) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.brpop(timeout, queue);
        } catch (Exception ex) {
            logger.error("blpop error.", ex);
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return Collections.emptyList();
    }

    public boolean isConnected() {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
        } catch (JedisException je) {
            returnBrokenResource(shardedJedis);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
        return true;
    }

    private static class SingletonHolder {
        private static final JedisUtil JEDIS_UTIL = new JedisUtil();

        private SingletonHolder() {
        }
    }

}