package com.ljz.concurrent.chapter11.use_threadlocal;


/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter11
 * @Description: 上下文，在同一个线程中，Context的构造函数和参数比较多的时候，Context从头传到尾会很麻烦
 *               1。线程安全 而且不用加锁
 *               2。高效
 * @author: liujiaze
 * @date: 2019年12月19日 15:16
 * @version: V1.0
 */
public final class ActionContext {
    private final static ThreadLocal<MyContext1> THREAD_LOCAL = new  ThreadLocal <MyContext1>(){
        @Override
        protected MyContext1 initialValue() {
            return new MyContext1();
        }
    };
    public MyContext1 getContext() {
        return THREAD_LOCAL.get();
    }

    //将ActionContext 使用Holder做成单例
    private static class MyContextHolder{
        private final static ActionContext  actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return MyContextHolder.actionContext;
    }
}
