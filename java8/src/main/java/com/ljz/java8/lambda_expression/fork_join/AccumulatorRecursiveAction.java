package com.ljz.java8.lambda_expression.fork_join;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.fork_join
 * @Description: RecursiveAction 无返回值
 * @author: LiuJiaZe
 * @date: 2019年12月02日 13:24
 * @version: V1.0
 */
public class AccumulatorRecursiveAction extends RecursiveAction {
    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    /**
     * 计算
     * @return
     */
    @Override
    protected void compute() {
        //分而治之 判断下标是否大于LIMIT
        if ((end - start) < LIMIT) {
            for (int i = start; i < end; i++) {
                AccumulatorHelper.accumulate(data[i]);
            }
        } else {
            //计算中间变量 再定义一个Recursive 相当于拆为两个任务
            int mid = (start + end) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }


    /**
     * getResult,rest 方法可以定义为包可见
     */
    static class AccumulatorHelper {
        private static final AtomicInteger result = new AtomicInteger(0);

        public static void accumulate(int value) {
            result.getAndAdd(value);
        }


        public static int getResult() {
           return result.get();
        }


        public void rest() {
            result.set(0);
        }
    }
}
