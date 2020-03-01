package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Description: 将业务数据 和 线程 分离
 *               Runnable: 可执行的逻辑单元，和线程控制分离开来
 *               与多线程设计中的   策略设计模式接近
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:06
 * @version: V1.0
 */

public class TicketWindowRunnable implements Runnable {
    private final static int MAX = 500;
    private int index = 1;

    @Override
    public void run() {
        while (true) {
            if (index>MAX)
                break;
            try {
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "的号码是：" + index++);
        }
    }
}
