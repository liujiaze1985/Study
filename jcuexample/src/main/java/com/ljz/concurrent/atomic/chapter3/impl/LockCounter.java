package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.atomic.chapter3.service.Counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description: 加锁的
 * @author: liujiaze
 * @date: 2019年12月28日 20:53
 * @version: V1.0
 */
public class LockCounter implements Counter {

    private final Lock lock = new ReentrantLock(); //ReentrantLock 默认不公平
    private long counter = 0;

    @Override
    public void increment() {
        try {
            lock.lock();
            counter++;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public long getCounter() {
        return counter;
    }
}
