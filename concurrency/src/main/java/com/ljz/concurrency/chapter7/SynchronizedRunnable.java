package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter7
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月11日 17:11
 * @version: V1.0
 */
public class SynchronizedRunnable implements Runnable {
    private final static int MAX = 500;
    private int index = 1;

    @Override
    public synchronized void run() { //synchronized 的锁是 this  this 代表SynchronizedRunnable的一个实例
        while (true) {
            if (index > MAX) {
                break;
            }
            try {
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "的号码是：" + index++);
        }
        //2
    }
}
