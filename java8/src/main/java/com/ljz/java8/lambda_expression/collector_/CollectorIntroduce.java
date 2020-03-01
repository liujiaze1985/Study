package com.ljz.java8.lambda_expression.collector_;

import com.ljz.java8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Copyright © 2019年11月25日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description:
 *              学习重点：1. Reducing and summarizing stream elements to a single value
 *                       2. Grouping elements
 *                       3. Partitioning elements
 *                       4. user custom the Collector
 * @author: LiuJiaZe
 * @date: 2019年11月25日 14:51
 * @version: V1.0
 */
public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170),
                new Apple("green", 150),
                new Apple("yellow", 120),
                new Apple("green", 170)
        );

        //只拿绿的
        List<Apple> greenList = list.stream()
                .filter(a -> a.getColor().equals("green"))
                .collect(Collectors.toList()); //jdk所有的Collector实现全在Collectors中
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        //分组
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
        //
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);
    }


    /**
     * 根据颜色进行分组
     * @param apples
     * @return
     */
    private static Map<String, List<Apple>> groupByNormal(List<Apple> apples) {

        //创建一个map  key:颜色 ,value: list
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple a : apples) {
            List<Apple> list = map.get(a.getColor());
            if (null == list) {
                list = new ArrayList<>();
                map.put(a.getColor(), list);
            }
            list.add(a);
        }
        return map;
    }

    /**
     * 演化2 函数式编程
     * @param apples
     * @return
     */
    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {

        //创建一个map  key:颜色 ,value: list
        Map<String, List<Apple>> map = new HashMap<>(); //map 是final的
        apples.stream().forEach(a -> {
                    List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                                List<Apple> list = new ArrayList<>();
                                map.put(a.getColor(), list);
                                return list;
                            }
                    );
                    colorList.add(a);
                }
        );
        return map;
    }

    /**
     * 演化3 使用Collector
     * @param apples
     * @return
     */
    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
