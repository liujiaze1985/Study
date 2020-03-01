package com.ljz.concurrent.chapter6;

import java.util.Random;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter6
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 17:47
 * @version: V1.0
 */
public class WriteWorker extends Thread {
    private final static Random random = new Random(System.currentTimeMillis());

    //写目标
    private final SharedData data;

    //写的数据
    private final String filler;

    private int index = 0;

    public WriteWorker(SharedData data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写下一个字符
     * @return
     */
    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
