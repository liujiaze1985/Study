package com.ljz.concurrent.chapter18.request;

import com.ljz.concurrent.chapter18.active_object.Servant;
import com.ljz.concurrent.chapter18.result.FutureResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description: 将主动对象（ActiveObject）的每一个方法都抽成一个命令
 *               MethodRequest 是 Active Objects 最重要的一个概念，会把ActiveObjects每一个方法转换成一个对象
 * @author: liujiaze
 * @date: 2019年12月21日 16:42
 * @version: V1.0
 */
public abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
