package com.ljz.java8.lambda_expression.optional_.action;

import com.ljz.java8.lambda_expression.optional_.Insurance;

import java.util.Optional;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:01
 * @version: V1.0
 */
public class CarAction {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
