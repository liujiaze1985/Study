package com.ljz.java8.lambda_expression.collector_;

import com.ljz.java8.lambda_expression.stream_.Dish;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.ljz.java8.lambda_expression.collector_.CollectorsAction.menu;

/**
 * Copyright © 2019年11月29日 LiuJiaZe. All rights reserve.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月29日 20:42
 * @version: V1.0
 */
public class CollectorsAction3 {
    public static void main(String[] args) {

        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollector();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
    }

    /**
     * 分组，true 和 false两个组
     * 返回一个map key为boolean类型
     */
    private static void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        //根据卡路里大小分组
        Optional.ofNullable(menu.stream().map(Dish::getCalories).collect(Collectors.partitioningBy(a -> a > 150)))
                .ifPresent(System.out::println);
        //根据是否水果分组
        Optional.ofNullable(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian)))
                .ifPresent(System.out::println);

    }

    /**
     * 分组完的结果 ，交给下个方法处理
     */
    private static void testPartitioningByWithPredicateAndCollector() {
        System.out.println("testPartitioningByWithPredicateAndCollector");
        //根据是否水果分组,再求卡路里平均值
        Map<Boolean, Double> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * 聚合 求最大
     * BinaryOperator minBy  maxBy
     */
    private static void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        menu.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     *求总和
     */
    private static void testReducingBinaryOperatorAndIdentity() {
        System.out.println("testReducingBinaryOperatorAndIdentity");
        Integer collect = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     *求总和
     * Collector<T, ?, U> reducing(U identity,
     * Function<? super T, ? extends U> mapper, 参流进行操作
     * BinaryOperator<U> op)
     */
    private static void testReducingBinaryOperatorAndIdentityAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentityAndFunction");
        Integer collect = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    private static void testSummarizingLong() {
        System.out.println("testSummarizingLong");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

    }


}
