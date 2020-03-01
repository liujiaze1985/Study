package com.ljz.concurrent.chapter16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Copyright © 2019年12月20日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter16
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月20日 16:37
 * @version: V1.0
 */
public class ClientHandler implements Runnable {
    private final Socket socket;

    private volatile boolean running = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        /**
         * try() try resource 会自动关闭流
         */
        //inputStream 服务端返回给客户端写给
        //outputStream  客户端发给服务端
        try (InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)); //装饰器设计模式
                PrintWriter printWriter = new PrintWriter(outputStream)
        ) {

            while (running) {
                String message = br.readLine(); //读入
                if (message == null) {
                    break;
                }

                System.out.println("Come from client >" + message);
                printWriter.write("echo " +message +"\n"); //写出
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.running = false;// 交给stop()方法去关闭
        }finally {
            this.stop();
        }

    }

    public void stop() {
        if (!running) {
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            //  e.printStackTrace();
        }

    }
}
