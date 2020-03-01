package com.ljz.concurrency.chapter10;

/**
 * Copyright ? 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter10
 * @Description: Synchronized Problem
 * @author: LiuJiaZe
 * @date: 2019年12月12日 21:19
 * @version: V1.0
 */
public class SynchronizedProblem {
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {

                SynchronizedProblem.run();
            }
        }.start();

            Thread.sleep(1_000);
        Thread t2=new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t2.start();
        Thread.sleep(1_000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());

    }

    private  synchronized static void run() {
        System.out.println(Thread.currentThread());
        while (true) {

        }
    }
}
