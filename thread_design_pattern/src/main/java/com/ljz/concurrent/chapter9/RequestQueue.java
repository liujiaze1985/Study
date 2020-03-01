package com.ljz.concurrent.chapter9;

import java.util.LinkedList;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter9
 * @Description: 请求
 * @author: liujiaze
 * @date: 2019年12月17日 14:45
 * @version: V1.0
 */
public class RequestQueue {
    //Queue
    LinkedList<MyRequest> queue = new LinkedList<>();

    public MyRequest getQueue() {
        synchronized (queue) {

            while (queue.size() <= 0) {
                //如果queue的size为0 就等待

                try {
                    queue.wait();
                } catch (InterruptedException e) {
                   return null; //
                }
            }
            return queue.removeFirst();
        }

    }

    public void putQueue(MyRequest request) {
        synchronized (queue) {
            queue.addLast(request); //添加至队列
            queue.notifyAll();
        }
    }


}
