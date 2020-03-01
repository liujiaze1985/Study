package com.ljz.concurrent.chapter16;

import java.util.Random;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter16
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:38
 * @version: V1.0
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread()
                        .getName() + " " + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally { //重点在finally代码块
            this.clean();

        }
    }

    private void clean() {
        System.out.println("do some clean work for the second phase , current counter: " + counter);

    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
