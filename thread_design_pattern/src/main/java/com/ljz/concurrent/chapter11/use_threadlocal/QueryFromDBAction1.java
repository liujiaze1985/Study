package com.ljz.concurrent.chapter11.use_threadlocal;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 14:53
 * @version: V1.0
 */
public class QueryFromDBAction1 {
    //context
    public void execute() {
        //从DB里query 数据
        try {
            Thread.sleep(1000L);
            String name = "Alex_"+Thread.currentThread().getName();//从数据库获取到的数据
             ActionContext.getActionContext().getContext().setName(name); //放入上下文

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
