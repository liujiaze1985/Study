package com.ljz.concurrent.class_loader.chapter3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter2
 * @Description:
 *      1。类加载器的委托是优先交给父加载器先去尝试加载
 *      2。父加载器和子加载器其实是一种包装关系，或者包含关系
 *      3。同命名空间一个class 不管理加载多少次，堆中只有一份
 * @author: liujiaze
 * @date: 2019年12月23日 13:54
 * @version: V1.0
 */
public class MyClassLoaderTest2 {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        demonstrate1();

    }

    /**
     * 重复加载 堆中 对象是同一个
     * @throws ClassNotFoundException
     */
    private static void demonstrate3() throws ClassNotFoundException{
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2");

        classLoader2.setDir("D:\\work\\classes\\classloader2");
        Class<?> aClass1 = classLoader1.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass1.hashCode());
        System.out.println(((MyClassLoader)aClass1.getClassLoader()).getClassLoaderName());

        Class<?> aClass2 = classLoader2.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass2);
        System.out.println(aClass2.hashCode());

    }


    /**
     * 无法加载
     * @throws ClassNotFoundException
     */
    private static void demonstrate2() throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        //
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2"); //找不到

        classLoader2.setDir("D:\\work\\classes\\classloader2");
        Class<?> aClass = classLoader2.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.hashCode());
        System.out.println(aClass.getClassLoader());
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }


    /**
     * 父委托演示1
     * @throws ClassNotFoundException
     */
    private static void demonstrate1() throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        //classLoader2 包含 classLoader1 ，而非继承
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2",classLoader1); //可以加载


        classLoader2.setDir("D:\\work\\classes\\classloader2");
        Class<?> aClass = classLoader2.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.hashCode());
        System.out.println(aClass.getClassLoader());
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());


        Class<?> aClass2 = classLoader2.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass2);
        System.out.println(aClass2.hashCode());
    }

    /**
     * 自定义类加载
     * @throws ClassNotFoundException
     */
    private static void demonstrate() throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader-1");
        Class<?> aClass = classLoader.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.hashCode());
        System.out.println(aClass.getClassLoader());
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());
    }
}
