package com.ljz.concurrent.chapter11;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description: 多线程运行上下文设计模式介绍
 * @author: liujiaze
 * @date: 2019年12月19日 14:43
 * @version: V1.0
 */
public class ExecutionTask implements Runnable {
    /**
     * 场景
     * ExecutionTask1 ：是一个线程，并发处理的时候，会做很多事情 例如，查询数据库，调用HTTP接口
     *                 1.实现Runnable
     *                 2. 执行逻辑 调用其它
     */

    private QueryFromDBAction queryFromDBAction = new QueryFromDBAction();
    private QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();



    @Override
    public void run() {

        final MyContext context = new MyContext();
        queryFromDBAction.execute(context);
        System.out.println("The Name query successful");
        queryFromHttpAction.execute(context);
        System.out.println("The Card Id  query successful");
        System.out.println("The Name is "+ context.getName() + " and CardId "+ context.getCardId());

    }

}
