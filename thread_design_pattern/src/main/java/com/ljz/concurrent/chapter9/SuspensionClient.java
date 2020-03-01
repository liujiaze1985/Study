package com.ljz.concurrent.chapter9;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter9
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 16:46
 * @version: V1.0
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();

        Thread.sleep(10000);
        serverThread.close();
    }
}
