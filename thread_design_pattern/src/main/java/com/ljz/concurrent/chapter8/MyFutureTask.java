package com.ljz.concurrent.chapter8;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter8
 * @Description: 真正执行任务的
 * @author: liujiaze
 * @date: 2019年12月17日 13:39
 * @version: V1.0
 */
public interface MyFutureTask<T> {
    T call();
}
