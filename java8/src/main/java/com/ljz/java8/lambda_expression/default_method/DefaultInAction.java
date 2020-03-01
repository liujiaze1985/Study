package com.ljz.java8.lambda_expression.default_method;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.default_method
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月02日 17:00
 * @version: V1.0
 */
public class DefaultInAction {

    /**
     * 如何解决多重继承带来的冲突
     * @param args
     */
    public static void main(String[] args) {
        //        demonstration();
        //        demonstration1();
        //优先级 class > sub-interface > 如果选择还是混淆的，必须重写该方法

    }

    private static void demonstration1() {
        //方法重载的时候，如果参数类型不明确，会优先匹配方法类型已指定的重载方法，最优路径原则

        DefaultInAction defaultInAction = new DefaultInAction();
        defaultInAction.confuse(null); //会调用confuse(int[] i) 这个方法

        int[] arr = null;
        Object o = arr;
        defaultInAction.confuse(o); //会调用 confuse(Object o) 的方法
    }

    /**
     * 演示
     */
    private static void demonstration() {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }

    /**
     * 混淆
     * @param o
     */
    private void confuse(Object o) {
        System.out.println("object");
    }

    private void confuse(int[] i) {
        System.out.println("int[]");
    }

    private interface A {
        int size();

        default boolean isEmpty() {
            return size() == 0;

        }
    }

    private interface D {
        default void hello() {
            System.out.println("========D.Hello=========");
        }
    }

    private interface B extends D {
        @Override
        default void hello() {
            System.out.println("========B.Hello=========");
        }
    }

    private static class C implements B, D {
        public void hello() {
            System.out.println("========D.Hello=========");
            //            B.super.hello();
            //            D.super.hello();
        }
    }


}
