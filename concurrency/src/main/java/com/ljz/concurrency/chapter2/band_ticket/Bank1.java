package com.ljz.concurrency.chapter2.band_ticket;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 模拟银行排队叫号 使用JDK8新特性改造
 * @author: LiuJiaZe
 * @date: 2019年12月05日 15:55
 * @version: V1.0
 */
public class Bank1 {
    private final static int MAX = 50;

    public static void main(String[] args) {

        demonstration();


    }

    private static void demonstration() {
        //业务数据只创建了一次
        final Runnable ticketWindow = () -> {
            int index = 1;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + "的号码是：" + index++);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread ticketWindow1 = new Thread(ticketWindow, "1号柜台");
        Thread ticketWindow2 = new Thread(ticketWindow, "2号柜台");
        Thread ticketWindow3 = new Thread(ticketWindow, "3号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }

}
