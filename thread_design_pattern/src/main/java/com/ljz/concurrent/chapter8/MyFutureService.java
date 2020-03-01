package com.ljz.concurrent.chapter8;

import java.util.function.Consumer;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter8
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 13:40
 * @version: V1.0
 */
public class MyFutureService {
    /**
     * 返回一个Future 不做其它任何事情,调用后需要轮询
     * @param task
     * @param <T>
     * @return
     */
    public <T> MyFuture<T> submit(final MyFutureTask<T> task) {
        //如果想立即得到返回
        MyAsynFuture<T> myAsynFuture = new MyAsynFuture<>();

        //判断工作是否已经做完
        new Thread(() -> {
            T result = task.call(); //例如 SyncInvoker.get()
            myAsynFuture.done(result);//通知结果
        }).start();


        return myAsynFuture;
    }

    /**
     * 增加callback
     * @param task
     * @param <T>
     * @return
     */
    public <T> MyFuture<T> submit1(final MyFutureTask<T> task,final Consumer<T> consumer) {
        //如果想立即得到返回
        MyAsynFuture<T> myAsynFuture = new MyAsynFuture<>();

        //判断工作是否已经做完
        new Thread(() -> {
            T result = task.call(); //例如 SyncInvoker.get()
            myAsynFuture.done(result);//通知结果
            consumer.accept(result);
        }).start();


        return myAsynFuture;
    }
}
