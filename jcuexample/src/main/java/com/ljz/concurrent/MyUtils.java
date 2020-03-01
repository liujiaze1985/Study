package com.ljz.concurrent;

import sun.misc.Unsafe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicStampedReference;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年12月02日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.util
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年12月02日 18:38
 * @version: V1.0
 */
public class MyUtils {
    /**
     * 获取类的方法列表
     * @param classz
     */
    public static void getMethds(Class classz) {
        Class<?> t = classz;
        Method[] methods = t.getDeclaredMethods();
        //获取方法列表
        //Optional.of(Arrays.asList(methods).stream().map(Method::getName).distinct().collect(toList()))
        //        .ifPresent(System.out::println);
        //获取方法定义
        Arrays.asList(methods).stream().map(Method::getName).forEach(System.out::println);
    }

    /**
     * 通过反射获取 Unsafe
     * @return
     */
    public static Unsafe getUnsafe() {

        //获取 private static final Unsafe theUnsafe;

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true); //可访问私有属性
            return (Unsafe) f.get(null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 读取class字节码文件
     * @return
     */
    public static byte[] loadClassContent(String path, String name) {
        File file = new File(path + File.separator.concat(name));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];//一次最多1024字节
            int len;//读了多少个，-1 表示读完
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);//
            }
            baos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long sizeOf(Object obj) {
        Unsafe unsafe = getUnsafe();
        /**
         * 拿出Field （子类可以覆写父类的Field）
         *
         */
        Set<Field> fields = new HashSet<>();
        Class<?> c = obj.getClass();
        while (c != Object.class) { //如果c不是最顶层的Object
            Field[] declaredFields = c.getDeclaredFields(); //获取声明的字段
            for (Field f : declaredFields) {
                //只要非静态的
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass(); //获取父类的
        }
        long maxOffSize = 0; //偏移量
        for (Field field : fields) {
            long offSet = unsafe.objectFieldOffset(field);

            if (offSet > maxOffSize) {
                maxOffSize = offSet; //拿到最大的
            }

        }

        //补位操作
        return ((maxOffSize / 8) + 1) * 8;

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MyUtils.getMethds(AtomicStampedReference.class);
    }

}
