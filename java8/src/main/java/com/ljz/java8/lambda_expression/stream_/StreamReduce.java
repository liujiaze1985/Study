package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Copyright © 2019年11月23日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description: reduce 聚合
 * @author: LiuJiaZe
 * @date: 2019年11月23日 16:29
 * @version: V1.0
 */
public class StreamReduce {

    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8};
        Stream<Integer> stream = Arrays.stream(integers);

        //求和 初始值为0
        //1
        Integer result = stream.reduce(0, (i, j) -> i + j);
        System.out.println(result);
        //1.1
        stream = Arrays.stream(integers);
        Integer result2 = stream.reduce(0, Integer::sum);
        System.out.println(result2);

        //1.2
        stream = Arrays.stream(integers);
        //不给初始值
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);


        //1.3
        //先过滤再求和
        stream = Arrays.stream(integers);
        //只给偶数求和
        stream.filter(i -> i % 2 == 0).reduce((i, j) -> i + j).ifPresent(System.out::println);
        //使用Optional先过滤再求和 不给初始值
        stream = Arrays.stream(integers);
        //只给偶数求和
        Optional<Integer> reduce = stream.filter(i -> i % 2 == 0).reduce((i, j) -> i + j);
        Optional.of(reduce).ifPresent(System.out::println);

        //使用Optional先过滤再求和 给初始值
        stream = Arrays.stream(integers);
        //只给偶数求和
        int reduce1 = stream.filter(i -> i % 2 == 0).reduce(0, (i, j) -> i + j);
        Optional.of(reduce1).ifPresent(System.out::println);


        //2
        //取最大值
        stream = Arrays.stream(integers);
        stream.reduce((i, j) -> i > j ? i : j).ifPresent(System.out::println);
        stream = Arrays.stream(integers);
        stream.reduce(Integer::max).ifPresent(System.out::println);

        //取最小值
        stream = Arrays.stream(integers);
        stream.reduce((i, j) -> i > j ? j : i).ifPresent(System.out::println);
        stream = Arrays.stream(integers);
        stream.reduce(Integer::min).ifPresent(System.out::println);


    }
}
