package com.ljz.concurrent.class_loader.chapter1;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter1
 * @Description: 验证： 虚拟机有义务 保证<clinit>() 方法的线程安全
 * @author: liujiaze
 * @date: 2019年12月23日 12:00
 * @version: V1.0
 */
public class ClientThreadTest {
    public static void main(String[] args) {
        //验证一个线程构造时，其它线程会卡住
        new Thread(()->new SimpleObject()).start();
        new Thread(()->new SimpleObject()).start();

    }

    static class SimpleObject {
        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName()+"\tI will be initial.");
            while (init.get()) {

            }
            System.out.println(Thread.currentThread().getName()+"\tI am finished initial.");
        }
    }
}
