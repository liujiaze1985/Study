package com.ljz.concurrent.class_loader.chapter6;

import com.ljz.concurrent.class_loader.chapter5.SimpleClassLoader;
import com.ljz.concurrent.class_loader.chapter5.SimpleObject;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter6
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月23日 17:46
 * @version: V1.0
 */
public class RuntimePackage {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        demonstrate();
    }

    /**
     * 自定义加载器加载
     * @throws ClassNotFoundException
     */
    private static void demonstrate() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        Class<?> aClass = simpleClassLoader.loadClass("com.ljz.concurrent.class_loader.chapter5.SimpleObject");
        //System.out.println(aClass.getClassLoader());
        SimpleObject simpleObject = (SimpleObject) aClass.newInstance();

    }

}
