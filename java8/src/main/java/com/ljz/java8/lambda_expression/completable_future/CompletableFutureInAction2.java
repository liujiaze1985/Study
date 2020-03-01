package com.ljz.java8.lambda_expression.completable_future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: CompletableFuture 异步
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:25
 * @version: V1.0
 */
public class CompletableFutureInAction2 {

    public static void main(String[] args) {

//        demonstration1();
        demonstration2();

    }

    private static void demonstration2() {
        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executorService = Executors.newFixedThreadPool(2,r-> {
            Thread t = new Thread(r);
            t.setDaemon(false); //非守护线程，等待后续执行结束
            return t;
        });
        //supplyAsync 异步动作，提交任务，无入参，有返回值
        CompletableFuture.supplyAsync(CompletableFutureInAction::get,executorService).whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
            finished.set(true);
        });
        System.out.println("==========no=====block===========");

        executorService.execute(()-> System.out.println("................test................"));
        executorService.shutdown();
    }

    private static void demonstration1() {
        AtomicBoolean finished = new AtomicBoolean(false);

        //supplyAsync 异步动作，无入参，有返回值
        CompletableFuture.supplyAsync(CompletableFutureInAction::get).whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
            finished.set(true);
        });
        System.out.println("==========no=====block===========");
        while (!finished.get()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
