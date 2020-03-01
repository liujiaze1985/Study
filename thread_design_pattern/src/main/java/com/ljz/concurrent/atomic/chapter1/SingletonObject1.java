package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。恶汉式
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject1 {
    /**
     * 恶汉式 不能懒加载
     * can't lazy load.
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {
        //empty
    }

    public static SingletonObject1 getInstance() {
        return instance;
    }

    //被使用时就加载入内存，如果不使用一直存在于内存中，占用资源
}
