package com.ljz.concurrency;



/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency
 * @Description: 程序异常退出 ，或者被kill 时，释放已占用的资源 ，如果使用kill -9  程序将会直接退出
 *               linux下演示效果好
 * @author: LiuJiaZe
 * @date: 2019年12月13日 14:33
 * @version: V1.0
 */
public class ExitCapture {

    public static void main(String[] args) {
        /**
         * 注册一个钩子程序
         */
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The application will be exit");
            notifyAndRelease();
        }));

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1_000L);
                System.out.println("I am working..."); //模拟正在工作
            } catch (InterruptedException e) {
                //e.printStackTrace();
                //ignore
            }

            i++;
            if (i > 20) {
                throw new RuntimeException("error");//模拟异常退出
            }
        }
    }

    /**
     * 通知管理员，释放资源。。。
     */
    private static void notifyAndRelease() {
        System.out.println("notify to the admin");
        try {
            Thread.sleep(1_000); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Will release resource (socket , file , connection.)");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        System.out.println("Release and Notify Done.");
    }
}
