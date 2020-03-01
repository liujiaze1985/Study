package com.ljz.concurrency.chapter8;

/**
 * Copyright ? 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description: 死锁分析
 * @author: LiuJiaZe
 * @date: 2019年12月12日 17:18
 * @version: V1.0
 */
public class DeadLock {

    private OtherService otherService = new OtherService();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void m1() {
        synchronized (lock) {
            System.out.println("====================m1======================");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("====================m2======================");
        }
    }
}
