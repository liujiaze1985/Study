package com.ljz.concurrent.chapter13;

import java.util.LinkedList;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter13
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 21:43
 * @version: V1.0
 */
public class MessageQueue {
    private final LinkedList<MyMessage> queue;

    private final static int DEFAULT_MAX_LIMIT=100;


    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);

    }
    public MessageQueue(final int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    /**
     * 存放消息
     * @param message
     */
    public void put(final MyMessage message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >limit) {
                queue.wait();

            }
            queue.addLast(message);
            queue.notifyAll();
        }


    }

    /**
     * 获取消息
     */
    public MyMessage take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();

            }
            MyMessage message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }

    }

    /**
     * 不发生更改，不用加锁
     * @return
     */
    public int getMaxLimit(){
        return this.limit;
    }

    /**
     * 获取消息数量
     * 变化，需要加锁
     * @return
     */
    public int getMessageSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
