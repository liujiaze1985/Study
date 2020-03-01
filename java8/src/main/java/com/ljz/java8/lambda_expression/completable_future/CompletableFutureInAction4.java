package com.ljz.java8.lambda_expression.completable_future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: CompletableFuture 常用API重点详解
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:25
 * @version: V1.0
 */
public class CompletableFutureInAction4 {

    public static void main(String[] args) throws InterruptedException {
        //        MyUtils.getMethds(CompletableFuture.class);
        /** supplyAsync 提交异步任务
         * thenApply 加工结果
         * whenComplete 当完成时执行（同步）
         */
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .whenComplete((v, t) -> System.out.println(v));

        System.out.println("============================================================");

        //handle 两个参数，返回结果 ，和出现的异常
        CompletableFuture.supplyAsync(() -> 1)
                .handle((v, t) -> Integer.sum(v, 10))
                .whenComplete((v, t) -> System.out.println(v))
                .thenRun(System.out::println);

        System.out.println("============================================================");

        //thenAccept 对流进行操作，无返回
        CompletableFuture.supplyAsync(() -> 1).thenApply(i -> Integer.sum(i, 10)).thenAccept(System.out::println);

        System.out.println("============================================================");

        //thenCompose 对输出结果交给另外一个CompletableFuture 进行处理
        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
                .thenAccept(System.out::println);

        System.out.println("============================================================");

        //thenCombine 将第一个入参 ，交给第二个入参（Function）来计算
        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
                .thenAccept(System.out::println);


        System.out.println("============================================================");

        //thenAcceptBoth 类似thenCombine ，无返回值 将第一个入参 ，交给第二个入参（Consumer）来消费
        CompletableFuture.supplyAsync(() -> 1).thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
            System.out.println(r1);
            System.out.println(r2);
            System.out.println(r1 + r2);
        });


        Thread.sleep(1000L);
        //以下为CompletableFutureInAction5内容

        System.out.println("==========================以下为CompletableFutureInAction5内容==================================");

        //runAfterBoth 两个CompletableFuture都结束以后，执行动作
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "is running. supplyAsync");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "is running. runAfterBoth");
            return 2;
        }), () -> System.out.println("Done"));

        System.out.println("============================================================");

        // applyToEither 当其中的一个执行完，就传递给FunctionInterface
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 1");
            return CompletableFutureInAction.get();
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 2");
            return CompletableFutureInAction.get();
        }), v -> v * 10).thenAccept(System.out::println);


        System.out.println("============================================================");

        // acceptEither 类似 applyToEither 无返回值 将第一个入参 ，交给第二个入参（Consumer）来消费
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 1");
            return CompletableFutureInAction.get();
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 2");
            return CompletableFutureInAction.get();
        }), System.out::println);


        System.out.println("============================================================");

        // acceptEither 类似 applyToEither 无返回值 将第一个入参 ，交给第二个入参（Consumer）来消费
        CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 1");
            return CompletableFutureInAction.get();
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("I am Future 2");
            return CompletableFutureInAction.get();
        }), () -> System.out.println("=======done========"));


        //allOf 等到都执行结束再执行
        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction::get))
                .collect(toList());
        //等到都执行结束再执行
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> System.out.println("=======done========"));

        Thread.currentThread().join();
    }

    /**
     * supplyAsync 提交异步任务
     * thenApply 加工结果
     * whenComplete 当完成时执行（同步）
     * whenCompleteAsync 当完成时异步执行
     * handle 两个参数，返回结果 ，和出现的异常
     * thenRun 再进行一个操作，上一步流的结果不会传入
     * thenAccept 无返回值，
     * thenCompose 对输出结果交给另外一个CompletableFuture 进行处理
     * thenCombine 将第一个入参 ，交给第二个入参（Function）来计算
     * thenAcceptBoth 类似thenCombine ，无返回值 将第一个入参 ，交给第二个入参（Consumer）来消费
     * runAfterBoth 两个CompletableFuture都结束以后，执行动作
     * applyToEither 当其中的一个执行完，就传递给FunctionInterface 来计算
     * acceptEither 类似 applyToEither 无返回值 将第一个入参 ，交给第二个入参（Consumer）来消费
     * runAfterEither 运行结束之后，执行一个操作
     * anyOf 类方法 对CompletableFuture 数组进行any运算 等到都执行结束再执行
     * allOf 类方法 对CompletableFuture 数组进行all运算 任意一个执行结束就执行
     */


}
