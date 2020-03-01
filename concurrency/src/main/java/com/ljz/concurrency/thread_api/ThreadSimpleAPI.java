package com.ljz.concurrency.thread_api;

import com.ljz.concurrency.MyUtils;

import java.util.Optional;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter4
 * @Description: Thread API
 * @author: LiuJiaZe
 * @date: 2019年12月11日 12:14
 * @version: V1.0
 */
public class ThreadSimpleAPI {
    public static void main(String[] args) {
        MyUtils.getMethds(Thread.class);
        Thread t = new Thread(()->
        {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t.start();
        Optional.of(t.getName()).ifPresent(System.out::println); //线程名
        Optional.of(t.getId()).ifPresent(System.out::println);//线程id
        Optional.of(t.getPriority()).ifPresent(System.out::println); //优先级
        //Optional.of(t.get).ifPresent(System.out::println);
    }


}
