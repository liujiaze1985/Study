package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年11月22日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月22日 12:42
 * @version: V1.0
 */
public class StreamMap {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1);

        //元素增大两倍
        List<Integer> result = list.stream().map(i -> i * 2).collect(toList());
        System.out.println(result);

        //只取名字
        //1
        listDish().stream().map(d -> d.getName()).forEach(System.out::println);
        //2
        List<String> dishs = listDish().stream().map(d -> d.getName()).collect(toList());
        System.out.println(dishs);

        //flat map 扁平化 作用:需要一个function 返回一个stream
        //需求 字符串数组 ，借助stream 将重复的chart去掉
        String[] words = {"Hello", "World"};
        //返回的是两个字符串数组{H,e,l,l,o},{W,o,r,l,d}
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));// stream_<String[]>
        //将多个数组合并（扁平化）为一个数组{H,e,l,l,o,W,o,r,l,d}
        Stream<String> stringStream = stream.flatMap(Arrays::stream);
        stringStream.distinct().forEach(System.out::println);

    }

    private static List<Dish> listDish() {

        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        return menu;
    }
}
