package com.ljz.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description: AtomicIntegerFieldUpdater 测试
 * @author: liujiaze
 * @date: 2019年12月27日 16:54
 * @version: V1.0
 */
public class AIFUTest {

    private volatile int i;
    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class, "i");

    public static void main(String[] args) {
        AIFUTest test = new AIFUTest();
        test.update(10);
        System.out.println(test.get());
    }

    /**
     * 实现自增
     * @param newValue
     */
    public void update(int newValue) {
        updater.compareAndSet(this, i, newValue);
    }

    public int get() {
        return i;
    }
}
