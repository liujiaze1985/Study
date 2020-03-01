package com.ljz.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.junit.Assert.assertEquals;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月27日 15:41
 * @version: V1.0
 */
public class AtomicIntegerArrayTest {
    @Test
    public void start() {

        testCreateAtomicIntegerArray1();
        testCreateAtomicIntegerArray();
        testGet();
        testSet();
        testGetAndSet();

    }


    private void testGetAndSet() {
        AtomicIntegerArray array = new AtomicIntegerArray(10); //长度为10
        array.set(1, 5);
        assertEquals(5, array.get(1));
        array.getAndSet(1, 10);
        assertEquals(10, array.get(1)); //
    }

    private void testSet() {
        AtomicIntegerArray array = new AtomicIntegerArray(10); //长度为10
        array.set(1, 10);
        assertEquals(10, array.get(1)); //
    }

    private void testGet() {
        AtomicIntegerArray array = new AtomicIntegerArray(10); //长度为10

        assertEquals(0, array.get(1)); //
    }

    private void testCreateAtomicIntegerArray() {
        AtomicIntegerArray array = new AtomicIntegerArray(10); //长度为10

        assertEquals(10, array.length()); //长度是否为10
    }

    private void testCreateAtomicIntegerArray1() {
        int[] ints = new int[10];
        AtomicIntegerArray array = new AtomicIntegerArray(ints); //长度为10

        assertEquals(10, array.length()); //长度是否为10
    }
}
