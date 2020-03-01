package com.ljz.concurrent.atomic.chapter1.try_lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1.try_lock
 * @Description: 没有抢到锁的，抛出异常，然后去干别的
 * @author: liujiaze
 * @date: 2019年12月26日 19:11
 * @version: V1.0
 */
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    /**
     * 抢锁
     * @throws GetLockException
     */
    public void tryLock() throws GetLockException {
        boolean sucess = value.compareAndSet(0, 1);//对比是否是0，如果是0 那么变为1
        if (!sucess) {
            throw new GetLockException("Get the Lock failed.");
        } else {
            lockedThread = Thread.currentThread();

        }
    }

    public void unLock() {


        if (0 == value.get()) { //已经被释放过了
            return;
        }
        if (lockedThread == Thread.currentThread()) {
            value.compareAndSet(1, 0);
        }
    }
}
