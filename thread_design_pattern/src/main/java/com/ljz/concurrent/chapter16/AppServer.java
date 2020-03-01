package com.ljz.concurrent.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter16
 * @Description: 启动后监听客户端链接，然后分配一个线程工作，在线程工作中，要停服务，那么要关闭线程
 * @author: liujiaze
 * @date: 2019年12月20日 15:46
 * @version: V1.0
 */
public class AppServer extends Thread {

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    //记录工作线程 用来关闭
    private List<ClientHandler> clientHandlers = new ArrayList<>();


    private final ExecutorService SERVICE = Executors.newFixedThreadPool(10);

    private ServerSocket server;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("server is start");
        try {
            this.server = new ServerSocket(port);
            while (start) {
                Socket client = server.accept();//该方法会阻塞住
                ClientHandler clientHandler = new ClientHandler(client);
                SERVICE.submit(clientHandler);
                this.clientHandlers.add(clientHandler); //
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.dispose();//清理工作
        }
    }

    private void dispose() {
        System.out.println("dispose");

        clientHandlers.stream()
                .forEach(ClientHandler::stop); //关闭每个工作线程

        this.SERVICE.shutdown();

    }


    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.server.close();
    }
}
