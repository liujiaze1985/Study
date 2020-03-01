package com.ljz.concurrent.chapter18.active_object;

import com.ljz.concurrent.chapter18.SchedulerThread;
import com.ljz.concurrent.chapter18.request.DisplayStringRequest;
import com.ljz.concurrent.chapter18.request.MakeStringRequest;
import com.ljz.concurrent.chapter18.result.FutureResult;
import com.ljz.concurrent.chapter18.result.MyResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18.active_object
 * @Description: 因为只是对一个对象进行代理，此处使用了静态代理的方式，
 *               如果是对多个对象进行代理，建议改进为动态代理
 *               该类主要作用： 整合  Servant,SchedulerThread
 * @author: liujiaze
 * @date: 2019年12月21日 17:32
 * @version: V1.0
 */
public class ActiveObjectProxy implements ActiveObject {
    private final Servant servant;
    private final SchedulerThread schedulerThread;

    public ActiveObjectProxy(Servant servant, SchedulerThread schedulerThread) {
        this.servant = servant;
        this.schedulerThread = schedulerThread;
    }


    @Override
    public MyResult makeString(int count, char fillChar) {
        FutureResult result = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant,result,count,fillChar));
        return result;
    }

    @Override
    public void displayString(String text) {
        //最终交由servant 来进行处理
        schedulerThread.invoke(new DisplayStringRequest(servant ,text));

    }
}
