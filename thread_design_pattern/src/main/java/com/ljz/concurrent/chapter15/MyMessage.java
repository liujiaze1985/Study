package com.ljz.concurrent.chapter15;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter15
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:15
 * @version: V1.0
 */
public class MyMessage {
    private final String value;

    public MyMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
