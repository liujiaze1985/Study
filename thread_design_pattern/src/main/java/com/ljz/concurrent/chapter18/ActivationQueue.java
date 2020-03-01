package com.ljz.concurrent.chapter18;

import com.ljz.concurrent.chapter18.request.MethodRequest;

import java.util.LinkedList;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 17:14
 * @version: V1.0
 */
public class ActivationQueue {
    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;
    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        this.methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest request) {
        try {
            while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.methodQueue.addLast(request);
        this.notifyAll();

    }

    public synchronized MethodRequest take() {
        try {
            while (methodQueue.isEmpty()) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MethodRequest result = this.methodQueue.removeFirst();
        this.notifyAll();

        return result;
    }
}
