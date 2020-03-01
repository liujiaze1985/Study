package com.ljz.concurrency.chapter11;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter11
 * @Description: 捕获线程里的异常
 * @author: LiuJiaZe
 * @date: 2019年12月13日 14:47
 * @version: V1.0
 */
public class ThreadException {

    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5_000L); // Thread里无法抛出异常，只可以捕获
                int result = A / B; //运行时错误，线程结束
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //捕获线程里的异常
        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);                  //java.lang.ArithmeticException: / by zero
            System.out.println(thread);             //Thread[Thread-0,5,main]
        });

        t.start();
    }

}
