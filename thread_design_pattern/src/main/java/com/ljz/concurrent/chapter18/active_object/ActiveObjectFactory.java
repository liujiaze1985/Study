package com.ljz.concurrent.chapter18.active_object;

import com.ljz.concurrent.chapter18.ActivationQueue;
import com.ljz.concurrent.chapter18.SchedulerThread;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18.active_object
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 17:42
 * @version: V1.0
 */
public final class ActiveObjectFactory {
    private ActiveObjectFactory() {
    }

    public static ActiveObject creatActiveObjects() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy( servant,schedulerThread);
        schedulerThread.start();
        return proxy;
    }
}
