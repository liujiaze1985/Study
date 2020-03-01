package com.ljz.concurrent.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter10
 * @Description: simulator 模拟ThreadLock
 * @author: liujiaze
 * @date: 2019年12月17日 17:20
 * @version: V1.0
 */
public class ThreadLocalSimulator<T> {

    //
    private final Map<Thread, T> storage = new HashMap<>();

    //



    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }

    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (null == value) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue() {

        return null;
    }

}
