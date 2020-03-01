package com.ljz.java8.lambda_expression.stream_.stream_in_action;

/**
 * Copyright © 2019年11月23日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_.streamInAction
 * @Description: 业务员
 * @author: LiuJiaZe
 * @date: 2019年11月23日 18:01
 * @version: V1.0
 */
public class Trader {

    private final String name;
    private final String city;

    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}
