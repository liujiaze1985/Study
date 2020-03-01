package com.ljz.concurrent.chapter15;

import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter15
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 15:19
 * @version: V1.0
 */
public class PerThreadClient {
    public static void main(String[] args) {
        final MyMessageHandler1 handler = new MyMessageHandler1();
        IntStream.rangeClosed(0, 10)
                .forEach(i -> handler.request(new MyMessage(String.valueOf(i))));

        handler.shutdown();
    }
}

