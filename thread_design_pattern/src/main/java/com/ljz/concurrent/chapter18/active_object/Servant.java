package com.ljz.concurrent.chapter18.active_object;

import com.ljz.concurrent.chapter18.result.MyResult;
import com.ljz.concurrent.chapter18.result.RealResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description: 真正做事的地方
 * @author: liujiaze
 * @date: 2019年12月21日 16:46
 * @version: V1.0
 */
public class Servant implements ActiveObject {

    @Override
    public MyResult makeString(int count, char fillChar) {
        //拼装，转换
        char[] buf = new char[count];
        for (int i = 0; i < count; i++) {
            buf[i]= fillChar;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println("DisplayString: "+ text);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
