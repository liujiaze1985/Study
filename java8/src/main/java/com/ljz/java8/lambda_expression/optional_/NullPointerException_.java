package com.ljz.java8.lambda_expression.optional_;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description: 传统方式不可避免会出现空指针异常
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:03
 * @version: V1.0
 */
public class NullPointerException_ {
    public static void main(String[] args) {

        //传统方式可能会出现空指针异常
        //        String insuaceName = getInsuaceName(new PersonAction());
        //返回未知
        String result = getInsuaceNameByDeepDoubts(new Person());
        System.out.println(result);
    }

    /**
     * 嵌套深的话判断比较复杂
     * @param person
     * @return
     */
    private static String getInsuaceNameByDeepDoubts(Person person) {
        if (null != person) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName(); //返回未知
                }
            }
        }
        return "UNKNOWN";
    }

    private static String getInsuaceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    /**
     * 多个返回避免嵌套判断
     * @param person
     * @return
     */
    private static String getInsuaceNameByMultExit(Person person) {
        final String defaultValue = "UNKNOWN";
        if (null == person) {
            return defaultValue;
        }
        Car car = person.getCar();
        if (null == car) {
            return defaultValue;
        }
        Insurance insurance = car.getInsurance();
        if (null == insurance) {
            return defaultValue;
        }
        return insurance.getName();

    }

}
