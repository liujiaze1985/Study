package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 模拟银行排队叫号 数据同步
 * @author: LiuJiaZe
 * @date: 2019年12月05日 15:55
 * @version: V1.0
 */
public class BankVersion2 {

    public static void main(String[] args) {

        //demonstration();
        demonstration1();


    }

    /**
     * 使用同步代码块
     */
    private static void demonstration1() {
        //业务数据只创建了一次
        final TicketWindowRunnable1 ticketWindow = new TicketWindowRunnable1();

        Thread ticketWindow1 = new Thread(ticketWindow, "1号柜台");
        Thread ticketWindow2 = new Thread(ticketWindow, "2号柜台");
        Thread ticketWindow3 = new Thread(ticketWindow, "3号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }

    /**
     * 未使用同步代码块，出现超卖现象
     */
    private static void demonstration() {
        //业务数据只创建了一次
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();

        Thread ticketWindow1 = new Thread(ticketWindow, "1号柜台");
        Thread ticketWindow2 = new Thread(ticketWindow, "2号柜台");
        Thread ticketWindow3 = new Thread(ticketWindow, "3号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }

}
