package com.ljz.concurrent.atomic.chapter1;

import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月14日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: 介绍4种Singleton方式在多线程情况下
 *                1。枚举方式
 *                   枚举构造函数私有，只会被装载一次
 *                   枚举类是final的
 *
 * @author: LiuJiaZe
 * @date: 2019年12月14日 16:47
 * @version: V1.0
 */
public class SingletonObject7 {

    private SingletonObject7() {
        //empty
    }

    private enum Singleton {
        INSTANCE;

        private final SingletonObject7 instance;

        Singleton() {
            instance = new SingletonObject7();
        }

        public SingletonObject7 getInstance() {
            return instance;
        }
    }

    public static SingletonObject7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * 在多线程中
     *
     */

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100)
                 .forEach(i -> new Thread(String.valueOf(i)) {
                     @Override
                     public void run() {
                         System.out.println(SingletonObject7.getInstance());
                     }
                 }.start());
    }
}
