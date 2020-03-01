package com.ljz.concurrency.chapter2.band_ticket;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 模拟银行排队叫号
 * @author: LiuJiaZe
 * @date: 2019年12月05日 15:55
 * @version: V1.0
 */
public class Bank {
    public static void main(String[] args) {
        //        demonstration1();

        demonstration2();


    }

    private static void demonstration3() {
        //业务数据只创建了一次
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread ticketWindow1 = new Thread(ticketWindowRunnable, "1号柜台");
        Thread ticketWindow2 = new Thread(ticketWindowRunnable, "2号柜台");
        Thread ticketWindow3 = new Thread(ticketWindowRunnable, "3号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }


    /**
     * 使用runnable 分离业务数据 和 线程控制
     */
    private static void demonstration2() {

        //业务数据只创建了一次
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread ticketWindow1 = new Thread(ticketWindowRunnable, "1号柜台");
        Thread ticketWindow2 = new Thread(ticketWindowRunnable, "2号柜台");
        Thread ticketWindow3 = new Thread(ticketWindowRunnable, "3号柜台");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();

    }

    /**
     * 第一版本
     * 使用继承的方式
     */
    private static void demonstration1() {
        // 每次new TicketWindow 时 TicketWindow的成员变量都被创建一次
        TicketWindow ticketWindow1 = new TicketWindow("1号柜台");
        ticketWindow1.start();
        TicketWindow ticketWindow2 = new TicketWindow("2号柜台");
        ticketWindow2.start();
        TicketWindow ticketWindow3 = new TicketWindow("3号柜台");
        ticketWindow3.start();
    }
}
