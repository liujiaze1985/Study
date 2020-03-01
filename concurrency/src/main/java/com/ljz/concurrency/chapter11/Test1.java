package com.ljz.concurrency.chapter11;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter11
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月13日 15:04
 * @version: V1.0
 */
public class Test1 {
    private Test2 test2 = new Test2();

    public void test() {
        test2.test();
    }
}
