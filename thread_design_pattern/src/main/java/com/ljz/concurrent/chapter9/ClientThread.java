package com.ljz.concurrent.chapter9;

import java.util.Random;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter9
 * @Description: ClientThread
 * @author: liujiaze
 * @date: 2019年12月17日 14:55
 * @version: V1.0
 */
public class ClientThread extends Thread{
    /**
     * 模拟场景
     * 多个线程，不断往里添加REQUEST
     *
     */

    private final RequestQueue queue;
    private final Random random;
    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue) {
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Client -> request " + sendValue);
            queue.putQueue(new MyRequest(sendValue)); //添加至请求队列
            try {
                Thread.sleep(random.nextInt(1000)); //随机休眠
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
