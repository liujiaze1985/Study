package com.ljz.concurrency.chapter3;

import java.util.Arrays;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 */
public class CreateThread2 {
    public static void main(String[] args) {

        //        demonstration();
        //        demonstration1();
        //        demonstration2();
        //        demonstration3();
        //        demonstration4();
        //        demonstration5();
        //        demonstration6();
        //        demonstration7();
    }

    /**
     * Thread(ThreadGroup group, Runnable target, String name,long stackSize)
     */
    private static void demonstration7() {

    }


    /**
     * Thread(ThreadGroup group, Runnable target, String name)
     */
    private static void demonstration6() {

    }

    /**
     * Thread(Runnable target, String name)
     */
    private static void demonstration5() {

    }

    /**
     * Thread(ThreadGroup group, String name)
     */
    private static void demonstration4() {
        Thread t = new Thread(() -> {
            System.out.println(" Thread(Runnable target) ");

        }, "RunnableThread");
        t.start();
        System.out.println("Thread(ThreadGroup group, String name) =>" + t.getName());

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
        //        System.out.println(t.getThreadGroup());
        //        System.out.println(Thread.currentThread().getName());

        //        System.out.println("Thread()=>"+t.getName());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        //        System.out.println(threadGroup.getName());
        //查看活动线程数
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        //        for (Thread temp : threads) {
        //            System.out.println(temp);
        //
        //        }
        Arrays.asList(threads).forEach(System.out::println);
    }


    /**
     *  init(ThreadGroup g, Runnable target, String name,long stackSize)
     */
}
