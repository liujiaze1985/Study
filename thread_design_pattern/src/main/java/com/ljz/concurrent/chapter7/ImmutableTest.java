package com.ljz.concurrent.chapter7;

import java.util.Collections;
import java.util.List;

/**
 * Copyright © 2019年12月17日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月17日 10:34
 * @version: V1.0
 */
public class ImmutableTest {
    private final int age;
    private final String name;
    private final List<String> list;

    public ImmutableTest(int age, String name, List<String> list) {
        this.age = age;
        this.name = name;
        this.list = list;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    /**
     * 返回的list 不想被修改，可以有两种方案：
     * 1。返回一个不可被修改的list Collections.unmodifiableList(list);
     * 2。克隆一个副本，返回给调用者
     * @return
     */
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }



}
