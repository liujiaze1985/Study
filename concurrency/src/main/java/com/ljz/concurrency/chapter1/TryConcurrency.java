package com.ljz.concurrency.chapter1;


/**
 * Copyright © 2019年12月03日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 创建并启动线程
 * @author: LiuJiaZe
 * @date: 2019年12月03日 18:45
 * @version: V1.0
 */
public class TryConcurrency {
    public static void main(String[] args) {

        new Thread("Read-Thread"){
            @Override
            public void run() {
//                readFromDataBase();
                for (int i = 0; i < 100; i++) {
                    println("Task 1 =>" +i );
                    mySleep();
                }
            }
        }.start();
        new Thread("Write-Thread"){
            @Override
            public void run() {
//                writeDataToFile();
                for (int i = 0; i < 100; i++) {
                    println("Task 2 =>" +i );
                    mySleep();
                }
            }
        }.start();


    }

    private static void mySleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void readFromDataBase() {
        try {
            System.out.println("Begin read data from db.");
            Thread.sleep(1000 * 10L);
            System.out.println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }


    private static void writeDataToFile() {
        try {
            System.out.println("Begin write data to file.");
            Thread.sleep(1000 * 10L);
            System.out.println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
