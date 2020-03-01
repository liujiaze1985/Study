package com.ljz.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description: 使用场景中出现的错误
 * @author: liujiaze
 * @date: 2019年12月27日 16:19
 * @version: V1.0
 */
public class AtomicIntegerFieldUpdaterFailedTest {


    @Test
    public void testPublic() {
        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);

    }

    /**
     * 不能访问私有变量
     * Can't access the private field of object
     */
    @Test(expected = RuntimeException.class)
    public void testPrivate() {
        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "j");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);

    }

    /**
     * ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testTargetObjectIsNull() {
        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        //TestMe me = new TestMe();
        updater.compareAndSet(null, 0, 1);

    }

    /**
     * 字段名非法
     * NoSuchFieldException
     */
    @Test(expected = RuntimeException.class)
    public void testFieldNameInvalid() {
        AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i1");
        updater.compareAndSet(null, 0, 1);

    }

    /**
     * 正确的
     */
    @Test
    public void testFieldTypeInvalid() {
        AtomicReferenceFieldUpdater<TestMe, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,
                Integer.class,
                "k");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1);

    }

    /**
     * 类型错误
     * ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testFieldTypeInvalid1() {
        AtomicReferenceFieldUpdater<TestMe, Long> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,
                Long.class,
                "k");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1L);

    }

    /**
     * 字段不是volatile修饰
     * java.lang.IllegalArgumentException: Must be volatile type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFieldIsNotVolatile() {
        AtomicReferenceFieldUpdater<TestMe, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,
                Integer.class,
                "l");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1);

    }

    @Test
    public void testFieldIsProtected() {
        AtomicReferenceFieldUpdater<TestMe, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe.class,
                Integer.class,
                "m");
        TestMe me = new TestMe();
        updater.compareAndSet(me, null, 1);

    }
}
