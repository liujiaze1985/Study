package com.ljz.concurrent.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter13
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 21:59
 * @version: V1.0
 */
public class ProducerThread extends Thread {
    private final MessageQueue messageQueue;

    //计数器
    private final static AtomicInteger counter = new AtomicInteger(0);


    private final Random random = new Random(System.currentTimeMillis());

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("PRODUCER-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                MyMessage message = new MyMessage("Message-" + counter.getAndDecrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }

    }


}
