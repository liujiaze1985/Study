package com.ljz.concurrent.chapter4.action;

import java.util.Arrays;
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
public class ThreadLifeCycleObserverClient  {
    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }


}
