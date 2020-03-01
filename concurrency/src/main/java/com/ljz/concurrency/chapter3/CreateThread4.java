package com.ljz.concurrency.chapter3;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter3
 * @Description: 改变stackSize
 * @author: LiuJiaZe
 * @date: 2019年12月10日 12:30
 * @version: V1.0
 */
public class CreateThread4 {
    private static int counter = 0;
    //main 的 stackSize 无法改变
    public static void main(String[] args) {
        //demonstration();
        demonstration1();

    }

    /**
     * 修改 stackSize
     */
    private static void demonstration1() {
        Thread t1 = new Thread(null,() -> {
            try {
                add(0);
            } catch (Error e) {
                e.printStackTrace();
                System.out.println(counter);
            }

        },"Thread-changeStackSize",1<<24);
        t1.start();
    }

    /**
     * 使用默认 stackSize
     */
    private static void demonstration() {
        Thread t1 = new Thread(() -> {
            try {
                add(0);
            } catch (Error e) {
                e.printStackTrace();
                System.out.println(counter);
            }

        });
        t1.start();
    }

    private static void add(int i) {
        counter++;
        add(i++);

    }
}
