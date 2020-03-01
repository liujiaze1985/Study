package com.ljz.concurrent.chapter3;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter3
 * @Description: volatile 不能保证原子性，可以保证有序性，可见性
 * @author: LiuJiaZe
 * @date: 2019年12月14日 18:16
 * @version: V1.0
 */
public class VolatileTest {
    private static final int MAX_LIMIT = 5;

    private volatile static int INIT_VALUE = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d] \n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("update The value  to [%d] \n", ++localValue);
                INIT_VALUE = localValue;

                try {
                    Thread.sleep(500); //休眠以保证不会写太快，导致读线程不能感知INIT_VALUE每一次变化
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }

}
/**
 *如果不加volatile 写线程一直写，但读线程不能发现INIT_VALUE的变化
 *  volatile 会使线程去主内存里去拿
 *
 */