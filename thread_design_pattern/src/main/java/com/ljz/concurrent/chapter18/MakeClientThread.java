package com.ljz.concurrent.chapter18;

import com.ljz.concurrent.chapter18.active_object.ActiveObject;
import com.ljz.concurrent.chapter18.result.MyResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 17:51
 * @version: V1.0
 */
public class MakeClientThread extends Thread {
    private final ActiveObject activeObject;
    private final char fillChar;

    public MakeClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {

        try {
            for (int i = 0; true; i++) {
                MyResult result = activeObject.makeString(i + 1, fillChar);
            Thread.sleep(200);
                String value = (String) result.getResultValue();
                System.out.println(Thread.currentThread().getName() + " : Value = " + value);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
