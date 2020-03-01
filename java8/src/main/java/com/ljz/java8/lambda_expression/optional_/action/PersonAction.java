package com.ljz.java8.lambda_expression.optional_.action;

import java.util.Optional;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:00
 * @version: V1.0
 */
public class PersonAction {
    private Optional<CarAction> carAction;

    public PersonAction() {

    }

    public Optional<CarAction> getCarAction() {
        return carAction;
    }

    public void setCarAction(Optional<CarAction> carAction) {
        this.carAction = carAction;
    }
}
