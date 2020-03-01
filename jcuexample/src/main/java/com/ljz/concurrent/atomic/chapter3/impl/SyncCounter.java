package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.atomic.chapter3.service.Counter;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description: 同步的
 * @author: liujiaze
 * @date: 2019年12月28日 20:50
 * @version: V1.0
 */
public class SyncCounter implements Counter {
    private long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;

    }

    @Override
    public long getCounter() {
        return counter;
    }
}
