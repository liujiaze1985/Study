package com.ljz.redis;

import redis.clients.jedis.Jedis;

/**
 * Copyright © 2020年03月18日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.redis
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月18日 17:36
 * @version: V1.0
 */
public class RedisMasterSlave {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("127.0.0.1",6379);
        Jedis jedis_S = new Jedis("127.0.0.1",6380);

        jedis_S.slaveof("127.0.0.1",6379); //设置主从关系

        jedis_M.set("k6","v6"); //主写
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis_S.get("k6")); //从读
    }
}
