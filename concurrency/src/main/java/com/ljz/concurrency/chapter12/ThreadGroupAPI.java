package com.ljz.concurrency.chapter12;

import com.ljz.concurrency.MyUtils;

import java.util.Arrays;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter12
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月13日 18:19
 * @version: V1.0
 */
public class ThreadGroupAPI {
    public static void main(String[] args) throws InterruptedException {
        MyUtils.getMethds(ThreadGroup.class);
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "t1") {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //break; //收到中断信号后终止循环
                    }
                }
            }
        };
        //tg1.setDaemon(true);

        t1.start();

        Thread.sleep(2_000);
        //可以手动destroy
        //System.out.println(tg1.isDestroyed());

        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break; //收到中断信号后终止循环
                    }
                }
            }
        };

        t2.start();

        System.out.println(tg1.activeCount()); //活跃线程数
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess();
        //tg1.destroy();


        System.out.println("==========================================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("==========================================");
        tg1.enumerate(ts1, true); //true 表示递归所有子线程组
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("==========================================");
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);
        //tg1.interrupt();        // 打断所有有线程

    }
}
