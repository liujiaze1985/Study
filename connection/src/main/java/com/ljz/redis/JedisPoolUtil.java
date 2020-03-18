package com.ljz.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Copyright © 2020年03月18日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.redis
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月18日 17:44
 * @version: V1.0
 */
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool = null;//被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。

    private JedisPoolUtil() {
    }

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    //poolConfig.setMaxActive(1000); //最大实例数
                    poolConfig.setMaxIdle(32); //最多有多少个空闲
                    //poolConfig.setMaxWait(100 * 1000); //等待时长
                    poolConfig.setTestOnBorrow(true); //是否检查 得到jedis实例的可用性

                    jedisPool = new JedisPool(poolConfig, "127.0.0.1");
                }
            }
        }
        return jedisPool;
    }

    /**
     * 释放
     * @param jedisPool
     * @param jedis
     */
    public static void release(JedisPool jedisPool, Jedis jedis) {
            //jedisPool.returnResourceObject(jedis); //放回池中
    }


    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            jedis.set("k18", "v183");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }

    }
}
