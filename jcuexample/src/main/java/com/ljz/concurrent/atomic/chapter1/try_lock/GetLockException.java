package com.ljz.concurrent.atomic.chapter1.try_lock;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic.chapter1.try_lock
 * @Description: 定义没有抢到琐的异常
 * @author: liujiaze
 * @date: 2019年12月26日 19:02
 * @version: V1.0
 */
public class GetLockException extends Exception {
    public GetLockException() {
        super();
    }

    public GetLockException(String message) {
        super(message);
    }
}
