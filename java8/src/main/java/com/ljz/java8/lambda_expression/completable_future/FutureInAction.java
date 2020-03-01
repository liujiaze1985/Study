package com.ljz.java8.lambda_expression.completable_future;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:30
 * @version: V1.0
 */
public class FutureInAction {
    public static void main(String[] args) {
        //        useUnBlock();
        useBlock();

    }

    private static void useBlock() {
        String block = block(() -> {

            try {
                Thread.sleep(10_000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }


        });
        System.out.println(block);
    }

    private static void useUnBlock() {
        MyFuture<String> future = invoke(() -> {

            try {
                Thread.sleep(10_000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "Error";
            }


        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        //可以做自己的业务逻辑

        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(future.get());
    }

    /**
     * 非阻塞方式
     * @param callable
     * @param <T>
     * @return
     */
    private static <T> MyFuture<T> invoke(MyCallable<T> callable) {

        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();
        MyFuture<T> future = new MyFuture<T>() {
            @Override
            public T get() {
                return result.get(); //获取线程中的值
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    /**
     * 阻塞方式
     * @param callable
     * @param <T>
     * @return
     */
    private static <T> T block(MyCallable<T> callable) {
        return callable.action();
    }

    private interface MyFuture<T> {

        T get();

        boolean isDone();
    }

    private interface MyCallable<T> {

        T action();

    }

}
