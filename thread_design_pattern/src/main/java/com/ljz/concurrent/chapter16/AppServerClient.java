package com.ljz.concurrent.chapter16;

import java.io.IOException;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter16
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 17:04
 * @version: V1.0
 */
public class AppServerClient {
    public static void main(String[] args) throws InterruptedException {
        AppServer server = new AppServer(13345);
        server.start();
        Thread.sleep(20_000L);
        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
