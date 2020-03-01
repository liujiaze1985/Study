package com.ljz.concurrency.chapter2.band_ticket;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 将业务数据 和 线程 分离
 *               Runnable: 可执行的逻辑单元，和线程控制分离开来
 *               与多线程设计中的   策略设计模式接近
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:06
 * @version: V1.0
 */
public class TicketWindowRunnable implements Runnable {
    private final static int MAX = 50;
    private int index = 1;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + "的号码是：" + index++);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
