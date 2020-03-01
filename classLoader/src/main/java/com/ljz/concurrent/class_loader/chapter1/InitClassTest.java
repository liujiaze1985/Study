package com.ljz.concurrent.class_loader.chapter1;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter1
 * @Description: 静态语句块只能访问到定义在静态语句块之前的变量，定义在他之后的变量，只能赋值，不能访问
 * @author: liujiaze
 * @date: 2019年12月23日 11:47
 * @version: V1.0
 */
public class InitClassTest {
    /**
     *  1。类加载过程中的最后一步
     *  2。~是执行构造函数<clinit>()方法的过程
     *  3。<clinit>()方法是由编译器自动收集类中的所有变量的赋值动作和静态语句块中的语句合并产生的
     *  4。静态语句块只能访问到定义在静态语句块之前的变量，定义在他之后的变量，只能赋值，不能访问
     *  5。<clinit>()方法与类的构造函数有点区别，它不需要显示 的调用父类的构造函数，
     *  虚拟机会保证在子类的<clinit>()执行之前 ，先执行父类的<clinit>()，因此在虚拟机中首先被 执行的是Object 的<clinit>()方法
     *  6。由于父类的<clinit>()方法法要先执行，也就意味着父类中定义的静态语句块，要优先于子类
     *
     */

    public static int x = 10;

    static {
        //System.out.println(x);
        //x = 10 + 1;
        // 静态语句块只能访问到定义在静态语句块之前的变量，定义在他之后的变量，只能赋值，不能访问
        //y = 200;
        // 以下行报错
        //System.out.println(y);

    }

    public static int y = 10;


    public static void main(String[] args) {
        System.out.println(Sub.x);
    }
}

class Parent {
    static{
        System.out.println("Parent");
    }
}

class Sub extends Parent{
    public static int x = 100;
    static{
        System.out.println("Sub");
    }



}