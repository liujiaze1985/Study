package com.ljz.concurrent.atomic.chapter1.try_lock;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1.try_lock
 * @Description: 模拟两个线程抢锁，没有抢到锁的，抛出异常，然后去干别的
 * @author: liujiaze
 * @date: 2019年12月26日 19:05
 * @version: V1.0
 */
public class AtomicIntegerDetailsTest2 {
    private final static CompareAndSetLock lock = new CompareAndSetLock();


    public static void main(String[] args) {
        //demonstrate();
        demonstrate1();
    }

    private static void demonstrate1() {
        for (int i = 0; i < 5; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        doSomeThing2();
                    } catch (GetLockException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }
    }

    private static void demonstrate() {
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    doSomeThing();

                }
            }.start();

        }
    }

    private static void doSomeThing() {
        synchronized (AtomicIntegerDetailsTest2.class) {
            System.out.println(Thread.currentThread().getName() + " get the lock ");
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void doSomeThing2() throws GetLockException, InterruptedException {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " get the lock ");
            Thread.sleep(100_000);
        } finally {
            lock.unLock();
        }

    }
}
