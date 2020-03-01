package com.ljz.java8.lambda_expression.completable_future;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: CompletableFuture 流水线工作，join多个异步任务
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:25
 * @version: V1.0
 */
public class CompletableFutureInAction3 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        //        demonstration2();

//        demonstration3();
        demonstration4();

    }

    /**
     * 对demonstration3进行简化
     */
    private static void demonstration4() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false); //非守护线程，等待后续执行结束
            return t;
        });

        List<Integer> productionIds = Arrays.asList(1, 2, 3, 4, 5);
        //CompletableFuture.supplyAsync 并发处理
        //completableFutureStream 不会立即执行
        //thenApply 对每个价格进行处理
        //CompletableFuture::join 等到所有结果都执行结束

        List<Double> result = productionIds.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executorService))
                .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
                .map(CompletableFuture::join)
                .collect(toList());
        System.out.println("result => " + result);
        executorService.shutdown(); //显示关闭线程池
    }

    /**
     *
     *  需求： 开商店（shop） , 有很多商品 （production） ,根据productionId查询价格，给价格翻n倍
     *
     */
    private static void demonstration3() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false); //非守护线程，等待后续执行结束
            return t;
        });

        List<Integer> productionIds = Arrays.asList(1, 2, 3, 4, 5);
        //并发处理
        //completableFutureStream 不会立即执行

        Stream<CompletableFuture<Double>> completableFutureStream = productionIds.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executorService));


        //map 对每个价格进行处理
        Stream<CompletableFuture<Double>> multiplyFuture = completableFutureStream.map(future -> future.thenApply(
                CompletableFutureInAction3::multiply));

        //CompletableFuture::join 等到所有结果都执行结束
        List<Double> result = multiplyFuture.map(CompletableFuture::join).collect(toList());
        System.out.println("result => " + result);
    }

    /**
     * 对结果进行加工
     */
    private static void demonstration2() {
        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread t = new Thread(r);
            t.setDaemon(false); //非守护线程，等待后续执行结束
            return t;
        });
        //supplyAsync 异步动作，提交任务，可以对数据进行加工，无入参，有返回值
        //thenApply 对结果进行加工
        CompletableFuture.supplyAsync(CompletableFutureInAction::get, executorService)
                .thenApply(CompletableFutureInAction3::multiply)
                .whenComplete((v, t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
                    finished.set(true);
                });
        System.out.println("==========no=====block===========");

        executorService.execute(() -> System.out.println("................test................"));
        executorService.shutdown(); //显示关闭线程池
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

    private static double queryProduction(int i) {
        return CompletableFutureInAction.get();

    }

}
