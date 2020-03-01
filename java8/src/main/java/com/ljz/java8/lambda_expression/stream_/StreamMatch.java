package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Copyright © 2019年11月22日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月22日 13:05
 * @version: V1.0
 */
public class StreamMatch {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8};

        //        stream_<Integer> stream_ = Arrays.stream_(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        Stream<Integer> stream = Arrays.stream(integers);

        //allMatch 所有元素均满足条件
        boolean matched = stream.allMatch(i -> i > 10);
        System.out.println("allMatch=" + matched);
        //        assert matched : "some elements not matched.";

        //任意一个满足条件
        Stream<Integer> stream2 = Arrays.stream(integers);
        boolean b = stream2.anyMatch(i -> i > 6);
        System.out.println("anyMatch=" + b);
        //无满足条件
        Stream<Integer> stream3 = Arrays.stream(integers);
        boolean b1 = stream3.noneMatch(i -> i < 0);
        System.out.println("noneMatch=" + b1);
    }
}
