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
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() +"->"+ i));
        });
        t1.start();
        t1.join(); //join 默认参数为0，表示直等到t1运行结束 才执行主线程的代码
        Thread t2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() +"->"+ i));
        });
        t2.start();
        t2.join();
        Optional.of("All of tasks finish done.").ifPresent(System.out::println);

        //直到T1,T2执行完才执行main 线程的方法
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName()  +"->"+ i));
    }
}
