package com.ljz.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月27日 16:10
 * @version: V1.0
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        //不能直接new
        AtomicIntegerFieldUpdater<TestMe> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        //多个线程访问TestMe 对i 进行原子操作
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    //加到多少就结束
                    final int MAX = 20;
                    for (int j = 0; j < MAX; j++) {
                        int v = fieldUpdater.getAndIncrement(me);
                        System.out.println(Thread.currentThread().getName() + " => " + v);
                    }
                }
            }.start();

        }
    }


}
