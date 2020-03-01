package com.ljz.concurrent.chapter5;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter5
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月15日 18:18
 * @version: V1.0
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User( "Baobao", "Beijing",gate);
        User gz = new User( "GuangLao", "GuangZhou",gate);
        User sh = new User( "ShangLao", "ShangHai",gate);
        bj.start();
        sh.start();
        gz.start();
    }

}
