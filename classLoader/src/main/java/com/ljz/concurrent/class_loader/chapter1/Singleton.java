package com.ljz.concurrent.class_loader.chapter1;

/**
 * Copyright © 2019年12月22日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter1
 * @Description: 主动使用总结
 * @author: liujiaze
 * @date: 2019年12月22日 17:55
 * @version: V1.0
 */
public class Singleton {
    //位置1
    private static Singleton instance = new Singleton();
    public static int x = 0;

    public static int y;

    //位置2
    //private static Singleton instance = new Singleton();
    public Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);

    }
}
/**
 *  位置1 0 1
 *      链接-准备
 *           instance = null;
 *           int x = 0;
 *           int y = 0;
 *      初始化:
 *           instance =  new Singleton();
 *           x++ => x = 1;
 *           y++ => y = 1;
 *          为类的静态变量赋予正确的初始值
 *          x = 0
 *          y = 1
 *
 *
 *  位置2 1 1
 *      链接-准备
 *          int x = 0;
 *          int y = 0;
 *          instance = null;
 *
 *      初始化:
 *           new Singleton()
 *           x++
 *           y++
 */