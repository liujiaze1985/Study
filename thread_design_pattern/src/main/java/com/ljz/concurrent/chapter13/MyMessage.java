package com.ljz.concurrent.chapter13;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter13
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 21:44
 * @version: V1.0
 */
public class MyMessage {
    private String data;

    public MyMessage(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
