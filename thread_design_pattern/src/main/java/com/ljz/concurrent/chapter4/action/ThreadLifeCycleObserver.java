package com.ljz.concurrent.chapter4.action;

import java.util.List;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4.action
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月15日 17:22
 * @version: V1.0
 */
public class ThreadLifeCycleObserver implements LifeCycleListener {

    private final Object LOCK = new Object();

    //批量查询id
    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream()
           .forEach(id -> new Thread(new ObservableRunnable(this) {
               @Override
               public void run() {
                   try {
                       notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                       System.out.println("query for the id " + id);
                       Thread.sleep(1_000);
                       notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                   } catch (Exception e) {
                       notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                   }
               }
           }).start());

    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName()+"] data changed and state is ["+event.getState()+"]");
            if (event.getCause() != null) {//如果失败
                System.out.println("The runnable ["+event.getThread().getName()+"] process failed.");
                event.getCause().printStackTrace();

            }
        }

    }
}
