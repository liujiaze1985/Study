package com.ljz.concurrent.chapter12;

import java.io.IOException;
import java.util.Random;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter12
 * @Description: 手动修改保存
 * @author: liujiaze
 * @date: 2019年12月19日 17:28
 * @version: V1.0
 */
public class CustomerThread extends Thread {
    private final BalkingData balkingData;

    private final Random random = new Random(System.currentTimeMillis());
    public CustomerThread(BalkingData balkingData) {
        super("Customer");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        try {
            balkingData.save();
            for (int i = 0; i < 20; i++) {
                balkingData.change("No. " + i);
                Thread.sleep(random.nextInt(1000));
                balkingData.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
