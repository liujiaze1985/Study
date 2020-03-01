package com.ljz.concurrent.chapter10;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter10
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 17:04
 * @version: V1.0
 */
public class ThreadLocalSimpleTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();
    private static ThreadLocal<String> threadLocal1 = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    //JVM start main thread
    public static void main(String[] args) throws InterruptedException {

        //threadLocal.set("Alex");
        Thread.sleep(1000);
        String value =  threadLocal1.get();
        System.out.println(value);
    }
}
