package com.ljz.concurrency.thread_api.close_thread;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description: 通过打断的方式，结束线程的生命周期
 * @author: LiuJiaZe
 * @date: 2019年12月11日 15:07
 * @version: V1.0
 */
public class ThreadCloseGraceful2 {


    public static void main(String[] args) {
        //demonstration();
        demonstration1();
    }

    private static void demonstration1() {
        Worker1 worker1 = new Worker1();
        worker1.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker1.interrupt();
    }


    /**
     * 通过打断的方式
     */
    private static void demonstration() {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }

    private static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                    break;//return 后边不执行， break 后边还做逻辑
                }

            }

            // TODO
        }
    }
    private static class Worker1 extends Thread {
        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) { //存在问题，如果没有机会判断是否被打断，参考ThreadCloseGraceful3
                    break;//return 后边不执行， break 后边还做逻辑
                }
            }

            // TODO
        }
    }
}
