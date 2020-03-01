package com.ljz.concurrency.chapter12;

import java.util.Arrays;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter12
 * @Description: ThreadGroup jdk1.5 之前没有线程池，用ThreadGroup来管理Thread
 * @author: LiuJiaZe
 * @date: 2019年12月13日 15:36
 * @version: V1.0
 */
public class ThreadGroupCreate {
    public static void main(String[] args) {
        //use the name 默认拿当前线程的thread group 做为父 thread group
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                try {
                    System.out.println(getThreadGroup().getName());
                    System.out.println(getThreadGroup().getParent());//当前线程组的父线程组
                    System.out.println(getThreadGroup().getParent().activeCount()); //活动线程数
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");
        System.out.println(tg2.getName());
        System.out.println(tg2.getParent());

        ThreadGroup tg3 = new ThreadGroup("TG3");
        Thread t3 = new Thread(tg3, "T3") {
            @Override
            public void run() {
                System.out.println("在tg3中访问 tg1 ==>" + tg1.getName());
                Thread[] threads = new Thread[tg1.activeCount()]; //tg1 中有多少个活跃线程
                tg1.enumerate(threads);
                Arrays.asList(threads).stream().forEach(System.out::println);
            }
        };

        t3.start();
        //use the parent and group name

        //System.out.println(Thread.currentThread().getName()); //当前线程名称
        //System.out.println(Thread.currentThread().getThreadGroup().getName()); //当前线程组名称
    }
}
