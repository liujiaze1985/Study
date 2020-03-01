package com.ljz.java8.lambda_expression.collector_;

import com.ljz.java8.lambda_expression.stream_.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
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
public class CollectorsAction2 {
    public static void main(String[] args) {
        testGroupingByConcurrent();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithSuplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        testMapping();
        testMaxBy();
        testMinBy();
    }

    /**
     *
     */
    private static void testGroupingByConcurrent() {
        System.out.println("testGroupingByConcurrent");
        ConcurrentMap<Dish.Type, List<Dish>> listConcurrentMap = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(listConcurrentMap.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(listConcurrentMap).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * ConcurrentSkipListMap 跳表，结构类似红黑树，当数量到一定量级，操作速度高于红黑树
     */
    private static void testGroupingByConcurrentWithSuplierAndCollector() {
        System.out.println("testGroupingByConcurrentWithSuplierAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType,
                        ConcurrentSkipListMap::new,
                        Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);

    }


    /**
     *joining 参数是CharSequence
     */
    private static void testJoining() {
        System.out.println("testJoining");
        String collect = menu.stream().map(Dish::getName).collect(Collectors.joining());
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * 加分隔符
     */
    private static void testJoiningWithDelimiter() {
        System.out.println("testJoiningWithDelimiter");
        String collect = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     * 加分隔符和前缀，后缀
     */
    private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
        System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
        String collect = menu.stream().map(Dish::getName).collect(Collectors.joining(",", "name=[", "]"));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     *
     */
    private static void testMapping() {
        System.out.println("testMapping");
        //等价于 menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        String collect = menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(",")));
        Optional.ofNullable(collect).ifPresent(System.out::println);

    }

    /**
     *最大的
     */
    private static void testMaxBy() {
        System.out.println("testMaxBy");
        menu.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    /**
     *最小的
     */
    private static void testMinBy() {
        System.out.println("testMinBy");
        menu.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

    }





}
