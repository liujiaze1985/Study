package com.ljz.concurrent.util.chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年02月10日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter5
 * @Description: 1.reset
 *               2.isBroken
 *               3.与CountDownLatch的区别
 * @author: liujiaze
 * @date: 2020年02月10日 11:36
 * @version: V1.0
 */
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws InterruptedException {

        //demonstrate();
        demonstrate1();
    }

    private static void demonstrate() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(" all thread is finished.");
        });
        CyclicBarrierUtil.testThread(cyclicBarrier, "T1", 5l);
        CyclicBarrierUtil.testThread(cyclicBarrier, "T2", 0l);   //T2直接进入await
        TimeUnit.MILLISECONDS.sleep(100);
        cyclicBarrier.reset(); //await() 时可以被 reset
    }

    /**
     * reset <==>  init <==> finished
     * @throws InterruptedException
     */
    private static void demonstrate1() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(" all thread is finished.");
        });
        CyclicBarrierUtil.testThread(cyclicBarrier, "T1", 5l);
        CyclicBarrierUtil.testThread(cyclicBarrier, "T2", 0l);   //T2直接进入await
        TimeUnit.SECONDS.sleep(4);
        CyclicBarrierUtil.getCyclicBarrierInfo(cyclicBarrier,false);
        TimeUnit.SECONDS.sleep(2);
        cyclicBarrier.reset(); //await() 时可以被 reset
        CyclicBarrierUtil.getCyclicBarrierInfo(cyclicBarrier,false);
    }
}
