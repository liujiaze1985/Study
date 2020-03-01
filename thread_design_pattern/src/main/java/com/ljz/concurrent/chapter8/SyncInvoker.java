package com.ljz.concurrent.chapter8;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter8
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 13:19
 * @version: V1.0
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
        //demonstration();

        //demonstration1();
        demonstration2();

    }

    /**
     * 调用有callback 方法
     * @throws InterruptedException
     */
    private static void demonstration2() throws InterruptedException {
        MyFutureService futureService = new MyFutureService();

        futureService.submit1(() -> {
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, System.out::println);

        System.out.println("===========");
        System.out.println(" do other thing.");
        Thread.sleep(1000);
        System.out.println("===========");
    }

    /**
     * 调用没有callback 的方法，结果需要轮循
     * @throws InterruptedException
     */
    private static void demonstration1() throws InterruptedException {
        MyFutureService futureService = new MyFutureService();

        MyFuture<String> myFuture = futureService.submit(() -> {
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });

        System.out.println("===========");
        System.out.println(" do other thing."); //做一些其它事情
        Thread.sleep(1000);
        System.out.println("===========");
        System.out.println(myFuture.get()); //轮询
    }

    /**
     * 同步调用
     * @throws InterruptedException
     */
    private static void demonstration() throws InterruptedException {
        String result = get();
        System.out.println(result);
    }

    private static String get() throws InterruptedException {
        Thread.sleep(10000l);
        return "FINISH";
    }
}
