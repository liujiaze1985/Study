package com.ljz.concurrent.atomic.chapter3;

import com.ljz.concurrent.MyUtils;
import com.ljz.concurrent.atomic.chapter3.impl.AtomicCounter;
import com.ljz.concurrent.atomic.chapter3.impl.CASCounter;
import com.ljz.concurrent.atomic.chapter3.impl.CounterRunnable;
import com.ljz.concurrent.atomic.chapter3.impl.LockCounter;
import com.ljz.concurrent.atomic.chapter3.impl.StupidCounter;
import com.ljz.concurrent.atomic.chapter3.impl.SyncCounter;
import com.ljz.concurrent.atomic.chapter3.service.Counter;

import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月27日 17:12
 * @version: V1.0
 */
public class UnsafeTest {


    public static void main(String[] args) throws Exception {
        //demonstrate();
        //demonstrate1();

        /**
         * Counter result: 9967718
         * Time passed in ms:383
         */
        //demonstrate2();

        /**
         * Counter result: 10000000
         * Time passed in ms:583
         */
        //demonstrate3();

        /**
         * Counter result: 10000000
         * Time passed in ms:416
         */
        //demonstrate4();

        /**
         * Counter result: 10000000
         * Time passed in ms:434
         */
        //demonstrate5();

        /**
         * Counter result: 10000000
         * Time passed in ms:854
         */
        demonstrate6();
    }


    private static void demonstrate6() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * 竞争条件
         */
        Counter counter = new CASCounter(); //容易出问题
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //提交1000个runnable
            service.submit(new CounterRunnable(counter, 10000));

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);//等一个小时
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());//一共加了多少次
        System.out.println("Time passed in ms:" + (end - start));
    }

    private static void demonstrate5() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * 竞争条件
         */
        Counter counter = new AtomicCounter(); //容易出问题
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //提交1000个runnable
            service.submit(new CounterRunnable(counter, 10000));

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);//等一个小时
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());//一共加了多少次
        System.out.println("Time passed in ms:" + (end - start));
    }

    private static void demonstrate4() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * 竞争条件
         */
        Counter counter = new LockCounter(); //容易出问题
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //提交1000个runnable
            service.submit(new CounterRunnable(counter, 10000));

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);//等一个小时
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());//一共加了多少次
        System.out.println("Time passed in ms:" + (end - start));
    }

    private static void demonstrate3() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * 竞争条件
         */
        Counter counter = new SyncCounter(); //容易出问题
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //提交1000个runnable
            service.submit(new CounterRunnable(counter, 10000));

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);//等一个小时
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());//一共加了多少次
        System.out.println("Time passed in ms:" + (end - start));
    }


    private static void demonstrate2() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * 竞争条件
         */
        Counter counter = new StupidCounter(); //容易出问题
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            //提交1000个runnable
            service.submit(new CounterRunnable(counter, 10000));

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);//等一个小时
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());//一共加了多少次
        System.out.println("Time passed in ms:" + (end - start));
    }

    private static void demonstrate1() {
        Unsafe unsafe = MyUtils.getUnsafe();

        System.out.println(unsafe);
    }

    private static void demonstrate() {
        Unsafe unsafe = Unsafe.getUnsafe();
        System.out.println(unsafe);
        /**
         * Exception in thread "main" java.lang.SecurityException: Unsafe
         */

    }


}
