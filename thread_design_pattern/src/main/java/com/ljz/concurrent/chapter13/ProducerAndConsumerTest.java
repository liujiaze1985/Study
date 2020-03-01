package com.ljz.concurrent.chapter13;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter13
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 22:13
 * @version: V1.0
 */
public class ProducerAndConsumerTest {

    public static void main(String[] args) {
        final MessageQueue messageQueue = new MessageQueue();
        new ProducerThread(messageQueue,1).start();
        new ProducerThread(messageQueue,2).start();
        new ConsumerThread(messageQueue,3).start();
        new ConsumerThread(messageQueue,1).start();
        new ConsumerThread(messageQueue,2).start();
    }
}
