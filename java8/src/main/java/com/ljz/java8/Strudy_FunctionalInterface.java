package com.ljz.java8;

/**
 * Copyright ? 2019??11??19?? LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019??11??19?? 20:07
 * @version: V1.0
 */
public class Strudy_FunctionalInterface {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }
        );
        thread.start();
        /**
         * lambda ???????????
         * ??? ?????????
         * ?????? ??????????
         */
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
        /**
         * ?????????????
         */
        Thread.currentThread().join();

    }

}
