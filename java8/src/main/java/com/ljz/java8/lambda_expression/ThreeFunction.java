package com.ljz.java8.lambda_expression;

/**
 * Copyright © 2019年11月20日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression
 * @Description: 三个以上参数需要自定义一个Function
 * @author: LiuJiaZe
 * @date: 2019年11月20日 18:37
 * @version: V1.0
 */
public interface ThreeFunction<T, U, K, R> {
    R apply(T t, U u, K k);
}
