package com.ljz.concurrent.chapter4.action;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4
 * @Description: 观察者设计模式观察 线程的生命周期
 * @author: LiuJiaZe
 * @date: 2019年12月15日 17:11
 * @version: V1.0
 */
public abstract class ObservableRunnable implements Runnable {
    final protected LifeCycleListener listener; //生命周期监听者

    public ObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    /**
     * 通知
     * @param event
     */
    protected void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);

    }

    /**
     * 运行状态
     */
    public enum RunnableState{
        RUNNING,ERROR,DONE;
    }



    public static class RunnableEvent{
        private final RunnableState state; //运行状态s
        private final Thread thread; //执行时的线程
        private final Throwable cause; //失败原因

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }


}
