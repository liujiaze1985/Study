package com.ljz.concurrent.chapter15;

import java.util.Random;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter15
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:16
 * @version: V1.0
 */
public class MyMessageHandler {
    private final static Random random = new Random(System.currentTimeMillis());

    public  void request(MyMessage message) {
        new Thread(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println( "The message will be handle by "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
