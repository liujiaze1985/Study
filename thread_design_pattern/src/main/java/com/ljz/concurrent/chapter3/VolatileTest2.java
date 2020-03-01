package com.ljz.concurrent.chapter3;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter3
 * @Description: volatile 不能保证原子性，可以保证有序性，可见性
 *
 * @author: LiuJiaZe
 * @date: 2019年12月14日 18:16
 * @version: V1.1
 */
public class VolatileTest2 {
    private static final int MAX_LIMIT = 50;

    private volatile static int INIT_VALUE = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("ADDER-1 -> "+ (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                while (INIT_VALUE < MAX_LIMIT) {
                    System.out.println("ADDER-2 -> "+ (++INIT_VALUE));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "ADDER-2").start();
    }

}
/**
 * 验证是否从主内存中获取数据
 * java所做的优化：
 *      如果线程中都是读操作，不会更新主内存，那么会从cpu cache中直接获取，
 *      如果线程中有写操作，会更新主内存，并从主内存中拿数据
 *
 */