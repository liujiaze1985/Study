package com.ljz.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月26日 20:03
 * @version: V1.0
 */
public class AtomicLongTest {


    @Test
    public void start() {
        testCreate();
        //testGet();
        //testSet();
        //testLazySet();
        //testGetAndAdd();
        //testAccumulateAndGet();
        //testAddAndGet();
        //testCompareAndSet();
        //testDecrementAndGet();
        //testGetAndAccumulate();
        //testGetAndDecrement();
        //testGetAndIncrement();
        //testGetAndSet();
        //testGetAndUpdate();
        //testIncrementAndGet();
        //testUpdateAndGet();
        //testWeakCompareAndSet();

    }

    private void testCreate() {
        AtomicLong l = new AtomicLong(100L);
        assertEquals(100L, l.get());
    }

    private void testWeakCompareAndSet() {


    }

    private void testUpdateAndGet() {

    }

    private void testIncrementAndGet() {

    }

    private void testGetAndUpdate() {

    }

    private void testGetAndSet() {

    }

    private void testGetAndIncrement() {

    }

    private void testGetAndDecrement() {

    }

    private void testGetAndAccumulate() {

    }

    private void testDecrementAndGet() {

    }

    private void testCompareAndSet() {

    }

    private void testAddAndGet() {

    }

    private void testAccumulateAndGet() {

    }

    private void testGetAndAdd() {

    }

    private void testLazySet() {

    }

    private void testSet() {

    }

    private void testGet() {

    }
}
