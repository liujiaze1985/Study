package com.ljz.concurrent.atomic.chapter3.impl;

import com.ljz.concurrent.MyUtils;
import com.ljz.concurrent.atomic.chapter3.service.Counter;

import sun.misc.Unsafe;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3.impl
 * @Description: 借助CAS算法
 * @author: liujiaze
 * @date: 2019年12月28日 21:05
 * @version: V1.0
 */
public class CASCounter implements Counter {

    private volatile long counter = 0;
    private Unsafe unsafe;

    //偏移量
    private long offset;

    public CASCounter() throws Exception {
        this.unsafe = MyUtils.getUnsafe();
        this.offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }


    @Override
    public void increment() {
        long current = counter;
        while (!unsafe.compareAndSwapLong(this, offset, current, current + 1)) {
            current = counter;
        }

    }

    @Override
    public long getCounter() {
        return counter;
    }


}
