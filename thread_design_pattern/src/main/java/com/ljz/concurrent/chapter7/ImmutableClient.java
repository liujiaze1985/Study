package com.ljz.concurrent.chapter7;

import java.util.stream.IntStream;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 18:30
 * @version: V1.0
 */
public class ImmutableClient {
    public static void main(String[] args) {
        //share data
        Person person = new Person("Alex", "GanSu");

        IntStream.range(0, 5).forEach(i -> new UsePersonThread(person).start());


    }
}
