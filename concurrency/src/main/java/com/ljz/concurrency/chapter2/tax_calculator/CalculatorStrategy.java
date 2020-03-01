package com.ljz.concurrency.chapter2.tax_calculator;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 策略
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:29
 * @version: V1.0
 */
@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary, double bonus);
}
