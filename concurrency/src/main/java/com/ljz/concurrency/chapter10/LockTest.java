package com.ljz.concurrency.chapter10;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Copyright ? 2019年12月12日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter10
 * @Description: 启动多个线程抢锁
 * @author: LiuJiaZe
 * @date: 2019年12月12日 21:00
 * @version: V1.0
 */
public class LockTest {

    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4")
              .forEach(name -> new Thread(() -> {
                  try {
                      booleanLock.lock(1000L);
                      Optional.of(Thread.currentThread()
                                        .getName() + " have the lock monitor.")
                              .ifPresent(System.out::println);
                      work();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } catch (Lock.TimeOutException e) {
                      Optional.of(Thread.currentThread()
                                        .getName() + "Time out").ifPresent(System.out::println);
                  } finally {
                      booleanLock.unlock();
                  }
              }, name).start());
        //如果不判断是否是拿锁的线程执行了unlock， 那么拿锁线程没有执行完，其它线程就开始执行
        //Thread.sleep(100);
        //booleanLock.unlock();
    }

    private static void work() throws InterruptedException {

        Optional.of(Thread.currentThread()
                          .getName() + " is working...")
                .ifPresent(System.out::println);
        Thread.sleep(1_000);
    }
}
