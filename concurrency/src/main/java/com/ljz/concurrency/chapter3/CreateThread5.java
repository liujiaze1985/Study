package com.ljz.concurrency.chapter3;

/**
 * Copyright © 2019年12月10日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter3
 * @Description: Thread stackSize 详细讲解
 *               该程序会导致系统崩溃
 * @author: LiuJiaZe
 * @date: 2019年12月10日 13:08
 * @version: V1.0
 */
public class CreateThread5 {

    private static int counter = 0;

    public static void main(String[] args) {
        //demonstration();
    }

    private static void demonstration() {
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                counter++;
                new Thread(() -> {
                    while (true) {

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();

            }
        } catch (Error e) {
            e.printStackTrace();
        }
        System.out.println("Total create thread nums => " + counter);
    }
}
