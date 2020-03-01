package com.ljz.concurrent.atomic;

/**
 * Copyright © 2019年12月27日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月27日 16:19
 * @version: V1.0
 */
public class TestMe {
    public volatile int i;
    public volatile Integer k;
    public Integer l;
    volatile Integer m; //protected
    private volatile int j;

    //多线程对TestMe操作，假设TestMe被原子封装，但是i 并没有被原子封装

    public void increment() {
        i++;
    }

    public int get() {

        return i;
    }

}
