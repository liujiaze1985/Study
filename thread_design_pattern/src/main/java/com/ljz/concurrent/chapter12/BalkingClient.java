package com.ljz.concurrent.chapter12;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter12
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 17:28
 * @version: V1.0
 */
public class BalkingClient {

    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("D:\\work\\ideaWorkspace\\javaStudy\\thread_design_pattern\\src\\main\\resources\\balking.txt", "===BEGIN====");
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}
