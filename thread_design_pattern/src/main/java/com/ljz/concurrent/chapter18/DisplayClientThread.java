package com.ljz.concurrent.chapter18;

import com.ljz.concurrent.chapter18.active_object.ActiveObject;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 17:47
 * @version: V1.0
 */
public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                String text = Thread.currentThread().getName() + "=>" +i;
                activeObject.displayString(text);
                Thread.sleep(200);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
