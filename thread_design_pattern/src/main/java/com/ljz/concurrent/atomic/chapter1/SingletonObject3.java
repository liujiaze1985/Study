package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。懒加载 加锁
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject3 {
    /**
     * 加锁懒加载
     */
    private static SingletonObject3 instance ;

    private SingletonObject3() {
        //empty
    }

    public synchronized static SingletonObject3 getInstance() {
        if (null == instance)
            instance = new SingletonObject3();
        return SingletonObject3.instance;
    }


    /**
     * 在多线程中
     *    每次获取实例都要抢锁，影响性能
     *
     *
     *
     */
}
