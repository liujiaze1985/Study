package com.ljz.concurrency.chapter8;

/**
 * Copyright ? 2019��12��12�� LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description: ��������
 * @author: LiuJiaZe
 * @date: 2019��12��12�� 17:18
 * @version: V1.0
 */
public class DeadLockTest {

    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();
    }


}
