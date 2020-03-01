package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。优化 双检
 *                volatile
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject5 {
    /**
     * 单例
     * 懒加载
     * 提升性能
     * 增加 volatile
     */
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        //empty
    }

    public static SingletonObject5 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject5.class) {
                if (null == instance) {
                    instance = new SingletonObject5();
                }
            }
        }


        return SingletonObject5.instance;
    }


    /**
     * 在多线程中
     *      volatile 不能保证原子性，
     *      但可以保证内存可见性（多线程在内存中看到都是同一份），
     *      有序性（不被jvm优化），
     *      保证初始化全部完成
     *
     *
     */
}
