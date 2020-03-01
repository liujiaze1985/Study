package com.ljz.concurrent.util.chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年02月10日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter5
 * @Description:
 * @author: liujiaze
 * @date: 2020年02月10日 13:14
 * @version: V1.0
 */
public class CyclicBarrierUtil {
    public static void getCyclicBarrierInfo(CyclicBarrier cyclicBarrier, boolean flag) {
        do {
            System.out.println("getNumberWaiting: " + cyclicBarrier.getNumberWaiting());
            System.out.println("getParties: " + cyclicBarrier.getParties());
            System.out.println("isBroken: " + cyclicBarrier.isBroken());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (flag);


    }

    public static void testThread(CyclicBarrier cyclicBarrier, String name, long seconds) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println(Thread.currentThread().getName() + " finished ");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " The other thread finished too .");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, name).start();
    }
}
