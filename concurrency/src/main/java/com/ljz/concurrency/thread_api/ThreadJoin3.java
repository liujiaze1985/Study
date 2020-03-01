package com.ljz.concurrency.thread_api;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description: Thread 数据采集案例
 * @author: LiuJiaZe
 * @date: 2019年12月11日 13:15
 * @version: V1.0
 */
public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunable("M1", 10000L));
        Thread t2 = new Thread(new CaptureRunable("M2", 30000L));
        Thread t3 = new Thread(new CaptureRunable("M3", 15000L));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        long endTimestamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s, end timestamp is:%s\n", startTimestamp, endTimestamp);
    }

}
class CaptureRunable implements Runnable{
    private String machineName;
    private long spendTime;

    public  CaptureRunable(String machineName,long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        //do the really capture data.
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp [%s] and successfully.\n",
                    System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish.";
    }
}