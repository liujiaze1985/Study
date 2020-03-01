package com.ljz.concurrent.chapter17;

import java.util.Arrays;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter17
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 15:50
 * @version: V1.0
 */
public class WorkerClient {
    public static void main(String[] args) {
        final MyChannel channel = new MyChannel(10); //五个工人
        channel.startWorker();
        Arrays.asList("Alex", "Jack", "William")
                .forEach(i -> new TransportThread(i, channel).start());


    }
}
