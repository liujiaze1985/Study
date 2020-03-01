package com.ljz.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ljz on 2019/11/17.
 */
public class FilterApple {
    /**
     * 使用策略
     */
    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * 常规方法
     */
    public static List<Apple> findGreenApple(List<Apple> apples) {
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        List<Apple> list = Arrays.asList(new Apple("green", 150),
                new Apple("red", 170),
                new Apple("yellow", 120),
                new Apple("green", 180)
        );
        //使用常规方法调用
        List<Apple> greenApples = findGreenApple(list);
        //        assert greenApples.size() == 3; 断言没有起作用
        //        System.out.println(greenApples.size());
        //使用策略模式方法调用
        List<Apple> greenApples_1 = findApple(list, new GreenAnd150WeightFilter());
        System.out.println(greenApples_1);
        List<Apple> yellowApples_1 = findApple(list, new YellowAnd120WeightFilter());
        System.out.println(yellowApples_1);
        //使用策略模式 以匿名内部类的方式
        List<Apple> redApples = findApple(list, new AppleFilter() {
                    @Override
                    public boolean filter(Apple apple) {
                        return (apple.getColor().equals("red") && apple.getWeight() >= 170);
                    }
                }
        );
        System.out.println(redApples);


        /**
         * 接口只有一个方法时可以使用lambda
         * 表达式只有一个参数时可以进行类型推导
         * List<Apple> lambdaResult = findApple(list, ( apple) ->
         * List<Apple> lambdaResult = findApple(list,  apple ->
         */
        List<Apple> lambdaResult = findApple(list, (Apple apple) -> {
                    return apple.getColor().equals("green");
                }
        );

        System.out.println("lambdaResult=" + lambdaResult);

    }

    /**
     * 策略模式 需要不断的添加实现类来满足新需求 可以使用匿名内部类的方式来简化 使用lambda表达式在jdk8中比匿名内部类方式节省JVM空间
     * FunctionalInterface 一个接口只有一个方法，那这个接口就是个FunctionalInterface
     */
    @FunctionalInterface
    public interface AppleFilter {
        boolean filter(Apple apple);
    }


    //    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * 实现接口
     */
    public static class GreenAnd150WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green") && apple.getWeight() >= 150);
        }
    }

    public static class YellowAnd120WeightFilter implements AppleFilter {

        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow") && apple.getWeight() >= 120);
        }
    }


}
