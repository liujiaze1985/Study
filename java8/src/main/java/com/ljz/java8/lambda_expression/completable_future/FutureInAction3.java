package com.ljz.java8.lambda_expression.completable_future;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.completable_future
 * @Description: 实现一个异步基于事件回调的Future程序
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:30
 * @version: V1.0
 */
public class FutureInAction3 {
    public static void main(String[] args) {
        useUnBlock();

    }

    private static void useUnBlock() {
        MyFuture<String> future = invoke(() -> {

            try {
                Thread.sleep(10_000);
                return "I am finished.";
            } catch (InterruptedException e) {
                return "I am Error.";
            }
        });
        //注册事件
        future.setCompletable(new MyCompletable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("Error");
                cause.printStackTrace();
            }
        });




        System.out.println("============可以做自己的业务逻辑===============");
        System.out.println(future.get());
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
        MyFuture<T> future = new MyFuture<T>() {
            private MyCompletable<T> completable;

            @Override
            public T get() {
                return result.get(); //获取线程中的值
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }

            @Override
            public MyCompletable<T> getCompletable() {
                return completable;
            }

            @Override
            public void setCompletable(MyCompletable<T> completable) {
                this.completable = completable;

            }
        };
        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if (null != future.getCompletable()) {
                    future.getCompletable().complete(value); //通知

                }
            } catch (Exception cause) {
                if (null != future.getCompletable()) {
                    future.getCompletable().exception(cause);
                }

            }
        });
        t.start();
        return future;
    }


    private interface MyFuture<T> {

        T get();

        boolean isDone();

        MyCompletable<T> getCompletable();

        void setCompletable(MyCompletable<T> completable);
    }

    private interface MyCallable<T> {

        T action();

    }

    private interface MyCompletable<T> {
        void complete(T t);

        void exception(Throwable cause);
    }


}
