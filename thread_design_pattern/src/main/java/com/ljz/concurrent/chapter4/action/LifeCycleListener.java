package com.ljz.concurrent.chapter4.action;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter4
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月15日 17:20
 * @version: V1.0
 */
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
