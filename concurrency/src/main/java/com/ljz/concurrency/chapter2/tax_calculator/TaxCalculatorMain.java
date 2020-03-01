package com.ljz.concurrency.chapter2.tax_calculator;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月05日 16:23
 * @version: V1.0
 */
public class TaxCalculatorMain {
    public static void main(String[] args) {
        //        demonstration1();

        //        demonstration2();
        //        demonstration3();
        demonstration4();

    }

    /**
     * 使用jdk8新特性改造
     * 修改TaxCalculator构造函数
     */
    private static void demonstration4() {
        TaxCalculator1 calculator = new TaxCalculator1(10000d, 2000d, (s, b) -> s * 0.1 + b * 0.2);
        System.out.println(calculator.calculate());
    }

    /**
     * 使用jdk8新特性改造
     */
    private static void demonstration3() {
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d);
        calculator.setCalculatorStrategy((s, b) -> s * 0.1 + b * 0.2);
        System.out.println(calculator.calculate());
    }

    /**
     * 使用策略模式
     */
    private static void demonstration2() {
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d);
        CalculatorStrategy strategy = new SimpleCalculatorStrategy();
        calculator.setCalculatorStrategy(strategy);
        System.out.println(calculator.calculate());
    }

    /**
     * 第一版 重写calcTax 方法
     */
    private static void demonstration1() {
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d) {
            @Override
            public double calcTax() {
                //工资乘以10%
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculate();
        System.out.println(tax);
    }
}
