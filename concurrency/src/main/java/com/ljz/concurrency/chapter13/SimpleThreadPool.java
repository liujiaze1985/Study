package com.ljz.concurrency.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter13
 * @Description: 简单线程池
 * @author: LiuJiaZe
 * @date: 2019年12月13日 19:29
 * @version: V1.0
 */
public class SimpleThreadPool {

    private final int size;//线程池大小
    private final static int DEFAULT_SIZE = 10;
    private static volatile int seq = 0;//定义线程时 自增

    //线程前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    //线程组
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");
    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    //
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();//初始化
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    //构建任务队列
    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    //提交任务 （对外提供接口）
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }


    private enum TaskState {
        FREE,
        RUNNING,
        BLOCKED,
        DEAD
    }

    /**
     * 工作线程
     */
    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }


        public TaskState getTaskState() {
            return taskState;
        }


        public void close() {
            this.taskState = TaskState.DEAD;
        }

        //拿任务
        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) { //如果该线程活着
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) { //如果任务队列为空
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;//被中断之后跳出
                        }
                    }

                    runnable = TASK_QUEUE.removeFirst();

                }
                if (null != runnable) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;

                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();
   /*     for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable   be serviced by " + Thread.currentThread());
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable   be serviced by " + Thread.currentThread()+" finished.");
            });
        }*/


        IntStream.rangeClosed(0, 40)
                 .forEach(i -> threadPool.submit(() -> {
                              System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread());
                              try {
                                  Thread.sleep(1_000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread() + " finished.");
                          })

                 );
    }
}
