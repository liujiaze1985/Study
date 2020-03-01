package com.ljz.concurrent.chapter18;

import com.ljz.concurrent.chapter18.active_object.ActiveObject;
import com.ljz.concurrent.chapter18.active_object.ActiveObjectFactory;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 16:10
 * @version: V1.0
 */
public class ActiveObjectClientTest {


    public static void main(String[] args) {
        //System.gc(); //发送命令，到另一个线程中，在另一个线程中执行这个命令，

        ActiveObject activeObject = ActiveObjectFactory.creatActiveObjects();
        new MakeClientThread("Alice",activeObject).start();
        new MakeClientThread("Bobby",activeObject).start();
        new DisplayClientThread("Chris",activeObject).start();
    }
}
