package com.ljz.concurrent.chapter9;

import java.util.Random;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter9
 * @Description: 接收服务： 通过queue 来接收
 * @author: liujiaze
 * @date: 2019年12月17日 15:00
 * @version: V1.0
 */
public class ServerThread extends Thread {

    //关闭标识
    private volatile boolean flag = false;
    private final RequestQueue queue;

    private final Random random;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!flag) {
            MyRequest request  = queue.getQueue();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }

            System.out.println("Server ->" +request.getValue());

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
               return;
            }

        }
    }

    public void close() {
        this.flag = true;//该赋值为原子性
        this.interrupt();
        /**
         * 中断的位置
         * 1.  ServerThread.run return  完成的时候设置了flase
         * 2.  getQueue 时进入wait状态 flag起不到作用， 中断时返回null出来，
         * 3.  获取request以后，进入了sleep的过程
         */
    }
}
