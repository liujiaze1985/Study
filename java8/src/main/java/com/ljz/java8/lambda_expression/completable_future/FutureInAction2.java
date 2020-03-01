package com.ljz.java8.lambda_expression.completable_future;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: 使用JDK自带future
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:30
 * @version: V1.0
 */
public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(10_000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "I am Error.";
            }
        });
        // 可以写自己的业务逻辑

        //        String value = future.get(10,TimeUnit.MICROSECONDS); // 等待10毫秒
        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());
        executorService.shutdown();//手动关闭线程

    }


}
