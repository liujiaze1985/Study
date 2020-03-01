package com.ljz.concurrency.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * Copyright © 2019年12月13日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrency.chapter11
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月13日 15:04
 * @version: V1.0
 */
public class Test2 {


    public void test() {

        Arrays.asList(Thread.currentThread().getStackTrace())
              .stream()
              .filter(e -> !e.isNativeMethod())
              .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                                    .ifPresent(System.out::println)
              );
    }
}
