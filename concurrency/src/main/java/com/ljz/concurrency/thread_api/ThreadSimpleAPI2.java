package com.ljz.concurrency.thread_api;

import com.ljz.concurrency.MyUtils;

import java.util.Optional;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter4
 * @Description: Thread API 优先级
 * @author: LiuJiaZe
 * @date: 2019年12月11日 12:14
 * @version: V1.0
 */
public class ThreadSimpleAPI2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->
        {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index" +i ).ifPresent(System.out::println);

            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        Thread t2 = new Thread(()->
        {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index" +i ).ifPresent(System.out::println);

            }
        });
        t2.setPriority(Thread.NORM_PRIORITY);
        t2.start();
        Thread t3 = new Thread(()->
        {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-Index" +i ).ifPresent(System.out::println);

            }
        });
        t3.setPriority(Thread.MIN_PRIORITY);
        t3.start();
    }


}
