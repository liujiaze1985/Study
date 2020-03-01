package com.ljz.concurrent.util.chapter4;

import com.ljz.concurrent.MyUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年01月31日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter4
 * @Description: API
 * @author: liujiaze
 * @date: 2020年01月31日 21:26
 * @version: V1.0
 */
public class CountDownLatchExample3 {
    public static void main(String[] args) throws InterruptedException {

        MyUtils.getMethds(CountDownLatch.class);
        demonstrate3();

    }

    /**
     * count不小于0，为0时await() 无效
     * @throws InterruptedException
     */
    private static void demonstrate() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(0);
        latch.await();
        System.out.println("==================");
    }

    /**
     * await() 等待 countDown 将count变为0后进行其它操作
     * @throws InterruptedException
     */
    private static void demonstrate1() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);


        new Thread(() -> {
            try {
                Thread.sleep(10_000L);
                System.out.println(Thread.currentThread().getName() + " release.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }, "T3").start();

        latch.await();
        System.out.println("==================");
    }

    /**
     * await() 等待 中断后进行其它操作
     * @throws InterruptedException
     */
    private static void demonstrate2() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(10_000L);
                System.out.println(Thread.currentThread().getName() + " release.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();//主线程中断
        }, "T3").start();

        latch.await();
        System.out.println("==================");
    }

    /**
     * 等待指定时间
     *  latch.await(1000l,TimeUnit.MILLISECONDS);
     * @throws InterruptedException
     */
    private static void demonstrate3() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(10_000L);
                System.out.println(Thread.currentThread().getName() + " release.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }, "T3").start();

        latch.await(1000l, TimeUnit.MILLISECONDS);//1000l 毫秒
        System.out.println("==================");
    }
}
