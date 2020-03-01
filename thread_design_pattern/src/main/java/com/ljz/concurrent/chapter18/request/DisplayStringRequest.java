package com.ljz.concurrent.chapter18.request;

import com.ljz.concurrent.chapter18.active_object.Servant;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月21日 17:10
 * @version: V1.0
 */
public class DisplayStringRequest extends MethodRequest{
    private final String text;

    public DisplayStringRequest(Servant servant,  String text) {
        super(servant,null); //display 无返回值 不需要futureResult
        this.text = text;
    }

    @Override
    public void execute() {

        this.servant.displayString(text);

    }
}
