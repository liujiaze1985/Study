package com.ljz.concurrent.chapter7;

/**
 * Copyright © 2019年12月16日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月16日 18:24
 * @version: V1.0
 */
public final class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
/**
 * 该类是一个线程安全的类，无修改的接口
 *  Person(final String name,final  String address)
 *  final 参数在jdk1.7之前，是放在常量池中，提升索引速度
 *  1.7以后 不加final 也会将其放入常量池
 */