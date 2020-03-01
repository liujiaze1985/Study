package com.ljz.concurrent.chapter7;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description: 测试String 类不可变
 * @author: liujiaze
 * @date: 2019年12月16日 18:37
 * @version: V1.0
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "Hello";
        String s1 = s.replace("l", "k");
        System.out.println(s.getClass() + " " + s.hashCode());
        System.out.println(s1.getClass() + " " + s1.hashCode());
    }
    /**
     * String  不可变对象  部分函数的入参加了synchronized
     * StringBuffer  可变对象 有大量加锁，线程安全的类
     * StringBuilder 可变 ，线程不安全，多线程操作，需要加锁
     */
}
