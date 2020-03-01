package com.ljz.concurrency.thread_api.close_thread;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月11日 15:47
 * @version: V1.0
 */
public class ThreadCloseForce {
    public static void main(String[] args) {
        demonstration();
    }

    /**
     *
     *
     */
    private static void demonstration() {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(()-> {
            //load a very heavy resource. 加载非常重的资源
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000); //finished在执行线程的守护线程结束时，已被设置为true
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
