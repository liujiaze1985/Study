package com.ljz.concurrent.chapter11;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月19日 14:53
 * @version: V1.0
 */
public class QueryFromDBAction {
    //context
    public void execute(MyContext context) {
        //从DB里query 数据
        try {
            Thread.sleep(1000L);
            String name = "Alex_"+Thread.currentThread().getName();//从数据库获取到的数据
            context.setName(name);  //放入上下文

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
