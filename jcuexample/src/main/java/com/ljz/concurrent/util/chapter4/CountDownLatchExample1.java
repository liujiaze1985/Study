package com.ljz.concurrent.util.chapter4;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Copyright © 2020年01月31日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter4
 * @Description: 简单使用场景
 * @author: liujiaze
 * @date: 2020年01月31日 9:38
 * @version: V1.0
 */
public class CountDownLatchExample1 {

    private final static Random random = new Random(System.currentTimeMillis());
    /**
     * 使用CountDownLatch
     */
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(10);
    /**
     * 线程池
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        //demonstrate();
        demonstrate1();

    }

    /**
     * 计数减为0，关闭线程池
     * @throws InterruptedException
     */
    private static void demonstrate1() throws InterruptedException {
        //1 获取数据
        int[] data = query();
        //2 处理数据
        //另一个线程
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable1(data, i, COUNT_DOWN_LATCH));
        }

        //3 输出结果
        // 主线程等待执行
        COUNT_DOWN_LATCH.await();
        System.out.println("all of work finish done.");
        executor.shutdown(); //结束 （异步）
    }

    /**
     *使用线程池循环处理数据，最后等待指定时间后释放资源
     */
    private static void demonstrate() throws InterruptedException {
        //1 获取数据
        int[] data = query();
        //2 处理数据
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i));
        }

        //3 输出结果
        executor.shutdown(); //结束 （异步：所有的任务执行完成后再结束 ，空闲的打断一下，不空闲不打断）
        executor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("all of work finish done.");
    }


    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    /**
     * 接口实现
     */
    static class SimpleRunnable implements Runnable {

        private final int[] data;
        private final int index;

        public SimpleRunnable(int[] data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }

            System.out.println(Thread.currentThread().getName() + " finished.");
        }
    }

    /**
     * 传入了CountDownLatch的接口实现
     */
    static class SimpleRunnable1 implements Runnable {

        private final int[] data;
        private final int index;

        private final CountDownLatch latch;

        public SimpleRunnable1(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            //计数减少
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished.");
        }
    }
}
