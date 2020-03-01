package com.ljz.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * Copyright © 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description:  sleep 和 wait 的区别
 * @author: LiuJiaZe
 * @date: 2019年12月12日 17:18
 * @version: V1.2
 */
public class DifferenceOfWaitAndSleep {

    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name ->
                new Thread(name) {
                    @Override
                    public void run() {
                        m2();
                    }
                }.start()
        );
    }

    /**
     * 不会释放锁
     * 只有一个线程sleep 完 执行完代码后，其它线程才可以拿锁
     */
    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void m2() {
        synchronized (LOCK) { //使用wait时不加  synchronized (LOCK) 会抛异常
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void m3() {
        try {
            System.out.println("The Thread " + Thread.currentThread().getName() + " enter.");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}