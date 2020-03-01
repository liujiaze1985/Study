package com.ljz.concurrent.util.chapter4;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright © 2020年01月31日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter4
 * @Description: CountDownLatch.md 用法
 * @author: liujiaze
 * @date: 2020年01月31日 21:05
 * @version: V1.0
 */
public class CountDownLatchExample2 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        demonstrate1(latch);
        Thread.currentThread().join();
    }

    /**
     * T1等待T2结束后再执行
     * @param latch
     */
    private static void demonstrate(final CountDownLatch latch) {
        touch1(latch);
    }

    /**
     * 一个或多个线程 await
     * @param latch
     */
    private static void demonstrate1(final CountDownLatch latch) {
        touch1(latch);

        new Thread(() -> {
            try {
                latch.await();
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread().getName() + " release.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T3").start();


    }

    private static void touch1(CountDownLatch latch) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Do some initial working.");
            try {
                Thread.sleep(1000L);
                latch.await();
                System.out.println(Thread.currentThread().getName() + " Do other working...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();


        touch(latch);
    }

    private static void touch(CountDownLatch latch) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " async prepare for some data.");
            try {
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread().getName() + " data prepare for done...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();//计数
            }
        }, "T2").start();
    }

}
