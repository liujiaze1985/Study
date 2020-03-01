package com.ljz.java8.lambda_expression.collector_;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Copyright © 2019年12月01日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description: 自定义Collector使用
 * @author: LiuJiaZe
 * @date: 2019年12月01日 15:34
 * @version: V1.0
 */
public class CustomerCollectorAction {
    public static void main(String[] args) {
        /**
         * String 数组
         * filter
         * toList
         */
        Collector<String, List<String>, List<String>> collector = new ToListCollector<>();
        String[] arrs = new String[]{"Alex", "Wang", "Hello", "Lambda", "Collector", "Java 8", "stream"};
//        List<String> result = Arrays.stream(arrs).filter(s -> s.length() >= 5).collect(collector);
//        System.out.println(result);

        //使用并行 parallelStream
        List<String> result2 = Arrays.asList("Alex", "Wang", "Hello", "Lambda", "Collector", "Java 8", "stream")
                .parallelStream()
                .filter(s -> s.length() >= 5)
                .collect(collector);
        System.out.println(result2);

    }
}
