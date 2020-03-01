package com.ljz.concurrency.thread_api.close_thread;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.thread_api
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月11日 15:48
 * @version: V1.0
 */
public class ThreadService {
    /**
     * 执行线程
     */
    private Thread executeThread;

    /**
     * 是否执行结束
     */
    private boolean finished = false;


    /**
     * 执行异步任务
     * @param task
     */
    public void execute(Runnable task) {
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);//守护线程，当主线程关闭时，守护线程结束
                runner.start();
                try {
                    runner.join(); //直到runner执行完毕，再执行执行线程的其它逻辑
                    finished = true;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }

            }
        };

        executeThread.start();
    }

    /**
     * 等待 mills 毫秒后关闭线程
     * @param mills
     */
    public void shutdown(long mills) {
        long currentTime =System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束 ");
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e ) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;

    }
}
