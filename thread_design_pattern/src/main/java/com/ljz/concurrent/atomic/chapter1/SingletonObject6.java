package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。Holder方式    推荐使用
 *
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject6 {

    private SingletonObject6() {
        //empty
    }

    /**
     *
     */
    private static class InstanceHolder {

        private final static  SingletonObject6 instance = new SingletonObject6();
    }


    public static SingletonObject6 getInstance() {
        return InstanceHolder.instance;
    }


    /**
     * 在多线程中
     *  即可保证懒加载，又可以保证线程安全，效率特别高
     *  static 主动加载，保证 只会加载一次
     *  类加载：
     *      装载：读class文件
     *      链接：赋值，初始化
     *
     */
}
