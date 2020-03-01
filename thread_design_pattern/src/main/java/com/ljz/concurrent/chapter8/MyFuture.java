package com.ljz.concurrent.chapter8;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter8
 * @Description: 返回MyFuture 通过get方法获取想要的结果
 * @author: liujiaze
 * @date: 2019年12月17日 13:37
 * @version: V1.0
 */
public interface MyFuture<T> {
    T get() throws InterruptedException;//可以返回任意类型
}
