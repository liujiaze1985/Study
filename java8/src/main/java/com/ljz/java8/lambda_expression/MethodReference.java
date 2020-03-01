package com.ljz.java8.lambda_expression;

import com.ljz.java8.Apple;
import com.ljz.java8.ComplexApple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Copyright © 2019年11月20日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression
 * @Description: 方法推导 使用情景：1.静态方法 2. 实例方法 3. 对象方法 4. 构造函数
 * @author: LiuJiaZe
 * @date: 2019年11月20日 17:58
 * @version: V1.0
 */
public class MethodReference {
    public static void main(String[] args) {
        Consumer<String> consumer = (s) -> System.out.println(s);
        useConsumer(consumer, "Hello Alex");
        //使用函数推导的方式 例1
        useConsumer(System.out::println, "Hello Alex");
        //例2
        List<Apple> list = Arrays.asList(new Apple("green", 150), new Apple("yellow", 180), new Apple("red", 120));
        System.out.println(list);
        //使用匿名内部类
        list.sort(new Comparator<Apple>() {
                      @Override
                      public int compare(Apple o1, Apple o2) {
                          return o1.getColor().compareTo(o2.getColor());
                      }
                  }
        );
        System.out.println("list 排序2  匿名内部类=" + list);
        List<Apple> list1 = Arrays.asList(new Apple("yellow", 150), new Apple("green", 180), new Apple("red", 120));
        list1.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(list1);


        List<Apple> list2 = Arrays.asList(new Apple("green", 420), new Apple("yellow", 380), new Apple("red", 100));
        list2.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("list 排序2 =" + list2);


        list.stream().forEach(a -> System.out.println(a));
        System.out.println("++++++++++++++++++++++++++++++++++++++====");
        list.stream().forEach(System.out::println);


        //方法推导使用场景
        //1 类中的静态方法
        int value = Integer.parseInt("123");
        Function<String, Integer> f = Integer::parseInt;
        Integer result = f.apply("123");
        System.out.println("方法推导 类中的静态方法 = " + result);
        //2 实例方法
        BiFunction<String, Integer, Character> f2 = String::charAt;
        Character c = f2.apply("hello", 2);
        System.out.println("方法推导 类中实例方法 = " + c);

        //3 对象方法
        String world = new String("world");

        Function<Integer, Character> f3 = world::charAt;
        Character d = f3.apply(0);
        System.out.println("方法推导 对象方法 = " + d);
        //3 构造函数
        Supplier<String> stringSupplier = String::new;
        String s = stringSupplier.get();
        System.out.println("方法推导 构造函数 = " + s);
        //3.1 构造函数中有两个参数
        BiFunction<String, Long, Apple> appleBiFunction = Apple::new;
        Apple red = appleBiFunction.apply("red", 200L);
        System.out.println("方法推导 构造函数 有两个参数 = " + red);
        //3.2 构造函数中有3个参数 需要自定义一个function
        ThreeFunction<String, String, Long, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("green", "红富士", 150l);
        System.out.println("方法推导 构造函数 有三个参数 = " + complexApple);

    }

    private static <T> void useConsumer(Consumer<T> consumer, T t) {

        consumer.accept(t);
        consumer.accept(t);

    }
}
