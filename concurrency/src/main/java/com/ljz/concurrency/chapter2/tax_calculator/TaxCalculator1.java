package com.ljz.concurrency.chapter2.tax_calculator;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 税务计算器
 *               随国家税务政策影响
 *               v1.1 使用jdk1.8方式改造
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:20
 * @version: V1.0
 */
public class TaxCalculator1 {
    //工资
    private final double salary;
    //奖金
    private final double bonus;
    private final CalculatorStrategy calculatorStrategy;


    public TaxCalculator1(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;

        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return calcTax();
    }
}
