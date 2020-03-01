package com.ljz.concurrent.chapter9;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter9
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 14:46
 * @version: V1.0
 */
public class MyRequest {
    final private String value;


    public MyRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
