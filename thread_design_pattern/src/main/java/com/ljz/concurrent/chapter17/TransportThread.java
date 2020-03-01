package com.ljz.concurrent.chapter17;

import java.util.Random;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter17
 * @Description: 模拟 往传送带上放产品
 * @author: liujiaze
 * @date: 2019年12月21日 15:38
 * @version: V1.0
 */
public class TransportThread extends Thread {
    //将产品放到传送带
    private final MyChannel channel;


    private final static Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, MyChannel channel) {
        super(name);
        this.channel = channel;
    }

    /**
     * 工作内容
     */
    @Override
    public void run() {
        System.out.println("TransportThread is run.");
        try {
            for (int i = 0; true; i++) {

                //构造产品
                MyRequest request = new MyRequest(getName(), i);
                //放到传送带
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
