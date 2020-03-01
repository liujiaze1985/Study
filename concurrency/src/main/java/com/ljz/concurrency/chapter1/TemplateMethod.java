package com.ljz.concurrency.chapter1;

/**
 * Copyright © 2019年12月05日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.concurrency.chapter2
 * @Description: 模板方法
 * @author: LiuJiaZe
 * @date: 2019年12月05日 13:04
 * @version: V1.0
 */
public class TemplateMethod {
    public final void print(String message) {
        System.out.println("#############################");
        wrapPrint(message);
        System.out.println("#############################");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("++++++" + message + "++++++++");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("**********" + message + "**********");
            }
        };
        t2.print("Hello Thread");

    }
}
