package com.ljz.concurrency.chapter10;

import java.util.Collection;

/**
 * Copyright ? 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter10
 * @Description: 如何实现显示锁
 * @author: LiuJiaZe
 * @date: 2019年12月12日 20:43
 * @version: V1.0
 */
public interface Lock {
    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeOutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();


}
