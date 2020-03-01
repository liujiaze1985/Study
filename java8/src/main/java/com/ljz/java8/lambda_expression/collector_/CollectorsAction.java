package com.ljz.java8.lambda_expression.collector_;

import com.ljz.java8.lambda_expression.stream_.Dish;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Copyright © 2019年11月25日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月25日 15:20
 * @version: V1.0
 */
public class CollectorsAction {

    //定义全局静态变量 final 表示被动加载 CollectorsAction不会被主动初始化，去掉final 会变为主动加载
    public final static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        //reduce
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();

        testCounting();

        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();

        //Summarizing
        testSummarizingInt();


    }

    /**
     * reduce的功能，
     * 传入T，返回Double
     */
    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    /**
     * reduce的功能，
     * 传入T，返回Double
     */
    private static void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    /**
     * reduce的功能，
     * 传入T，返回Double
     */
    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    /**
     * reduce的功能，
     * 对第一个参数的的执行结果，执行参数2的方法
     */
    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream()
                        .collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories),
                                        a -> "The Average Calories is -> " + a
                                )
                        )
        ).ifPresent(System.out::println);
        //Collections::unmodifiableList 设置不可修改属性
        Optional.ofNullable(menu.stream()
                        .filter(d -> d.getType().equals(Dish.Type.MEAT))
                        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList))
        ).ifPresent(System.out::println);
    }

    /**
     * reduce的功能，
     *
     */
    private static void testCounting() {
        System.out.println("testCounting");
        //Collectors.counting()返回的是Collector 可为后续服务
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        Optional.of(menu.stream().count()).ifPresent(System.out::println);

    }

    /**
     * 分组
     */
    private static void testGroupingByFunction() {
        System.out.println("testGroupingByFunction");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);

    }

    /**
     * 每个类型对应多少个个数
     */
    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);
    }

    /**
     * 传入一个map ，返回指定map类型
     */
    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByFunctionAndSupplierAndCollector");
        Optional.of(menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                        TreeMap::new,
                                        Collectors.averagingDouble(Dish::getCalories)
                                )
                        )
        ).ifPresent(System.out::println);


        TreeMap<Dish.Type, Double> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                                TreeMap::new,
                                Collectors.averagingDouble(Dish::getCalories)
                        )
                );
        System.out.println(collect.getClass());
    }


    //summarizing
    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");

        IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.of(result).ifPresent(System.out::println);

    }


}
