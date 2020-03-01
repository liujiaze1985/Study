package com.ljz.concurrent.chapter6;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter6
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 17:47
 * @version: V1.0
 */
public class ReadWorker extends Thread{

    //读取目标
    private final SharedData data;



    public ReadWorker(SharedData data) {
        this.data = data;
    }


    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuff = data.read();
                System.out.println(Thread.currentThread().getName() +" reads " + String.valueOf(readBuff));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
