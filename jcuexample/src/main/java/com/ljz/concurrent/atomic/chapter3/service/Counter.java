package com.ljz.concurrent.atomic.chapter3.service;

/**
 * Copyright © 2019年12月28日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月28日 20:36
 * @version: V1.0
 */
public interface Counter {
    void increment();

    long getCounter();
}
