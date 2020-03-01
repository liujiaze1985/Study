package com.ljz.concurrent.chapter16;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter16
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:43
 * @version: V1.0
 */
public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        Thread.sleep(10_000L);

        counterIncrement.close();
    }

}
