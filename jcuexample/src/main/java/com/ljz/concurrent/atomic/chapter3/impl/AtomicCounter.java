package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.atomic.chapter3.service.Counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月28日 21:00
 * @version: V1.0
 */
public class AtomicCounter implements Counter {
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }
}
