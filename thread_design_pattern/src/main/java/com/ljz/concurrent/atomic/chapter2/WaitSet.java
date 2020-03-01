package com.ljz.concurrent.atomic.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter2
 * @Description: 多线程的休息室WaitSet详细介绍 与知识点总结
 * @author: LiuJiaZe
 * @date: 2019年12月14日 17:33
 * @version: V1.0
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        //demonstration();
        new Thread(){
            @Override
            public void run() {
                try {
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Thread.sleep(1_000);

        synchronized (LOCK) {
            LOCK.notify();
        }


    }



    private static void demonstration() throws InterruptedException {
        IntStream.rangeClosed(1, 10)
                 .forEach(i -> new Thread(String.valueOf(i)) {
                     @Override
                     public void run() {
                         synchronized (LOCK) {
                             try {
                                 Optional.of(Thread.currentThread()
                                                   .getName() + " -  will come to wait set ")
                                         .ifPresent(System.out::println);
                                 LOCK.wait();
                                 Optional.of(Thread.currentThread()
                                                   .getName() + " -  will live to wait set ")
                                         .ifPresent(System.out::println);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }
                     }
                 }.start());

        Thread.sleep(3_000);


        IntStream.rangeClosed(1, 10)
                 .forEach(i -> {
                     synchronized (LOCK) {
                         LOCK.notify();
                         try {
                             Thread.sleep(1_000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 });
    }

    //wait 后是否重新拿锁，但是执行代码是有记录的，
    private static void work() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("Begin...");
            try {
                System.out.println("Thread will coming.");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out.");
        }



    }


}
/**
 * This method causes the current thread (call it <var>T</var>) to
 * place itself in the wait set for this object and then to relinquish
 * any and all synchronization claims on this object.
 * 释放锁，进入休息状态
 * 存在哪里？
 * 怎么唤醒？
 * 唤醒后怎么出来？
 *
 * 1。所有有对象都会有一个wait set ，用来存放调用了该对象wait方法之后中进入block状态线程
 * 2。线程被notify之后 ，不一定立即得到执行
 * 3. 线程从wait set中被唤醒，顺序不一定是FIFO
 * 4。线程被唤醒后重新抢锁，但是执行代码是有记录的，有地址恢复这个概念
 */
