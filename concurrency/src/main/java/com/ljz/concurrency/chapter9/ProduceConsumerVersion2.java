package com.ljz.concurrency.chapter9;

import java.util.stream.Stream;

/**
 * Copyright © 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description: 线程间通讯 生产者，消费者
 * @author: LiuJiaZe
 * @date: 2019年12月12日 17:18
 * @version: V1.1
 */
public class ProduceConsumerVersion2 {

    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false; //判断是否已经生产

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { //没有生产，生产消息，唤醒消费者线程，将生产标识设置为已生产
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {  //已经生产，消费掉，唤醒生产者线程，将生产标识设置为未生产
                System.out.println("C->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //demonstration();
        demonstration1();
    }

    /**
     * 一个生产者和一个消费者，不会出现问题
     *
     */
    private static void demonstration() {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        Stream.of("P1")
              .forEach(n -> new Thread(() -> {
                           while (true) {
                               pc.produce();
                           }

                       }, n).start()

              );
        Stream.of("C1")
              .forEach(n -> new Thread(() -> {
                           while (true) {
                               pc.consume();
                           }

                       }, n).start());
    }

    /**
     * 多个生产者和多个消费者，会出现问题
     * 导致程序假死
     * 线程都进入wait状态，等待 notify
     */
    private static void demonstration1() {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        Stream.of("P1","P2")
              .forEach(n -> new Thread(() -> {
                           while (true) {
                               pc.produce();
                           }

                       }, n).start()

              );
        Stream.of("C1","C2")
              .forEach(n -> new Thread(() -> {
                           while (true) {
                               pc.consume();
                           }

                       }, n).start());
    }
}