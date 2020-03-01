package com.ljz.concurrent.chapter18.request;

import com.ljz.concurrent.chapter18.active_object.Servant;
import com.ljz.concurrent.chapter18.result.FutureResult;
import com.ljz.concurrent.chapter18.result.MyResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description: 主要负责{@link ActiveObject#makeString(int, char)}
 * @author: liujiaze
 * @date: 2019年12月21日 17:00
 * @version: V1.0
 */
public class MakeStringRequest extends MethodRequest {
    private final int count;
    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        MyResult result = servant.makeString(count, fillChar);//真正做事
        //
        futureResult.setResult(result);
    }
}
