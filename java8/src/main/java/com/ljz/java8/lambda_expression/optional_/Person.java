package com.ljz.java8.lambda_expression.optional_;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:00
 * @version: V1.0
 */
public class Person {
    private Car car;

    public Person() {

    }

    public Person(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
