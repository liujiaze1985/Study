package com.ljz.concurrent.chapter18.active_object;

import com.ljz.concurrent.chapter18.result.MyResult;

/**
 * Copyright © 2019年12月21日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter18
 * @Description: 接受异步消息的主动对象
 * @author: liujiaze
 * @date: 2019年12月21日 16:19
 * @version: V1.0
 */
public interface ActiveObject {
    /**
     *
     * @param count 要把传入的char 拼成几个  例如传入 a  count是5 那就拼装成aaaaa
     * @param fillChar
     * @return
     */
    MyResult makeString(int count, char fillChar);

    void displayString(String text);
}
