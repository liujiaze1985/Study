package com.ljz.concurrent.chapter17;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter17
 * @Description: 模拟产品
 * @author: liujiaze
 * @date: 2019年12月21日 15:18
 * @version: V1.0
 */
public class MyRequest {

    private final String name;
    private final int number;

    public MyRequest(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * 怎么摆放
     */

    public void execute() {
        //this 默认会调用toString ，如果 没有重写toString,会调用hashcode
        System.out.println(Thread.currentThread()
                .getName() + " executed " + this);

    }

    @Override
    public String toString() {
        return "Request => No. " + number + "\tName. " + name;
    }
}
