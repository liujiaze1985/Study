package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。懒加载
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject2 {
    /**
     * 饿汉式 不能懒加载
     */
    private static  SingletonObject2 instance ;

    private SingletonObject2() {
        //empty
    }

    public static SingletonObject2 getInstance() {
        if (null == instance)
            instance = new SingletonObject2();
        return SingletonObject2.instance;
    }


    /**
     * 在多线程中
     *   if (null == instance)
     *   该判断 可能会new 多个实例
     *
     *
     *
     *
     */
}
