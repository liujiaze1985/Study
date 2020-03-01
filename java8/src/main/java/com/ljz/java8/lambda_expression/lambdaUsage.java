package com.ljz.java8.lambda_expression;

import com.ljz.java8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Copyright © 2019年11月20日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression
 * @Description: 本节学习重点: Predicate boolean test(T t); 诊断 / Consumer accept(T t); / Function<T ,R > R apply(T t); 传入T输出
 * R/ Supplier<T> T get() 供给;
 * @author: LiuJiaZe
 * @date: 2019年11月20日 13:25
 * @version: V1.0
 */
public class lambdaUsage {

    //************************Predicate********************************

    private static List<Apple> filter(List<Apple> sources, Predicate<Apple> applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : sources) {
            if (applePredicate.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    //************************LongPredicate********************************
    private static List<Apple> filterByWeight(List<Apple> sources, LongPredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : sources) {
            if (applePredicate.test(a.getWeight())) {
                result.add(a);
            }
        }
        return result;
    }


    //************************BiPredicate********************************
    private static List<Apple> filterByBiPredicate(List<Apple> sources, BiPredicate<String, Long> applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : sources) {
            if (applePredicate.test(a.getColor(), a.getWeight())) {
                result.add(a);
            }
        }
        return result;
    }


    //************************Consumer********************************
    private static void simpleTestConsumer(List<Apple> sources, Consumer<Apple> consumer) {
        for (Apple a : sources) {
            consumer.accept(a);
        }
    }

    //************************BiConsumer********************************
    private static void simpleTestBiConsumer(String c, List<Apple> sources, BiConsumer<Apple, String> consumer) {
        for (Apple a : sources) {
            consumer.accept(a, c);
        }
    }


    //************************Function********************************
    private static String testFunction(Apple apple, Function<Apple, String> function) {

        return function.apply(apple);
    }

    //************************BiFunction********************************
    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> biFunction) {

        return biFunction.apply(color, weight);
    }


    //使用Supplier 通过定义方法的方式
    private static Apple createApple(Supplier<Apple> appleSupplier) {
        return appleSupplier.get();
    }


    public static void main(String[] args) {
      /*  Runnable r1 = () -> System.out.println("Hello");
        Runnable r2 = new Runnable() {

            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello"));*/

        List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150), new Apple("yellow", 170));
        //使用Predicate
        List<Apple> greenList = filter(list, (apple) -> apple.getColor().equals("green"));
        System.out.println("greenList= " + greenList);
        //使用LongPredicate
        List<Apple> filterByWeightResult = filterByWeight(list, w -> w > 100);
        System.out.println("filterByWeightResult= " + filterByWeightResult);
        //使用BiPredicate
        List<Apple> filterByBiPredicateResult = filterByBiPredicate(list, (s, w) -> s.equals("green") && w > 100);
        System.out.println("filterByBiPredicateResult= " + filterByBiPredicateResult);

        //使用Consumer
        simpleTestConsumer(list, a -> System.out.println("使用 Consumer= " + a));
        //使用BiConsumer
        System.out.println("===========使用BiConsumer============");
        simpleTestBiConsumer("XXX", list, (a, s) -> System.out.println(s + a.getColor() + ":Weight" + a.getWeight()));

        //使用Function
        String testFunctionResult = testFunction(new Apple("blue", 100), a -> a.toString());
        System.out.println("使用Function= " + testFunctionResult);

        //使用IntFunction
        IntFunction<Double> f = i -> i * 100.0d;
        double result4 = f.apply(10);
        System.out.println("===========使用Function============" + result4);

        //使用BiFunction
        Apple testBiFunction = testBiFunction("blue", 130, (s, w) -> new Apple(s, w));
        System.out.println("===========使用BiFunction============" + testBiFunction);

        //使用Supplier
        Supplier<String> s = String::new; // method inference. 函数推导
        System.out.println("===========使用Supplier============" + s.get().getClass());

        Apple createAppleResult = createApple(() -> new Apple("black", 200));
        System.out.println("===========定义函数使用Supplier============" + createAppleResult);

    }

    private static void process(Runnable r) {
        r.run();
    }

    //*****************************FunctionalInterface*******************************************

    @FunctionalInterface
    public interface Adder {
        int add(int a, int b);
    }

    public interface SmartAdder extends Adder {
        long add(long a, long b);
    }

    @FunctionalInterface
    public interface DoNothing extends Adder {
    }

    @FunctionalInterface
    public interface Empty extends Adder {
    }
}
