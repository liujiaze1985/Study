package com.ljz.concurrent.class_loader.chapter5;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter5
 * @Description: 打破双亲委托机制
 * @author: liujiaze
 * @date: 2019年12月23日 17:00
 * @version: V1.0
 */
public class SimpleClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        demonstrate();
    }

    /**
     * 自定义加载器加载
     * @throws ClassNotFoundException
     */
    private static void demonstrate() throws ClassNotFoundException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.ljz.concurrent.class_loader.chapter5.SimpleObject");
        System.out.println(aClass.getClassLoader());
    }
}
