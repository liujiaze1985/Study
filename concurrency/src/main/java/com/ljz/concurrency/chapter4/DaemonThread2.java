package com.ljz.concurrency.chapter4;

/**
 * Copyright ? 2019年12月10日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter4
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月10日 14:19
 * @version: V1.0
 */
public class DaemonThread2 {
    public static void main(String[] args) {
        //demonstration();
        demonstration1();
    }

    private static void demonstration1() {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                while (true) {
                    System.out.println("Do some thing for health check.");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            //innerThread.setDaemon(true);
            innerThread.start();
            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
        //t.setDaemon(true);
        t.start();
        try {
            Thread.sleep(50_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 innerThread 设置为守护线程，当主线程结束
     */
    private static void demonstration() {
        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                while (true) {
                    System.out.println("Do some thing for health check.");
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            innerThread.setDaemon(true);
            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            innerThread.start();
        });
        //t.setDaemon(true);
        t.start();
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(Thread.currentThread().getName());//执行后，main线程结束
    }
}
