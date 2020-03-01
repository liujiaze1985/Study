package com.ljz.concurrent.util.chapter5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年02月10日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter5
 * @Description:  任务分片，各部分互相毛笔，等所有的部分都执行结束，才算结束
 * @author: liujiaze
 * @date: 2020年02月10日 11:36
 * @version: V1.0
 */
public class CyclicBarrierExample1 {
    public static void main(String[] args) {

        //demonstrate1();
        demonstrate2();
        //demonstrate3();
    }

    /**
     * 都结束以后回调
     */
    private static void demonstrate3() {
        //传入runnable的构造函数
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
            System.out.println(" all of finished. "); //都执行结束以后可通知或者回调
        });
        CyclicBarrierUtil.testThread(cyclicBarrier, "T1", 10l);
        CyclicBarrierUtil.testThread(cyclicBarrier, "T2", 5l);

        CyclicBarrierUtil.getCyclicBarrierInfo(cyclicBarrier,true);
    }



    /**
     * 主线程也知道其它部分是否结束
     */
    private static void demonstrate2() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3); //3 代表分为两部分
        CyclicBarrierUtil.testThread(cyclicBarrier, "T1", 10l);
        CyclicBarrierUtil.testThread(cyclicBarrier, "T2", 5l);

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(" all of finished. ");
        CyclicBarrierUtil.getCyclicBarrierInfo(cyclicBarrier,true);
    }


    /**
     * T2等待T1结束之后才完全结束
     */
    private static void demonstrate1() {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2); //2 代表分为两部分
        CyclicBarrierUtil.testThread(cyclicBarrier, "T1", 10l);
        CyclicBarrierUtil.testThread(cyclicBarrier, "T2", 5l);
        CyclicBarrierUtil.getCyclicBarrierInfo(cyclicBarrier,true);
    }



}
