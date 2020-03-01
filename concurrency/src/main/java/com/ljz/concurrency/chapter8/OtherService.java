package com.ljz.concurrency.chapter8;

/**
 * Copyright ? 2019��12��12�� LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019��12��12�� 17:18
 * @version: V1.0
 */
public class OtherService {

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    private final Object lock = new Object();
    public void s1() {
        synchronized (lock) {
            System.out.println("+++++++++++++++++s1++++++++++++++++++");
        }
    }
    public void s2() {
        synchronized (lock) {
            deadLock.m2();
            System.out.println("+++++++++++++++++s2++++++++++++++++++");
        }
    }
}
