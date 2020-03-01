package com.ljz.concurrent.class_loader.chapter1;

import java.util.Random;

/**
 * Copyright © 2019年12月22日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader
 * @Description: 类的主动使用
 * @author: liujiaze
 * @date: 2019年12月22日 17:26
 * @version: V1.0
 */
public class ClassActiveUse {
        static {
            System.out.println("ClassActiveUse 初始化");
        }


    public static void main(String[] args) {
        // java程序对类的主动使用
        //new ，直接使用
        //new Obj();

        //访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
        //System.out.println(Obj.salary);
        //System.out.println(I.a);

        //调用静态方法
        //Obj.printSalary();

        //反射某个类
        //try {
        //    Class.forName("com.ljz.concurrent.class_loader.chapter1.Obj");
        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}

        //初始化一个子类
        //new Child();
        //System.out.println(Child.age);


        //启动类 ClassActiveUse 的 静态代码块


        //特殊情况
        //子类访问父类的静态变量，父类初始化，子类不初始化
        //System.out.println(Child.salary);

        //定义引用数组，不会初始化
        //Obj[] arrays = new Obj[10];

        //final static 修饰的 为常量，编译时放入常量池，引用时 类不会初始化
        //System.out.println(Obj.salary1);

        //final 修饰的复杂类型，在编译期间无法计算得出，会初始化类
        //System.out.println(Obj.x);





    }
}

class Obj {
    static {
        System.out.println("Obj 被初始化");
    }

    public  static long salary = 100000L;
    public final static long salary1 = 100000L;

    public  final static int x = new Random().nextInt(100);

    public static void printSalary() {
        System.out.println(salary);
    }
}

class Child  extends Obj{
    static {
        System.out.println("Child 被初始化");
    }

    public static int age = 32;
}


interface I {
    int a = 10; //默认为 public final static
}
/**
 *访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
 * 1。对某个类的静态变量进行读写  -> class
 * 2. 对接口中静态变量进行读取   -> interface
 *
 */