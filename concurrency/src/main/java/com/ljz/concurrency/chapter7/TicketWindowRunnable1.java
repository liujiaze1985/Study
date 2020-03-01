package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Description:  使用同步代码块
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:06
 * @version: V1.0
 */

public class TicketWindowRunnable1 implements Runnable {
    private final static int MAX = 500;
    private int index = 1;
    private final Object MONITOR = new Object();
    @Override
    public void run() {
        //1 synchronized 代码块中，变为了单线程
        synchronized (MONITOR) {
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
        }
        //2
    }
}
