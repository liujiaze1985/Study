package com.ljz.concurrent.class_loader.chapter6;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter6
 * @Description: 命名空间
 * @author: liujiaze
 * @date: 2019年12月23日 17:26
 * @version: V1.0
 */
public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classloader  = NameSpace.class.getClassLoader() ;
        Class<?> aClass = classloader.loadClass("java.lang.String");
        Class<?> bClass = classloader.loadClass("java.lang.String");
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());
    }

}
