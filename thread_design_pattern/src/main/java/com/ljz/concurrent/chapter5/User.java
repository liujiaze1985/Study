package com.ljz.concurrent.chapter5;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter5
 * @Description: 人，代表一个线程（资源使用者）
 * @author: LiuJiaZe
 * @date: 2019年12月15日 18:14
 * @version: V1.0
 */
public class User extends Thread{
    private final String myName;
    private final String myAddress;
    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName + "BEGIN");
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
