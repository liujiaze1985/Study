package com.ljz.concurrency.chapter4;


/**
 * Copyright ? 2019年12月10日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter4
 * @Description: main线程生命周期结束 ，守护线程也跟着结束
 *               守护线程使用场景，网络连接，长连接设置为守护线程发送心跳包，当主线程结束时，该线程也结束
 *               setDaemon 在start之前调用，否则会报java.lang.IllegalThreadStateException
 * @author: LiuJiaZe
 * @date: 2019年12月10日 14:19
 * @version: V1.0
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+" running.");
                Thread.sleep(100_000);
                System.out.println(Thread.currentThread().getName() + " done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.setDaemon(true);
        //runnable  ->running| ->dead| ->blocked
        t.start();

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());//main 线程结束
    }
}
/**
 * A<---------------------------------->B
 *  ->daemonThread(health check)
 * */