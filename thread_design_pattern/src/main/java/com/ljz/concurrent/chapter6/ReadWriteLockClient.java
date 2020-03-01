package com.ljz.concurrent.chapter6;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter6
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 17:57
 * @version: V1.0
 */
public class ReadWriteLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new ReadWorker(sharedData).start();
        new WriteWorker(sharedData, "qwertyuiopasdfg").start();
        new WriteWorker(sharedData, "qwertyuiopasdfg").start();
    }

}

/**
 * 以上是一个经典的设计模式
 * ReadWriteLock design pattern
 * Reader-Writer design pattern
 */