package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.atomic.chapter3.service.Counter;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description: 愚蠢的
 * @author: liujiaze
 * @date: 2019年12月28日 20:45
 * @version: V1.0
 */
public class StupidCounter implements Counter {
    private long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
