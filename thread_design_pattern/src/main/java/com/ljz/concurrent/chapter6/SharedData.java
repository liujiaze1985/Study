package com.ljz.concurrent.chapter6;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter6
 * @Description: 共享数据
 * @author: liujiaze
 * @date: 2019年12月16日 16:52
 * @version: V1.0
 */
public class SharedData {

    //char数组 ，将其作为一个buffer 读线程不断读数据 ，写线程不断写入数据
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            this.buffer[i] = '*';
        }
    }

    /**
     * 读操作
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        } finally { //保证可以释放
            lock.readUnLock();
        }

    }

    private char[] doRead() {
        //创建一个副本 一个一个赋值
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        this.slowly(50);
        return newBuffer;
    }


    /**
     * 写操作
     * @param c
     * @return
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally { //保证可以释放
            lock.writeUnLock();
        }

    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }


    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
