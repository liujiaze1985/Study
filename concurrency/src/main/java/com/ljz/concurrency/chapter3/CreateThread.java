package com.ljz.concurrency.chapter3;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter3
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月05日 17:03
 * @version: V1.0
 */
public class CreateThread {
    public static void main(String[] args) {

        demonstration();
        demonstration1();
        demonstration2();
        demonstration3();
        demonstration4();
        demonstration5();
        demonstration6();
        demonstration7();
    }

    /**
     * Thread(ThreadGroup group, Runnable target, String name,long stackSize)
     */
    private static void demonstration7() {

    }


    /**
     *  Thread(ThreadGroup group, Runnable target, String name)
     */
    private static void demonstration6() {

    }

    /**
     * Thread(Runnable target, String name)
     */
    private static void demonstration5() {
        Thread t = new Thread(() -> {
            System.out.println(" Thread(Runnable target) ");

        }, "RunnableThread");
        t.start();
        System.out.println("Thread(Runnable target, String name) =>" + t.getName());

    }

    /**
     * Thread(ThreadGroup group, String name)
     */
    private static void demonstration4() {

    }

    /**
     * Thread(ThreadGroup group, Runnable target)
     */
    private static void demonstration3() {

    }

    /**
     * Thread(String name)
     */
    private static void demonstration2() {
        Thread t = new Thread("MyName");
        t.start();
        System.out.println("Thread(String name) =>" + t.getName());
    }

    /**
     * Thread(Runnable target)
     */
    private static void demonstration1() {
        Thread t = new Thread(() -> {
            System.out.println("=============");
        });
        t.start();
        System.out.println("Thread(Runnable target)=>" + t.getName());
    }

    /**
     * Thread()
     */
    private static void demonstration() {
        Thread t = new Thread();
        t.start();
        //复写Thread 的run方法
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("==================");
            }
        };
        t1.start();
        System.out.println("Thread()=>" + t.getName());
        System.out.println("Thread() 复写run方法 =>" + t1.getName());
    }


    /**
     *  init(ThreadGroup g, Runnable target, String name,long stackSize)
     */
}
