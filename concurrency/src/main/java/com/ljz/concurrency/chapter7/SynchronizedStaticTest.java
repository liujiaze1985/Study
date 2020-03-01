package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter7
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月11日 17:50
 * @version: V1.0
 */
public class SynchronizedStaticTest {
    public static void main(String[] args) {
        //demonstration();
        demonstration1();

    }

    private static void demonstration1() {
        new Thread(()->{
            SynchronizedStatic.m1();
        },"T1").start();
        new Thread(()->{
            SynchronizedStatic.m3();
        },"T2").start();
    }

    /**
     * 调用静态同步代码
     * 锁相同，抢到锁的先执行
     */
    private static void demonstration() {
        new Thread(()->{
            SynchronizedStatic.m1();
        },"T1").start();
        new Thread(()->{
            SynchronizedStatic.m2();
        },"T2").start();
    }
}
