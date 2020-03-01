package com.ljz.java8.lambda_expression.collector_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Copyright © 2019年12月01日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description: 自定义Collector
 * @author: LiuJiaZe
 * @date: 2019年12月01日 15:10
 * @version: V1.0
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(final String log) {
        System.out.println(Thread.currentThread().getName()+"-"+log);
    }
    /**
     * Collector<T, A, R>
     * T： Stream 元素的类型
     * A： supplier 的类型
     * R： 返回的类型
     */

    /**
     * 返回一个容器
     * @return
     */
    @Override
    public Supplier<List<T>> supplier() {
        log("supplier");
        return ArrayList::new;
    }

    /**
     * 累加器
     * @return
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator");
        return List::add;
    }

    /**
     * 两个参数
     * 并行操作
     * 注意：Supplier返回的是一个可变的容器
     * @return
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        log("combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 最终返回的R
     * @return
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        //return t->t ;
        return Function.identity();
    }

    /**
     * 设置特征，
     * 不可修改 : Characteristics.IDENTITY_FINISH
     * 支持并行 ：Characteristics.CONCURRENT
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
