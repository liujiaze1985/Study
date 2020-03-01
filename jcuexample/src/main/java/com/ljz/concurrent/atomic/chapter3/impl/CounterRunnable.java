package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.atomic.chapter3.service.Counter;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月28日 20:37
 * @version: V1.0
 */
public class CounterRunnable implements Runnable {
    private final Counter counter;
    private final int num;

    public CounterRunnable(Counter counter, int num) {
        this.counter = counter;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            counter.increment();
        }
    }
}
