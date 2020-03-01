package com.ljz.concurrency.thread_api;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description: 给当前线程发送中断信号，被中断线程需要捕获异常
 * @author: LiuJiaZe
 * @date: 2019年12月11日 14:00
 * @version: V1.0
 */
public class ThreadInterrupt {

    private static Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

        //demonstration();
        //demonstration1();
        //demonstration2();
        demonstration3();
    }

    /**
     * 使用join 方式打断
     * @throws InterruptedException
     */
    private static void demonstration3() throws InterruptedException {
        {
            Thread t = new Thread(() -> {
                //被中断需要捕获异常
                while (true) {
                    //wait 是Object的方法 ,使用wait 时必须要使用monitor ，monitor必须用synchronized包装

                }

            });
            Thread main = Thread.currentThread();
            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //t.interrupt(); //打断t 线程
                main.interrupt(); //只有用main线程才可以打断 t
                System.out.println("interrupt t ");
            });
            t2.start();

            t.start();
            try {
                t.join(); //join的是main线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //--------------------block状态 ，无法在join之后打断-----------------------
        }

    }

    /**
     *  public static boolean interrupted()
     *  public boolean isInterrupted()
     *  以上两个方法的使用场景
     * @throws InterruptedException
     */
    private static void demonstration2() throws InterruptedException {
        Thread t = new Thread(() -> {
            //被中断需要捕获异常
            while (true) {
                //wait 是Object的方法 ,使用wait 时必须要使用monitor ，monitor必须用synchronized包装
                synchronized (MONITOR) {
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号.");
                        System.out.println(Thread.interrupted()); //是否被中断
                        e.printStackTrace();
                    }
                }

            }

        });

        t.start();
        Thread.sleep(100); //等待t真正启动
        System.out.println(t.isInterrupted()); //是否中断 false
        t.interrupt();
        System.out.println(t.isInterrupted());//是否中断  true
    }

    /**
     * 使用wait方式打断
     * @throws InterruptedException
     */
    private static void demonstration1() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                //被中断需要捕获异常
                while (true) {
                    //wait 是Object的方法 ,使用wait 时必须要使用monitor ，monitor必须用synchronized包装
                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到打断信号.");
                            System.out.println(isInterrupted()); //是否被中断
                            e.printStackTrace();
                        }
                    }

                }

            }

        };

        t.start();
        Thread.sleep(100); //等待t真正启动
        System.out.println(t.isInterrupted()); //是否中断 false
        t.interrupt();
        System.out.println(t.isInterrupted());//是否中断  true
    }

    /**
     * 使用sleep方式打断
     * @throws InterruptedException
     */
    private static void demonstration() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                //被中断需要捕获异常
                while (true) {
                    //System.out.println(">>" + this.isInterrupted()); //true
                    try {
                        Thread.sleep(10); //使用sleep的方式打断
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号.");
                        e.printStackTrace();
                    }
                }

            }

        };

        t.start();
        Thread.sleep(100); //等待t真正启动
        System.out.println(t.isInterrupted()); //是否中断 false
        t.interrupt();
        System.out.println(t.isInterrupted());//是否中断  true
    }
}
