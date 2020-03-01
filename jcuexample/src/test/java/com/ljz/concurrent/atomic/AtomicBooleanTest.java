package com.ljz.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月26日 19:36
 * @version: V1.0
 */
public class AtomicBooleanTest {


    /**
     * 创建
     */
    private void testCreateWithOutArguments() {
        System.out.println("testCreateWithOutArguments ===================================");
        AtomicBoolean b = new AtomicBoolean();
        assertFalse(b.get());

    }

    /**
     * 创建2
     */
    private void testCreateWithOutArguments1() {
        System.out.println("testCreateWithOutArguments1 ===================================");
        AtomicBoolean b = new AtomicBoolean(true);
        assertTrue(b.get());

    }

    /**
     * 先拿出来再设置值
     */
    private void testGetAndSet() {
        System.out.println("testGetAndSet ===================================");
        AtomicBoolean b = new AtomicBoolean(true);
        boolean result = b.getAndSet(false);
        assertTrue(result);
        assertFalse(b.get());

    }

    /**
     * 对比后设置
     */
    private void testCompareAndSet() {
        System.out.println("testCompareAndSet ===================================");
        AtomicBoolean b = new AtomicBoolean(true);
        boolean result = b.compareAndSet(true, false);
        assertTrue(result);
        assertFalse(b.get());

    }

    /**
     * 对比后设置
     */
    private void testCompareAndSetFalse() {
        System.out.println("testCompareAndSetFalse ===================================");
        AtomicBoolean b = new AtomicBoolean(true);
        boolean result = b.compareAndSet(false, true);
        assertFalse(result);
        assertTrue(b.get());

    }


    @Test
    public void start() {
        testCreateWithOutArguments();
        testCreateWithOutArguments1();
        testGetAndSet();
        testCompareAndSet();
        testCompareAndSetFalse();
    }
}
