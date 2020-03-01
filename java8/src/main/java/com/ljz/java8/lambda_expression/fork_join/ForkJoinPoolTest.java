package com.ljz.java8.lambda_expression.fork_join;

import java.util.concurrent.ForkJoinPool;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.fork_join
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月02日 13:20
 * @version: V1.0
 */
public class ForkJoinPoolTest {
    //定义一个数组 ，将数组的内容全加起来
    private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        System.out.println("result =>" + calc());

        //通过RecursiveTask方式计算
        AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length, data);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Integer result = forkJoinPool.invoke(task);
        System.out.println("AccumulatorRecursiveTask >>"+result);

        //通过RecursiveAction方式计算
        AccumulatorRecursiveAction task1 = new AccumulatorRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(task1);
        System.out.println("AccumulatorRecursiveAction >>"+AccumulatorRecursiveAction.AccumulatorHelper.getResult());

    }

    private static int calc() {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }
}
