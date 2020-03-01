package com.ljz.concurrent.chapter14;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter14
 * @Description: 自定义CountDown
 * @author: liujiaze
 * @date: 2019年12月20日 15:05
 * @version: V1.0
 */
public class CountDown {
    private final int total;
    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    public void countDown() {
        synchronized (this) {
            this.counter++;
            this.notifyAll();
        }

    }
    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }

    }
}
