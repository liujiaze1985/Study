package com.ljz.java8.lambda_expression;

import com.ljz.java8.Apple;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Copyright ? 2019��11��20�� LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019��11��20�� 11:53
 * @version: V1.0
 */
public class LambdaExpression {
    /**
     * lambda expression 1. �������� ���� �� չʾ һ���������� 2. ���Ա����ݣ� 3. �������
     */
    public static void main(String[] args) {
        /**
         * ����һ��Comparator ��Listȥsort
         *�Ƚ�����ƻ��
         */

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {

                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();
        list.sort(byColor);

        /**
         *  ʹ��lambda���ʽ
         *  ��parameters�� -> expression
         *  ��parameters�� -> {statments;}
         *  () ->{}
         *  () -> "hello" �ȼ��� () -> { return "hello" ;}
         *
         *  �Ƿ�����
         *  (Integer i ) -> return "Alex" + i  ��return ����Ҫ��{}
         *
         *
         *
         *  ��{} ��Ҫʹ��return
         *  ����{} return ��ʡ��
         *   Comparator<Apple> byColor2 = (o1, o2) -> {
         *       return o1.getColor().compareTo(o2.getColor());
         *   };
         *
         *   �����б�  (o1, o2)
         *   lambda body   (o1, o2) -> o1.getColor().compareTo(o2.getColor());
         */
        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        /**
         *��ȷ����
         */

        Function<String, Integer> flambda = s -> s.length();

        //        Predicate.test ���ص���һ��boolean ����
        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");
        /**
         *  (int x, int y ) -> {
         *      System.out.println(x);
         *      System.out.println(y);
         *  };
         *
         *  () -> 42;
         */
        Runnable r = () -> {
        };
    }
}
