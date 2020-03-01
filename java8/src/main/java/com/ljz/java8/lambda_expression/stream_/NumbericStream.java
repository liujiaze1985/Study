package com.ljz.java8.lambda_expression.stream_;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Copyright © 2019年11月23日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description: 如果有具体的元数据类型，不要使用object的方式 会节省很多堆内存空间
 * @author: LiuJiaZe
 * @date: 2019年11月23日 16:56
 * @version: V1.0
 */
public class NumbericStream {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7};
        Stream<Integer> stream = Arrays.stream(integers);
        //取大于3的 ，再求和
        /**
         * stream_.filter(i -> i.intValue() > 3) 返回的是integerStream
         * integerStream 方便使用拆箱后的结果
         * int 4byte/32bit
         */
        Stream<Integer> integerStream = stream.filter(i -> i.intValue() > 3);
        //integerStream.reduce(0, Integer::sum) 操作的数据类型是integer 内存占用 远大于int类型
        Integer result = integerStream.reduce(0, Integer::sum);
        System.out.println(result);


        //使用int类型
        stream = Arrays.stream(integers);
        //intStream 使用int数据类型 每个元素占用空间是4byte
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        int sum = intStream.filter(i -> i > 3).sum();
        System.out.println(sum);
        //等价于        intStream.filter(i->i>3).reduce(0,(i,j)->i+j);

        System.out.println("====================");
        box();

    }

    private static void box() {

        //给一个数 int a= 9 ，从1-1000中，哪个值和a ，可以满足 勾股定律
        int a = 9;

        //rangeClosed  定义一个int 类型的stream 元素是1到100

        //        IntStream intStream = IntStream.rangeClosed(1, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0);
        //        intStream.forEach(System.out::println);
        //  封箱 boxed() 返回Int 数组 使用boxed
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        //  使用 mapToObj
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(r -> System.out.println("a=" + r[0] + ",b=" + r[1] + ",c=" + r[2]));


    }


}
