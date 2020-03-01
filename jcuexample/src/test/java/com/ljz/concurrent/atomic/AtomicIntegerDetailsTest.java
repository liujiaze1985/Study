package com.ljz.concurrent.atomic;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月23日 22:00
 * @version: V1.0
 */
public class AtomicIntegerDetailsTest {


    public void testCreate() {
        System.out.println("testCreate ================================= ");
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());
    }

    public void testSet() {
        System.out.println("testSet ================================= ");
        AtomicInteger i = new AtomicInteger();
        i.set(20);
        System.out.println(i.get());
        i.lazySet(10);

        System.out.println(i.get());
    }

    public void testGetAndAdd() {
        System.out.println("testGetAndAdd ================================= ");
        AtomicInteger i = new AtomicInteger(10);
        int result = i.getAndAdd(10);
        System.out.println(result);
        System.out.println(i.get());

    }

    public void testGetAndSet() {
        System.out.println("testGetAndAdd ================================= ");
        AtomicInteger i = new AtomicInteger(10);
        int result = i.getAndSet(10);
        System.out.println(result);
        System.out.println(i.get());

    }


    public void testGetAndSet1() {
        System.out.println("testGetAndAdd ================================= ");
        AtomicInteger i = new AtomicInteger(10);

        new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    int v = i.getAndSet(10);
                    System.out.println(v);
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 10; j++) {
                    int v = i.getAndSet(10);
                    System.out.println(v);
                }
            }
        }.start();

    }

    /**
     * 对比然后设置
     * 允许最快失败
     */
    public void testCompareAndSet() {
        System.out.println("testCompareAndSet ================================= ");
        AtomicInteger i = new AtomicInteger(10);
        boolean flag = i.compareAndSet(12, 20);//期望值 ，更新后的值
        System.out.println(i.get());  //10
        System.out.println(flag);  //false

    }


    @Test
    public void testStart() {
        //testCreate();
        //testSet();
        //testGetAndAdd();
        //testGetAndSet();
        //testGetAndSet1();
        testCompareAndSet();


        try {
            Thread.sleep(15_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get()
    //set(int)
    //lazySet(int)

    //类型强制转换
    //intValue()
    //longValue()
    //floatValue()
    //doubleValue()

    //accumulateAndGet(int,java.util.function.IntBinaryOperator)
    //addAndGet(int) 先加，再get
    //getAndAdd(int)
    //getAndAccumulate(int,java.util.function.IntBinaryOperator)
    //getAndDecrement() 减1
    //getAndIncrement() 加1
    //getAndUpdate(java.util.function.IntUnaryOperator)
    //getAndSet(int)
    //incrementAndGet() 先加1后get
    //decrementAndGet() 先减1后get
    //updateAndGet(java.util.function.IntUnaryOperator)


    //compareAndSet(int,int)
    //weakCompareAndSet(int,int)


}
