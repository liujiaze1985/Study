package com.ljz.concurrent.atomic.chapter2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter2
 * @Description: 使用场景
 * @author: liujiaze
 * @date: 2019年12月26日 19:53
 * @version: V1.0
 */
public class AtomicBooleanFlag {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                while (flag.get()) {
                    try {
                        Thread.sleep(1_000);
                        System.out.println(" I am working.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("I am finished.");

            }
        }.start();

        Thread.sleep(5_000);

        flag.set(false);

    }
}
