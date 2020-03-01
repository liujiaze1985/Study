package com.ljz.concurrency.thread_api;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description: Thread 的join方法详解，结合一个典型案例（也可使用concurrency包中的方法解决，不必使用join）
 * @author: LiuJiaZe
 * @date: 2019年12月11日 13:15
 * @version: V1.0
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("T1 is running.");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1 is done.");
        });
        t1.start();
        t1.join(100); //100毫秒没有完成就执行main的下列代码
        //join(100，10) 100毫秒 ，10 纳秒

        Optional.of("All of tasks finish done.").ifPresent(System.out::println);
        //直到T1,T2执行完才执行main 线程的方法
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName()  +"->"+ i));
    }
}
