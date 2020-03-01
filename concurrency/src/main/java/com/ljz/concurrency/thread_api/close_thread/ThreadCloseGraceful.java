package com.ljz.concurrency.thread_api.close_thread;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description: 结束线程的生命周期
 * @author: LiuJiaZe
 * @date: 2019年12月11日 15:07
 * @version: V1.0
 */
public class ThreadCloseGraceful {


    public static void main(String[] args) {
        demonstration();
    }


    /**
     * 通过开关和flag方式结束线程的生命周期
     */
    private static void demonstration() {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }

    private static class Worker extends Thread {
        private volatile boolean start = true ; //是否定义volatile区别不大
        @Override
        public void run() {
            while (start) {
                //
            }
        }

        public void shutdown() {
            this.start = false;
        }
    }
}
