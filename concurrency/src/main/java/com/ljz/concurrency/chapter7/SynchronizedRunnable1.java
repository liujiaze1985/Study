package com.ljz.concurrency.chapter7;

/**
 * Copyright © 2019年12月11日 LiuJiaZe. All rights reserved.
 * @Project: concurrency
 * @Package: com.ljz.concurrency.chapter7
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月11日 17:11
 * @version: V1.0
 */
public class SynchronizedRunnable1 implements Runnable {
    private final static int MAX = 500;
    private int index = 1;

    @Override
    public  void run() {
        while (true) {
            if (ticket()) {
                break;
            }
        }

        //2
    }

    /**
     * 是否可以售票
     * @return
     */
    private synchronized boolean ticket() {
        //1. getField  读操作，读的过程中要拷贝到当前线程的cache中，有可能在拷贝过程中已经被其它线程修改，所以要加锁
        if (index > MAX) {
            return true ;
        }
        try {
            Thread.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //2.  修改操作 细分为三步
        //2.1  get Field index
        //2.2 index = index+1
        //2.3 put field index 放回主内存
        System.out.println(Thread.currentThread() + "的号码是：" + index++);
        return false;
    }
}
