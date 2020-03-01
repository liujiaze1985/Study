package com.ljz.concurrent.chapter11;

import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 15:09
 * @version: V1.0
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 5).forEach(i -> new Thread(new ExecutionTask()).start());
    }
}
