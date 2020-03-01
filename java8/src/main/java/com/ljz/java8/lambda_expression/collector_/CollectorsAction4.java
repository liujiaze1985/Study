package com.ljz.java8.lambda_expression.collector_;

import com.ljz.java8.lambda_expression.stream_.Dish;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.ljz.java8.lambda_expression.collector_.CollectorsAction.menu;

/**
 * Copyright © 2019年11月29日 LiuJiaZe. All rights reserve.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.collector_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月29日 20:42
 * @version: V1.0
 */
public class CollectorsAction4 {
    public static void main(String[] args) {
        testSummarizingDouble();
        testSummarizingLong();
        testSummarizingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
        testTotMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
    }

    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.ofNullable(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);

    }

    private static void testSummarizingLong() {
        System.out.println("testSummarizingLong");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);

    }

    private static void testToCollection() {
        System.out.println("testToCollection");
        //拿出其中的一部分，再生成一个list
        Optional.ofNullable(menu.stream()
                .filter(d -> d.getCalories() > 600)
                .collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);

    }

    /**
     * 拿出名字做key,卡路里做value
     */
    private static void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

    }

    /**
     * merge Type:total
     *  Collector<T, ?, ConcurrentMap<K,U>>
     *  toConcurrentMap(Function<? super T, ? extends K> keyMapper,
     *  Function<? super T, ? extends U> valueMapper,
     *  BinaryOperator<U> mergeFunction)
     */
    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        Optional.ofNullable(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v);
                });

    }

    /**
     *   public static <T, K, U, M extends ConcurrentMap<K, U>>
     *   Collector<T, ?, M> toConcurrentMap(Function<? super T, ? extends K> keyMapper,
     *   Function<? super T, ? extends U> valueMapper,
     *   BinaryOperator<U> mergeFunction,
     *   Supplier<M> mapSupplier)
     */
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getType,
                        v -> 1L,
                        (a, b) -> a + b,
                        ConcurrentSkipListMap::new))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });

    }

    private static void testToList() {
        System.out.println("testToList");
        Optional.ofNullable(menu.stream().collect(Collectors.toList())).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });

    }

    private static void testToSet() {
        System.out.println("testToSet");
        Optional.ofNullable(menu.stream().collect(Collectors.toSet())).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });

    }

    private static void testTotMap() {
        System.out.println("testTotMap");
        Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories))).ifPresent(v -> {
            System.out.println(v);
            System.out.println(v.getClass());
        });

    }

    private static void testToMapWithBinaryOperator() {
        System.out.println("testToMapWithBinaryOperator");
        Optional.ofNullable(menu.stream().collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v);
                });

    }

    private static void testToMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });

    }

}
