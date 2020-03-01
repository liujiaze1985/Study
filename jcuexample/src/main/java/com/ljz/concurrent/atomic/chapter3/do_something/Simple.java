package com.ljz.concurrent.atomic.chapter3.do_something;

/**
 * Copyright © 2019年12月29日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3.do_something
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月29日 16:34
 * @version: V1.0
 */
public class Simple {
    private long l = 0;
    //private int i = 0 ;

    public Simple() {
        this.l = 1;
        //this.i = 1;
        System.out.println("=============== Simple()======================");
    }

    public long getL() {
        return this.l;
    }
}
