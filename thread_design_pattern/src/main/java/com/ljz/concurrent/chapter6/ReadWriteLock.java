package com.ljz.concurrent.chapter6;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter6
 * @Description: 读写锁
 * @author: LiuJiaZe
 * @date: 2019年12月15日 18:59
 * @version: V1.0
 */
public class ReadWriteLock {

    //当前有几个线程进行读操作
    private int readingReaders = 0;

    //有几个读不了的线程 （放入wait set队列中）
    private int waitingReaders = 0;

    //当前有几个线程进行写的操作 (只能有一个)
    private int writingWriters = 0;

    //有几个线程等着写 （放入wait set队列中 >= 0）
    private int waitingWriters = 0;


    //v1.1
    //默认使write线程
    private boolean preferWriter = true;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }


    /**
     * 读锁
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            //v1.1 当preferWriter = true 且 写线程等待个数大于0
            while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait(); //正在写，读线程wait
            }
            this.readingReaders++;//正在读的
        } finally {
            this.waitingReaders--; //不wait了  --
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void readUnLock()  {
        this.readingReaders--;//正在读的
        this.notifyAll();
    }

    /**
     * 写锁
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while ( readingReaders>0||writingWriters > 0) { //当有读或写的线程时
                this.wait(); //写线程wait
            }
            this.waitingWriters++;//正在读的
        } finally {
            this.waitingWriters--; //不wait了  --
        }
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnLock()  {
        this.waitingWriters--;
        this.notifyAll();
    }
}
