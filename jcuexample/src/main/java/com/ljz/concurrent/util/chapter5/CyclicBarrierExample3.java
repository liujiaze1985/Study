package com.ljz.concurrent.util.chapter5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年02月10日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter5
 * @Description: 使用CountDownLatch模拟 CyclicBarrier回调功能
 * @author: liujiaze
 * @date: 2020年02月10日 13:37
 * @version: V1.0
 */
public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                //执行runnable  (模拟CyclicBarrier 的回调构造)
                this.runnable.run();
            }
        }
    }


    public static void main(String[] args) {
        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, () -> {
            System.out.println("all of the work finished done");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished.");
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished.");
        }, "T2").start();
    }
}
