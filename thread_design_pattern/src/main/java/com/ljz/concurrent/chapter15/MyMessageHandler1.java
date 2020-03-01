package com.ljz.concurrent.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter15
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:16
 * @version: V1.1 使用线程池改进
 */
public class MyMessageHandler1 {
    private final static Random random = new Random(System.currentTimeMillis());

    //使用线程池
    private final static Executor EXECUTOR = Executors.newFixedThreadPool(5);


    public  void request(MyMessage message) {
        EXECUTOR.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println( "The message will be handle by "+Thread.currentThread().getName()+" " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 被调用后会立即返回，但其中的任务还是会继续处理
     */
    public void shutdown() {
        ((ExecutorService)EXECUTOR).shutdown();
    }
}
