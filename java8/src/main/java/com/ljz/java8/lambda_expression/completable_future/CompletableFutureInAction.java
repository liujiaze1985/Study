package com.ljz.java8.lambda_expression.completable_future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: CompletableFuture 的基本用法
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:25
 * @version: V1.0
 */
public class CompletableFutureInAction {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        //        MyUtils.getMethds(CompletableFuture.class);

        demonstration();
    }

    private static void demonstration() {
        //CompletableFuture 一般使用工厂方法获取
        CompletableFuture<Double> completableFuture = new CompletableFuture();
        new Thread(() -> {
            Double value = get();
            completableFuture.complete(value);
        }).start();
        System.out.println("==========no=====block===========");
        //主动获取结果
        try {
            Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("==================================================================");
        //接收通知方式获取结果

        completableFuture.whenComplete((v, t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });

    }

    protected static Double get() {
        try {
            Thread.sleep(RANDOM.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }

}
