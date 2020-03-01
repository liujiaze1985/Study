package com.ljz.concurrent.class_loader.chapter4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter4
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月23日 16:48
 * @version: V1.0
 */
public class DecryptClassLoaderTest {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        DecryptClassLoader classLoader = new DecryptClassLoader();
        Class<?> aClass = classLoader.loadClass("com.ljz.concurrent.class_loader.chapter3.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});
        Object result = method.invoke(o, new Object[]{});
        System.out.println(result);



    }

}
