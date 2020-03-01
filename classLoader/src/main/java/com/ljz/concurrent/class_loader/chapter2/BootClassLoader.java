package com.ljz.concurrent.class_loader.chapter2;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter2
 * @Description: 根加载器验证
 * @author: liujiaze
 * @date: 2019年12月23日 12:21
 * @version: V1.0
 */
public class BootClassLoader {
    public static void main(String[] args) {
        System.out.println( System.getProperty("sun.boot.class.path"));

        /**
         * D:\Development\java\jdk\jdk1.8\jre\lib\resources.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\rt.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\sunrsasign.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jsse.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jce.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\charsets.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jfr.jar;
         * D:\Development\java\jdk\jdk1.8\jre\classes
         */
    }
}
