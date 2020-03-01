package com.ljz.java8.lambda_expression.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.fork_join
 * @Description: Accumulator 累加 Recursive递归
 * @author: LiuJiaZe
 * @date: 2019年12月02日 13:24
 * @version: V1.0
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {
    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    /**
     * 计算
     * @return
     */
    @Override
    protected Integer compute() {
        //分而治之 判断下标是否大于LIMIT
        if ((end - start) < LIMIT) {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;

        }
        //计算中间变量 再定义一个Recursive 相当于拆为两个任务
        int mid = (start + end) / 2;

        AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
        AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
        left.fork();
        //计算右边结果
        Integer rightResult = right.compute();

        Integer leftResult = left.join();
        return rightResult + leftResult;
    }
}
