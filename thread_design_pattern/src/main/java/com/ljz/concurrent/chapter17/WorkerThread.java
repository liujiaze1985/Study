package com.ljz.concurrent.chapter17;

import java.util.Random;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter17
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 15:20
 * @version: V1.0
 */
public class WorkerThread  extends Thread{

    private final MyChannel channel;
    private final static Random random = new Random(System.currentTimeMillis());
    /**
     * 需要用MyChannel 来工作
     * @param name
     * @param channel
     */
    public WorkerThread(String name, MyChannel channel) {
        super(name);
        this.channel = channel;

    }

    /**
     * 加工产品
     */
    @Override
    public void run() {
        System.out.println("WorkerThread is run.");

        try {
            while (true) {
                //从流水线上拿产品
                //加工产品
                channel.take().execute();
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
