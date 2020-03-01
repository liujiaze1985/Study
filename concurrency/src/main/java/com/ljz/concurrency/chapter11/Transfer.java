package com.ljz.concurrency.chapter11;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter11
 * @Description: 跟踪方法被调用  public StackTraceElement[] getStackTrace() 获取堆栈信息
 * @author: LiuJiaZe
 * @date: 2019年12月13日 15:10
 * @version: V1.0
 */
public class Transfer {

    public static void main(String[] args) {
        new Test1().test();
    }
}
