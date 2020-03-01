package com.ljz.concurrent.chapter8;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter8
 * @Description: 异步Future的实现
 * @author: liujiaze
 * @date: 2019年12月17日 13:42
 * @version: V1.0
 */
public class MyAsynFuture<T> implements MyFuture {

    //判断任务是否结束
    private volatile boolean done = false;

    //返回的结果
    private T result;

    /**
     * 存放结果
     */

    public void done(T result) {
        synchronized (this) {
            this.result = result;
            this.done = true;
            this.notifyAll(); //方法的调用者不一定和get()是同一个线程
        }

    }


    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (!done) {
                this.wait();
            }
        }
        return result;
    }
}
