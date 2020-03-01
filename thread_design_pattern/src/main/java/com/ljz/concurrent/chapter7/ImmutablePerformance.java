package com.ljz.concurrent.chapter7;

import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 18:30
 * @version: V1.0
 */
public class ImmutablePerformance {
    public static void main(String[] args) {

        long startTimestamp = System.currentTimeMillis();
        //syncUseTime();
        //noSyncUseTime();
        //multSyncUseTime();
        multNoSyncUseTime();
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time  " + (endTimestamp - startTimestamp));
    }

    /**
     * 单线程
     * Elapsed time  1283
     */
    private static void syncUseTime() {
        SyncObj syncObj = new SyncObj();
        syncObj.setName("Alex");
        for (int i = 0; i < 100000; i++) {
            System.out.println(syncObj.toString());
        }
    }

    /**
     * 单线程
     * Elapsed time  1155
     */
    private static void noSyncUseTime() {
        ImmutableObj obj = new ImmutableObj("Alex");
        for (int i = 0; i < 100000; i++) {
            System.out.println(obj.toString());
        }
    }

    /**
     * 多线程
     * Elapsed time  2425
     */
    private static void multSyncUseTime() {
        SyncObj syncObj = new SyncObj();
        syncObj.setName("Alex");
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + syncObj.toString());
                    }
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 多线程
     * Elapsed time  2450
     */
    private static void multNoSyncUseTime() {
        ImmutableObj obj = new ImmutableObj("Alex");
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + obj.toString());
                    }
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}

/**
 * 不可变的类
 */
final class ImmutableObj {
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

/**
 * 可变对象
 */
class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }
}