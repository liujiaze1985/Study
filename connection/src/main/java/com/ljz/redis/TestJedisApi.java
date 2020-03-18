package com.ljz.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Copyright © 2020年03月18日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.redis
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月18日 16:21
 * @version: V1.0
 */
public class TestJedisApi {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());

        //set
        jedis.set("K1", "V1");
        jedis.set("K2", "V2");
        jedis.set("K3", "V3");
        //get
        System.out.println(jedis.get("K1"));
        System.out.println(jedis.get("K2"));
        System.out.println(jedis.get("K3"));


        //keys
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());
        for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        System.out.println("jedis.exists===>" + jedis.exists("K2"));
        System.out.println(jedis.ttl("K1"));

        //五大数据类型
        //String
        System.out.println(jedis.get("K1"));
        jedis.set("k4", "v4_redis");
        System.out.println("===========================================");
        //批量写入
        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3", "str4", "v4");
        System.out.println(jedis.mget("str1", "str2", "str3", "str4"));

        System.out.println("===========================================");
        //list
        jedis.lpush("mylist", "v1", "v2", "v3", "v4", "v5");
        List<String> list = jedis.lrange("mylist", 0, -1);
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("===========================================");
        //set
        jedis.sadd("orders", "jd001");
        jedis.sadd("orders", "jd002");
        jedis.sadd("orders", "jd003");
        Set<String> set1 = jedis.smembers("orders");
        for (Iterator iterator = set1.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        jedis.srem("orders", "jd002"); //del
        System.out.println(jedis.smembers("orders").size());

        System.out.println("===========================================");
        //hash
        jedis.hset("hash1", "username", "lisi");
        System.out.println(jedis.hget("hash1", "username"));
        Map<String, String> map = new HashMap<>();
        map.put("phone", "15010333231");
        map.put("address", "beijing");
        map.put("email", "logging_in@163.com");
        jedis.hmset("hash2", map);
        List<String> hmget = jedis.hmget("hash2", "phone", "address");
        for (String s : hmget) {
            System.out.println(s);
        }

        System.out.println("===========================================");
        //zset
        jedis.zadd("zset01", 60d, "v1");
        jedis.zadd("zset01", 70d, "v2");
        jedis.zadd("zset01", 80d, "v3");
        jedis.zadd("zset01", 90d, "v4");
        Set<String> zset01s = jedis.zrange("zset01", 0, -1);
        for (Iterator iterator = zset01s.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }

        //事务

    }
}
