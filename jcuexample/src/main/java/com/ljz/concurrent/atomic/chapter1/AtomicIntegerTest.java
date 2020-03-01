package com.ljz.concurrent.atomic.chapter1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月23日 21:01
 * @version: V1.0
 */
public class AtomicIntegerTest {

    /**
     * volatile
     * 内存可见
     * 有序性
     * 不能保证原子性
     */
    private static volatile int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());

    public static void main(String[] args) throws InterruptedException {
        //demonstrate();
        //demonstrate1();


    }

    /**
     * 使用 AtomicInteger
     *      具备可见性，有序性，原子性
     * @throws InterruptedException
     */
    private static void demonstrate1() throws InterruptedException {
        final AtomicInteger value = new AtomicInteger(0); //默认值为0
        Arrays.asList("T1", "T2", "T3").forEach(i -> new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int v = value.getAndIncrement(); //拿值 再自增
                set.add(v);
                System.out.println(Thread.currentThread().getName() + ":" + v);
                x++;
            }
        }, i).start());
        Thread.sleep(10_000L);
        System.out.println(set.size());
    }

    /**
     * 验证volatile 不能保证原子性
     */
    private static void demonstrate() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;
                    /**
                     * value +=1;的四个阶段
                     * 读取：（1） get value from main memory to local memory
                     * 计算： (2) add 1
                     * 赋值： (3) assign the value to x
                     * 刷内存 (4) flush to main memory
                     *
                     */
                    x++;
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;

                    x++;
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while (x < 500) {
                    set.add(value);
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + ":" + tmp);
                    value += 1;
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(set.size());
    }
}
