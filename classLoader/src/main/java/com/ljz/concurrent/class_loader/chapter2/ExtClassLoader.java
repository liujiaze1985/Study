package com.ljz.concurrent.class_loader.chapter2;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter2
 * @Description: 验证 扩展（Extension）类加载器
 * @author: liujiaze
 * @date: 2019年12月23日 12:25
 * @version: V1.0
 */
public class ExtClassLoader {
    public static void main(String[] args) {
        System.out.println( System.getProperty("java.ext.dirs"));
        /**
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext;
         * C:\Windows\Sun\Java\lib\ext
         */
    }
}
