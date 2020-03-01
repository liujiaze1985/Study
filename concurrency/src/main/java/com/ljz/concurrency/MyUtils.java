package com.ljz.concurrency;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.util
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月02日 18:38
 * @version: V1.0
 */
public class MyUtils {
    public static void getMethds(Class classz) {
        Class<?> t = classz;
        Method[] methods = t.getMethods();
        //获取方法列表
        //Optional.of(Arrays.asList(methods).stream().map(Method::getName).distinct().collect(toList()))
        //        .ifPresent(System.out::println);
        Arrays.asList(methods).stream().forEach(System.out::println);
    }


}
