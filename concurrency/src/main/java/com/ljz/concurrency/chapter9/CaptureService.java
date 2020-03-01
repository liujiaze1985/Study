package com.ljz.concurrency.chapter9;

import java.util.*;

/**
 * Copyright © 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter8
 * @Description: 通知综合应用， 数据采集
 * @author: LiuJiaZe
 * @date: 2019年12月12日 17:18
 * @version: V1.2
 */
public class CaptureService {

    final static private LinkedList<Control> CONTROLS = new LinkedList<>();
    private final static int MAX_WORKER = 5;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        //主机列表
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
              .stream()
              .map(CaptureService::createCaptureThread)
              .forEach(t -> {
                  t.start();
                  worker.add(t);//将线程加入容器中
              });

        worker.stream()
              .forEach(t -> {
                  try {
                      t.join();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              });

        Optional.of("All of capture work finished")
                .ifPresent(System.out::println);
    }


    /**
     * 运行逻辑
     * @param name
     * @return
     */
    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("The worker [" + Thread.currentThread()
                                               .getName() + "] BEGIN capture data.")//开始工作
                    .ifPresent(System.out::println);
            synchronized (CONTROLS) {
                while (CONTROLS.size() > MAX_WORKER) {
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                CONTROLS.addLast(new Control());
            }


            Optional.of("The worker [" + Thread.currentThread()
                                               .getName() + "] is working...") //正在工作
                    .ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (CONTROLS) {
                Optional.of("The worker [" + Thread.currentThread()
                                                   .getName() + "] END capture data.")//完成工作
                        .ifPresent(System.out::println);
                CONTROLS.removeFirst();//删除第一个 先进先出
                CONTROLS.notifyAll();//唤醒wait的队列
            }
        }, name);
    }

    /**
     * 做一个控制的作用
     */
    private static class Control {
    }
}