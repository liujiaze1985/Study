package com.ljz.concurrency.chapter3;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter3
 * @Description: 多线程与JVM内存结构的关系，虚拟机栈实践
 * @author: LiuJiaZe
 * @date: 2019年12月10日 12:30
 * @version: V1.0
 */
public class CreateThread3 {
    private static int counter = 0; //计数器
    //public class CreateThread3 修饰符，名字引用变量 放入方法区
    private int i = 0; //方法区
    private byte[] bytes = new byte[1024]; //bytes 地址放入方法区，数据放在堆中

    //JVM will create  a thread named "main"
    public static void main(String[] args) {
        //create a JVM stack

        //int j = 0;//局部变量表
        //int[] arr = new int[1024]; //arr 地址在局部变量表中，数据在堆中

        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace(); //java.lang.StackOverflowError 栈溢出
            System.out.println(counter); //一共压了多个栈桢
        }

    }

    private static void add(int i) {
        counter++;

        add(i++);
    }

}
