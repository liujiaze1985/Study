package com.ljz.concurrent.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter13
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 22:11
 * @version: V1.0
 */
public class ConsumerThread extends Thread {
    private final MessageQueue messageQueue;

    private final Random random = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue messageQueue, int seq) {
        super("Consumer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MyMessage message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take message " + message.getData());

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
