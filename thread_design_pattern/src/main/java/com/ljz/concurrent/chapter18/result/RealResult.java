package com.ljz.concurrent.chapter18.result;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 16:48
 * @version: V1.0
 */
public class RealResult implements MyResult {
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }


    @Override
    public Object getResultValue( ) {
        return this.resultValue;
    }
}
