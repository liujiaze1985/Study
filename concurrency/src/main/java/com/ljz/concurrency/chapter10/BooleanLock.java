package com.ljz.concurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Copyright ? 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter10
 * @Description: 所有操作的 操作都synchronized
 * @author: LiuJiaZe
 * @date: 2019年12月12日 20:48
 * @version: V1.0
 */
public class BooleanLock implements Lock {
    //The initValue is true indicated the lock have be get.
    //The initValue is false indicated the lock is free (other thread can get this.)
    private boolean initValue;

    //记录blockedThread
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public BooleanLock() {
        this.initValue = false;
    }

    private Thread currentThread;//当前获取锁的线程，谁获取锁，谁来释放


    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) { //已经被人拿了
            blockedThreadCollection.add(Thread.currentThread()); //放入阻塞容器
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread()); //拿到锁，从阻塞容器移除
        this.initValue = true;//拿到锁
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;//应该结束时间
        while (initValue) {
            if (hasRemaining <= 0) { //判断是否超时
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis() ;

        }
        //blockedThreadCollection.remove(Thread.currentThread()); //拿到锁，从阻塞容器移除
        this.initValue = true;
        this.currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;//释放锁
            Optional.of(Thread.currentThread() + "release the lock monitor.")
                    .ifPresent(System.out::println); // monitor 是 BooleanLock
            this.notifyAll();//唤醒所有wait 的线程
        }

    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection); //返回一个不可修改的实例（否则容易被其它线程操作）
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
