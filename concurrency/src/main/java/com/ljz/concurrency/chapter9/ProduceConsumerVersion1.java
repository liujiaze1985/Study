package com.ljz.concurrency.chapter9;

/**
 * Copyright © 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description:  使用wait 和notify 进行线程间通讯 生产者，消费者
 *
 * @author: LiuJiaZe
 * @date: 2019年12月12日 17:18
 * @version: V1.0 没有通讯，生产者不断生产，但消费者，只消费了最新的而且一直重复消费
 */
public class ProduceConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    /**
     * 生产数据
     */
    private void produce() {
        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    /**
     * 消费数据
     */
    private void consume() {
        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {

        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

        //生产数据
        new Thread(() -> {
            while (true) {
                pc.produce();
            }

        }, "P").start();

        //消费数据
        new Thread(() -> {
            while (true) {
                pc.consume();
            }

        }, "C").start();


    }
}
