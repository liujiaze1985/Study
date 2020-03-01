package com.ljz.concurrent.chapter18;

import com.ljz.concurrent.chapter18.request.MethodRequest;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description: 将MethodRequest 放入队列 中
 *               Scheduler 定时任务
 * @author: liujiaze
 * @date: 2019年12月21日 17:23
 * @version: V1.0
 */
public class SchedulerThread extends Thread {
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        this.queue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            queue.take().execute();
        }
    }
}
