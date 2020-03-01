package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter7
 * @Description: 证明this 锁的存在
 * @author: LiuJiaZe
 * @date: 2019年12月11日 17:38
 * @version: V1.0
 */
public class SynchronizedThisLock {
    public static void main(String[] args) {
        //demonstration();
        //demonstration1();
        demonstration2();

    }

    /**
     * 调用 m4, m5
     * 锁不同，几乎同时执行
     */
    private static void demonstration2() {
        ThisLock thisLock = new ThisLock();
        new Thread(() -> {
            thisLock.m4();
        }, "T1").start();
        new Thread(() -> {
            thisLock.m5();
        }, "T2").start();
    }

    /**
     * 调用 同步方法 m1和非同步方法m3
     * 几乎同时执行
     */
    private static void demonstration1() {
        ThisLock thisLock = new ThisLock();
        new Thread(() -> {
            thisLock.m1();
        }, "T1").start();
        new Thread(() -> {
            thisLock.m3();
        }, "T2").start();
    }

    /**
     * 调用 同步方法 m1,m2
     * synchronized 的锁为同一个
     * 先抢到锁的先执行
     */
    private static void demonstration() {
        ThisLock thisLock = new ThisLock();
        new Thread(() -> {
            thisLock.m1();
        }, "T1").start();
        new Thread(() -> {
            thisLock.m2();
        }, "T2").start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();

    public synchronized void m1() {
        try {
            Thread.sleep(10_000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        try {
            Thread.sleep(10_000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m3() {
        try {
            Thread.sleep(10_000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void m4() {
        synchronized (LOCK) {
            try {
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void m5() {
        synchronized (this) {
            try {
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}