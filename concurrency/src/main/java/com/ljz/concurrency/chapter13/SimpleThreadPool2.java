package com.ljz.concurrency.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter13
 * @Description: 继承 增加自动扩充线程数量 ，闲时自动回收的功能
 * @author: LiuJiaZe
 * @date: 2019年12月13日 20:32
 * @version: V1.2
 */
public class SimpleThreadPool2 extends Thread {

    //v1.2 线程池大小 可变，取消final 修饰
    private int size;
    //v1.2 取消 默认线程池大小
    //private final static int DEFAULT_SIZE = 10;

    //定义线程时 自增
    private static volatile int seq = 0;

    //V1.1 任务队列大小
    private final int queueSize;
    //V1.1 默认任务队列大小
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;


    //线程前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    //线程组
    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    //工作队列
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    //v1.1 默认拒绝策略
    private final DiscardPolicy discardPolicy;
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task.");
    };

    //v1.1 线程池状态
    private volatile boolean destroy = false;

    //v1.2
    private int min;

    //v1.2
    private int active;

    //v1.2
    private int max;


    public SimpleThreadPool2() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    //v1.2 修改构造函数，去除size ，增加 min active max
    public SimpleThreadPool2(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();//初始化
    }

    //v1.2
    private void init() {
        for (int i = 0; i < this.min; i++) { //初始化线程数为min
            createWorkTask();
        }
        this.size = min;
        this.start();//启动
    }

    @Override
    public void run() {
        while (!destroy) {
            //每隔五秒输出线程池相关信息
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                              this.min,
                              this.active,
                              this.max,
                              this.size,
                              TASK_QUEUE.size());

            try {
                Thread.sleep(5_000L);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to active.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }

                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=========Reduce========");
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }

                            WorkerTask task = it.next();
                            task.close();
                            task.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //构建任务队列
    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 提交任务（对外提供接口）
     * v1.1  当超过TASK_QUEUE_SIZE需要处理
     *       判断线程池状态
     */
    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The thread pool already destroy and not allow submit task.");
        }

        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard(); //执行拒绝策略
            }

            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    /**
     * v1.1 停止线程池
     * v1.2  synchronized (THREAD_QUEUE)
     */
    public void shutdown() throws InterruptedException {

        while (!TASK_QUEUE.isEmpty()) { //如果工作队列不为空
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size(); //获取线程数
            while (initVal > 0) {
                for (WorkerTask task : THREAD_QUEUE) {
                    if (task.getTaskState() == TaskState.BLOCKED) { //如果是阻塞状态
                        task.interrupt(); //中断
                        task.close();     //将状态置为DEAD （避免中断过程中  taskState = TaskState.FREE;）
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        System.out.println(GROUP.activeCount());
        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    /**
     * v1.1 获取任务队列大小
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * v1.1 获取线程池大小
     */
    public int getSize() {
        return size;
    }

    //v1.2 方法名从destroy修改为isDestroy
    public boolean isDestroy() {
        return this.destroy;
    }

    //v1.2
    public int getMin() {
        return min;
    }

    //v1.2
    public int getActive() {
        return active;
    }

    //v1.2
    public int getMax() {
        return max;
    }

    //工作线程状态
    private enum TaskState {
        FREE,
        RUNNING,
        BLOCKED,
        DEAD
    }

    /**
     * v1.1
     * 定义异常
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * v1.1
     * 定义拒绝策略
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;

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
            return this.taskState;
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
                            System.out.println("Closed.");
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

    public static void main(String[] args) throws InterruptedException {

        //SimpleThreadPool1 threadPool = new SimpleThreadPool1(6,10,SimpleThreadPool1.DEFAULT_DISCARD_POLICY); //测试拒绝策略
        SimpleThreadPool2 threadPool = new SimpleThreadPool2();
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
                                  Thread.sleep(3_000);
                              } catch (InterruptedException e) {
                                  e.printStackTrace();
                              }
                              System.out.println("The runnable " + i + " be serviced by " + Thread.currentThread() + " finished.");
                          })

                 );

        //Thread.sleep(10_000);
        //threadPool.shutdown();
        //threadPool.submit(() -> System.out.println("The runnable   be serviced by " + Thread.currentThread()));


    }
}
