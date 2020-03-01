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
public abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();

}
