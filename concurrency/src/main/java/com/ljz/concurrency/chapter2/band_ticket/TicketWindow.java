package com.ljz.concurrency.chapter2.band_ticket;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 取票窗口
 *               Thread 的子类 无法将业务逻辑数据与线程控制分离
 * @author: LiuJiaZe
 * @date: 2019年12月05日 15:55
 * @version: V1.0
 */
public class TicketWindow extends Thread{

    private final  String name ;
    private final static int MAX = 50;
    private static int index = 1; //static 多份线程操作同一份数据

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台："+name+"当的前的号码是："+index++);
        }
    }

}
