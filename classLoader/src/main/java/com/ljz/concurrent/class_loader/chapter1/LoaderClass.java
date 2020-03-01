package com.ljz.concurrent.class_loader.chapter1;

/**
 * Copyright © 2019年12月22日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter1
 * @Description: 类加载详解
 * @author: liujiaze
 * @date: 2019年12月22日 18:21
 * @version: V1.0
 */
public class LoaderClass {
    public static void main(String[] args) {
        //在堆内存中会有四个实例，都同应同一个class
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();
        MyObject myObject4 = new MyObject();

        System.out.println(myObject1.getClass() == myObject2.getClass());
        System.out.println(myObject1.getClass() == myObject3.getClass());
        System.out.println(myObject1.getClass() == myObject4.getClass());
        System.out.println(MyObject.x);
    }
}

class MyObject {
    public static int x = 10 ;

}

/**
 * 内存分布
 * MyObject myObject1 = new MyObject();
 *
 * 方法区
 *  MyObject 数据结构
 *
 * 堆
 *  MyObject 数据
 *  myObject1 数据
 *
 *
 */