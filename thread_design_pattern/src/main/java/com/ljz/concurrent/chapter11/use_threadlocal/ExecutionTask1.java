package com.ljz.concurrent.chapter11.use_threadlocal;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description: 多线程运行上下文设计模式介绍
 * @author: liujiaze
 * @date: 2019年12月19日 14:43
 * @version: V1.0
 */
public class ExecutionTask1 implements Runnable {
    /**
     * 场景
     * ExecutionTask1 ：是一个线程，并发处理的时候，会做很多事情 例如，查询数据库，调用HTTP接口
     *                 1.实现Runnable
     *                 2. 执行逻辑 调用其它
     */

    private QueryFromDBAction1 queryFromDBAction = new QueryFromDBAction1();
    private QueryFromHttpAction1 queryFromHttpAction = new QueryFromHttpAction1();


    @Override
    public void run() {


        queryFromDBAction.execute();
        System.out.println("The Name query successful");
        queryFromHttpAction.execute();
        System.out.println("The Card Id  query successful");
        MyContext1 context = ActionContext.getActionContext().getContext();
        /**
         * 通过此方法需要注意
         * 如果使用线程池
         * 线程使用完之后会释放，
         * 下次使用时，thread local中还还会有数据 ，所以在使用前需要清理
         */
        System.out.println("The Name is " + context.getName() + " and CardId " + context.getCardId());

    }

}
