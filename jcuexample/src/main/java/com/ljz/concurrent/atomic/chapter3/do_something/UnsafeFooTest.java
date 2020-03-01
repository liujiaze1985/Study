package com.ljz.concurrent.atomic.chapter3.do_something;

import com.ljz.concurrent.MyUtils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright © 2019年12月29日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter3
 * @Description: 测试Unsafe都做些什么
 * @author: liujiaze
 * @date: 2019年12月29日 16:32
 * @version: V1.0
 */
public class UnsafeFooTest {
    public static void main(String[] args) throws Exception {

        //demonstrate();
        //demonstrate1();
        //demonstrate2();
        //demonstrate3();
        //demonstrate4();
        //demonstrate5();
        //demonstrate6();
        demonstrate7();
    }

    /**
     * 获取类长度
     */
    private static void demonstrate7() {
        System.out.println(MyUtils.sizeOf(new Simple()));

    }

    /**
     * 加载类
     */
    private static void demonstrate6() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Unsafe unsafe = MyUtils.getUnsafe();

        byte[] bytes = MyUtils.loadClassContent("D:\\work\\classes", "A.class");
        Class<?> aClass = unsafe.defineClass(null, bytes, 0, bytes.length, null, null);
        int v = (int) aClass.getMethod("getI").invoke(aClass.newInstance(), new Object[0]);
        System.out.println(aClass.getClassLoader());
        System.out.println(v);
    }

    /**
     * 测试绕过权限工作
     */
    private static void demonstrate5() throws NoSuchFieldException {
        Unsafe unsafe = MyUtils.getUnsafe();
        Guard guard = new Guard();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
        guard.work();

    }

    /**
     * 测试没有权限不可工作
     */
    private static void demonstrate4() {
        Guard guard = new Guard();
        guard.work();

    }

    /**
     * 使用 Unsafe 绕过初始化
     */
    private static void demonstrate3() throws InstantiationException {
        Unsafe unsafe = MyUtils.getUnsafe();
        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
        System.out.println(simple.getL());
        System.out.println(simple.getClass());
        System.out.println(simple.getClass().getClassLoader());
        /**
         * 0 证明Simple并没有被初始化
         * class com.ljz.concurrent.atomic.chapter3.do_something.Simple
         * sun.misc.Launcher$AppClassLoader@4aa298b7
         */
    }

    /**
     * classLoader 时不会出初化, 直接开辟内存
     * 如果使用需要引用的时候 还是会初始化
     */
    private static void demonstrate2() throws ClassNotFoundException {
        Class.forName("com.ljz.concurrent.atomic.chapter3.do_something.Simple");
    }

    /**
     * Simple.class.newInstance() 的方式构造时会打印
     * =============== Simple()===============
     */
    private static void demonstrate1() throws IllegalAccessException, InstantiationException {
        Simple simple = Simple.class.newInstance();
    }

    /**
     * new 的方式构造时会打印
     * =============== Simple()===============
     */
    private static void demonstrate() {
        Simple simple = new Simple();
        System.out.println(simple.getL());
    }
}
