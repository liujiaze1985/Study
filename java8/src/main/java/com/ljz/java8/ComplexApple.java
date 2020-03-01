package com.ljz.java8;

/**
 * Copyright © 2019年11月20日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月20日 18:35
 * @version: V1.0
 */
public class ComplexApple {
    private String name;
    private String color;
    private long weight;

    public ComplexApple(String color, String name, long weight) {
        this.color = color;
        this.name = name;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ComplexApple{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
