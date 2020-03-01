package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。双检
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject4 {
    /**
     * 单例
     * 懒加载
     * 提升性能
     */
    private static SingletonObject4 instance;

    private SingletonObject4() {
        //empty
    }

    public static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }


        return SingletonObject4.instance;
    }


    /**
     * 在多线程中
     *     可能会引起空指针异常
     *     原因：
     *          编译重排序，（编译期优化，运行期优化）
     *          before
     *     假设SingletonObject4有很多引用，一个线程还没有初始化完成，另一个线程判断 if (null == instance) 不为null ，
     *     直接引用，就导致该类中的某些引用空指针异常
     *
     *
     *
     */
}
