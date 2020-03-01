package com.ljz.concurrent.chapter4;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月15日 17:06
 * @version: V1.0
 */
public class ObserverClient {
    public static void main(String[] args) {
        final Subject subject = new Subject();
        BinaryObserver binaryObserver = new BinaryObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);

        System.out.println("===============================");
        subject.setState(10);
        System.out.println("===============================");
        subject.setState(10);
        System.out.println("===============================");
        subject.setState(10);
    }

}
