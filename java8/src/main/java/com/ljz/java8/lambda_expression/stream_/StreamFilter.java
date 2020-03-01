package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年11月22日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description: stream_ Filter 方法的使用
 * @author: LiuJiaZe
 * @date: 2019年11月22日 12:34
 * @version: V1.0
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 1);
        //拿出其中的偶数
        List<Integer> result = list.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println(result);

        //去重
        result = list.stream().distinct().collect(toList());
        System.out.println(result);
        //跳过前五个
        result = list.stream().skip(5).collect(toList());
        System.out.println(result);
        //只取前五个
        result = list.stream().limit(5).collect(toList());
        System.out.println(result);


    }

}
