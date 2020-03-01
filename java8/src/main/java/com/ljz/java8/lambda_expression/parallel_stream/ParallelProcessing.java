package com.ljz.java8.lambda_expression.parallel_stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Copyright © 2019年12月01日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.parallel_stream
 * @Description: 并行Stream操作  对1到10000000进行加法运算
 * 并非所有的Stream的方法都能使用并行
 *  Stream sources and decomposability
 *  Source              Decomposability（执行效率）
 *  ArrayList           Excellent         卓越
 *  LinkedList          Poor              很差
 *  IntStream.range     Excellent
 *  Stream.iterate      Poor
 *  HashSet             Good              优秀
 *  TreeSet             Good
 * @author: LiuJiaZe
 * @date: 2019年12月01日 15:56
 * @version: V1.0
 */
public class ParallelProcessing {
    public static void main(String[] args) {
        //设置全属参数，改变默认使用cpu的数量，会影响整个fork join
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallellism", "12");
        System.out.println(Runtime.getRuntime().availableProcessors()); //查看cpu核数
        System.out.println("The best process time (normalAdd) => " + measureSumPerformance(ParallelProcessing::normalAdd,
                100_000_000));
        //        System.out.println("The best process time (iterateStream)=> " + measureSumPerformance(ParallelProcessing::iterateStream,
        //                10_000_000));
        //        System.out.println("The best process time (parallelStream) => " + measureSumPerformance(ParallelProcessing::parallelStream,
        //                10_000_000));
        //        System.out.println("The best process time (parallelStream2) => " + measureSumPerformance(ParallelProcessing::parallelStream2,
        //                10_000_000));
        System.out.println("The best process time (parallelStream3) => " + measureSumPerformance(ParallelProcessing::parallelStream3,
                1000_000_000));


    }

    /**
     * 测量器： 测量哪个方法快
     * @param adder
     * @param limit
     * @return
     */
    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            //   System.out.println("The result of sum =>" + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;

    }

    /**
     * 串行
     *
     * @param limit
     * @return
     */
    private static long iterateStream(long limit) {
        Long result = Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
        return result;

    }

    /**
     * 并行
     *  Stream.iterate(1L, i -> i + 1) 返回的是Object 拆装箱很慢
     * @param limit
     * @return
     */
    private static long parallelStream(long limit) {
        Long result = Stream.iterate(1L, i -> i + 1).parallel().limit(limit).reduce(0L, Long::sum);
        return result;

    }

    /**
     * 显示拆箱
     * @param limit
     * @return
     */
    private static long parallelStream2(long limit) {
        Long result = Stream.iterate(1L, i -> i + 1)
                .mapToLong(Long::longValue)
                .parallel()
                .limit(limit)
                .reduce(0L, Long::sum);
        return result;


    }

    /**
     * 不使用stream
     * @param limit
     * @return
     */
    private static long parallelStream3(long limit) {
        //        return LongStream.rangeClosed(1, limit).parallel().sum();
        return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);

    }

    private static long normalAdd(long limit) {
        long result = 0L;
        for (long i = 1L; i < limit; i++) {
            result += i;
        }
        return result;
    }




}
