package com.ljz.concurrent.chapter17;


import java.util.Arrays;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter17
 * @Description: 模拟流水线 负责维护和管理工作
 * @author: liujiaze
 * @date: 2019年12月21日 15:16
 * @version: V1.0
 */
public class MyChannel {

    private final static int MAX_REQUEST = 100;
    private final MyRequest[] requestQueue; // 存放产品的队列
    //队头
    private int head;
    //队尾
    private int tail;
    //产品数
    private int count;
    //工人
    private final WorkerThread[] workerPool;

    public MyChannel(int workers) {
        this.requestQueue = new MyRequest[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker- " + i, this);
        }
    }

    /**
     *开始工作
     */
    public void startWorker() {
        System.out.println("MyChannel is startWorker");
        Arrays.asList(workerPool)
                .forEach(WorkerThread::start); //启动每个工人开始工作
    }


    /**
     * MyRequest 为共享资源 需要加锁
     * @param request
     */
    public synchronized void put(MyRequest request) {

        while (count >= requestQueue.length) { //当前产品数量 大于等于 产品队列长度
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //没满 添加商品到队列尾
        this.requestQueue[tail] = request;
        this.tail = (this.tail + 1) % this.requestQueue.length; //下一个队尾的角标
        this.count++;
        this.notifyAll();
    }

    /**
     * 开始干活，取产品
     * @return
     */
    public synchronized MyRequest take() {
        while (count <= 0) { //如果流水线上没有产品
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //有产品
        MyRequest request = this.requestQueue[head];
        this.head = (this.head + 1) % this.requestQueue.length; //下一个队首的角标
        this.count--;
        this.notifyAll();
        return request;
    }
}
