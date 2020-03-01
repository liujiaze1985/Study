package com.ljz.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月26日 21:07
 * @version: V1.0
 */
public class AtomicStampedReferenceTest {
    /**
     * 解决ABA的问题
     * @throws Exception
     */
    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                boolean success = atomicRef.compareAndSet(100, 101, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                System.out.println("T1 100->101 " + success);
                success = atomicRef.compareAndSet(101, 100, atomicRef.getStamp(), atomicRef.getStamp() + 1);
                System.out.println("T1 101->100 " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1");
        Thread t2 = new Thread(() -> {
            try {
                int stamp = atomicRef.getStamp();
                System.out.println("T2 Before sleep:stamp= " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean success = atomicRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("T2 100->101 " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
