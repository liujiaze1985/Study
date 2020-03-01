package com.ljz.concurrent.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter14
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 13:28
 * @version: V1.0
 */
public class JDKCountDown {
    private final static Random random = new Random(System.currentTimeMillis());
    final static CountDownLatch latch = new CountDownLatch(5); //latch（阀门）  5对应启动的线程数

    public static void main(String[] args) throws InterruptedException {

        System.out.println("准备多线程处理任务");
        //the first phase;
        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is working.");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }, String.valueOf(i)).start());

        latch.await();//有可能也会被中断
        //the second phase;
        System.out.println("多线程任务全部结束 ，准备第二阶段任务");
        System.out.println("...........................");
        System.out.println("FINISHED");


    }
}
