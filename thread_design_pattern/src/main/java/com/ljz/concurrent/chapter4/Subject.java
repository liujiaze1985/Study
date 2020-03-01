package com.ljz.concurrent.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4
 * @Description: 观察者设计模式 Subject / observer
 *               监听模式，
 * @author: LiuJiaZe
 * @date: 2019年12月15日 16:54
 * @version: V1.0
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state ;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state; //赋值并通知
        notifyAllObserver();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObserver() {
        observers.stream().forEach(Observer::update);
    }
}
