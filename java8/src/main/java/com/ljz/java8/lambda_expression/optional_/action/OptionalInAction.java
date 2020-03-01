package com.ljz.java8.lambda_expression.optional_.action;

import com.ljz.java8.lambda_expression.optional_.Insurance;

import java.util.Optional;

/**
 * Copyright © 2019年11月25日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_.action
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月25日 14:26
 * @version: V1.0
 */
public class OptionalInAction {
    public static void main(String[] args) {
        //1 传一个null
        System.out.println(getInsuranceNameByOptional(null));

        //用Optional封装 判断是否存在，存在的话打印
        Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);

    }

    private static String getInsuranceNameByOptional(PersonAction personAction) {
        //Optional.ofNullable(personAction)
        //.map(PersonAction::getCarAction) 返回的是一个  Optional<Optional<Car>>
        //.map(Car::getInsurance)不能继续用来执行

        //使用flatMap 返回的是一个Optional<Car>


        return Optional.ofNullable(personAction)
                .flatMap(PersonAction::getCarAction)
                .flatMap(CarAction::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }
}
