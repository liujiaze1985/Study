package com.ljz.java8.lambda_expression.stream_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年11月20日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description: stream_ 升级的API 操作collections 和 source等， 可以并行处理 底层使用了Fork join， 不需要关心并行的细节
 * @author: LiuJiaZe
 * @date: 2019年11月20日 19:02
 * @version: V1.0
 */
public class SimpleStream {
    public static void main(String[] args) {
        //获取菜单
        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        //        List<String> dishNamesByCollections = getDishNamesByCollections(menu);
        //        System.out.println(dishNamesByCollections);
        List<String> dishNamesByStream = getDishNamesByStream(menu);
        System.out.println(dishNamesByStream);

    }

    /**
     * stream_ 方式
     */
    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return menu.stream().filter(d -> {
         /*   try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
*/
                    return d.getCalories() < 400;
                }
        ).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).limit(2).collect(toList());

    }

    /**
     * 传统方法
     */
    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();
        //filter and get calories less 400
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalories.add(d);
            }
        }

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //sort
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));
        List<String> dishNameList = new ArrayList<>();

        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }

        return dishNameList;
    }
}
