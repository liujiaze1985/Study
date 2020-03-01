package com.ljz.concurrent.atomic.chapter3.do_something;

/**
 * Copyright © 2019年12月29日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3.do_something
 * @Description: 给此类一个权限
 * @author: liujiaze
 * @date: 2019年12月29日 16:52
 * @version: V1.0
 */
public class Guard {

    private int ACCESS_ALLOWED = 1;

    private boolean allow() {
        return 42 == ACCESS_ALLOWED; //返回的是false 表示一直不允许
    }

    public void work() {
        if (allow()) { //如果允许
            System.out.println("I am working by allowed...");
        }
    }
}
