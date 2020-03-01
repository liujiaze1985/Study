package com.ljz.concurrent.chapter7;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 18:29
 * @version: V1.0
 */
public class UsePersonThread extends Thread {
    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print" + person.toString());
        }
    }
}
