package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Copyright © 2019年11月22日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月22日 13:13
 * @version: V1.0
 */
public class StreamFind {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8};
        //过滤奇数
        Stream<Integer> stream = Arrays.stream(integers);
        Optional<Integer> optional1 = stream.filter(i -> i % 2 == 0).findAny();
        System.out.println(optional1.get());


        Stream<Integer> stream2 = Arrays.stream(integers);
        Optional<Integer> optional2 = stream2.filter(i -> i % 2 == 0).findFirst();
        optional2.ifPresent(System.out::println);


        //findAny 的优势 生成的Optional 可以避免空指针异常
        Stream<Integer> stream3 = Arrays.stream(integers);
        Optional<Integer> optional3 = stream3.filter(i -> i > 10).findAny();
        //条件满足返回，不满足返回-1
        System.out.println(optional3.orElse(-1));


        //自定义方法 可能会出现空指针
        int result = find(integers, 19, i -> i > 100);
        //-1代表没有找到
        System.out.println(result);


    }

    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
        for (int i : values) {
            if (predicate.test(i)) {
                return i;
            }

        }
        return defaultValue;

    }
}
