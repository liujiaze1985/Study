package com.ljz.concurrency.chapter2.tax_calculator;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: CalculatorStrategy 的简单实现
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:29
 * @version: V1.0
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy{
    private final static double SALARY_DATE = 0.1;
    private final static double BONUS_DATE = 0.1;

    @Override
    public double calculate(double salary, double bonus) {
        return   salary * SALARY_DATE +  bonus * BONUS_DATE;
    }
}
