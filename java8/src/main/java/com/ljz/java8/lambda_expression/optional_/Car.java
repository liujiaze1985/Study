package com.ljz.java8.lambda_expression.optional_;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:01
 * @version: V1.0
 */
public class Car {
    private Insurance insurance;

    public Car(Insurance insurance) {
        this.insurance = insurance;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
