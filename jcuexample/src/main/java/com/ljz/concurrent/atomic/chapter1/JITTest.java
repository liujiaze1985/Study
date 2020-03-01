package com.ljz.concurrent.atomic.chapter1;

/**
 * Copyright © 2019年12月25日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1
 * @Description: volatile JNI 相关问题
 * @author: liujiaze
 * @date: 2019年12月25日 20:59
 * @version: V1.0
 */
public class JITTest {

    //private volatile static boolean init = false; //会退出
    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        //T1
        new Thread() {
            @Override
            public void run() {
                while (!init) {
                    //1.
                    //System.out.println(".");
                }
            }
        }.start();

        Thread.sleep(1000); //保证上边的线程完全起来，再起其它线程

        //T2
        new Thread() {
            @Override
            public void run() {
                init = true;
                System.out.println("Set init to true.");
            }
        }.start();


    }
}

/**
 * 当1 的位置什么都不做的时候，T1 一直不停止（JDK8） ,JDK6会停止，JIT运行时进行了优化，非volatile 的
 * boolean变量 做为条件修饰的代码块，等价为 while(true),如果while 代码块中有其它的代码，那么不会进行优化
 *
 * 解决方式：
 *  1。volatile 修饰该boolean变量
 *  2。AtomicBoolean
 *
 */