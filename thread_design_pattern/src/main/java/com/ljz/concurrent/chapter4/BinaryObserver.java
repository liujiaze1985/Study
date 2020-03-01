package com.ljz.concurrent.chapter4;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月15日 16:57
 * @version: V1.0
 */
public  class BinaryObserver  extends Observer{
    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public  void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));

    }

}
