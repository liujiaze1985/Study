package com.ljz.concurrent.class_loader.chapter2;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter2
 * @Description: 验证系统类加载器
 * @author: liujiaze
 * @date: 2019年12月23日 12:26
 * @version: V1.0
 */
public class AppClassLoader {



    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.ljz.concurrent.class_loader.chapter2.AppClassLoader");
            System.out.println(aClass.getClassLoader());//由谁加载
            /**
             * sun.misc.Launcher$AppClassLoader@42a57993
             */


            System.out.println(aClass.getClassLoader().getParent());
            /**
             * sun.misc.Launcher$ExtClassLoader@74a14482
             */

            System.out.println(aClass.getClassLoader().getParent().getParent());
            /**
             * null 由c++写的
             */
        } catch (ClassNotFoundException e) {//无法找到类的异常
            e.printStackTrace();
        }


        //System.out.println(System.getProperty("java.class.path"));
        /**
         * D:\Development\java\jdk\jdk1.8\jre\lib\charsets.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\deploy.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\javaws.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jce.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jfr.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jfxswt.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\jsse.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\management-agent.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\plugin.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\resources.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\rt.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\access-bridge-64.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\cldrdata.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\dnsns.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\jaccess.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\jfxrt.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\localedata.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\nashorn.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\sunec.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\sunjce_provider.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\sunmscapi.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\sunpkcs11.jar;
         * D:\Development\java\jdk\jdk1.8\jre\lib\ext\zipfs.jar;
         * D:\work\ideaWorkspace\javaStudy\classLoader\target\classes;
         * D:\Development\java\IDE\IntelliJ IDEA 14.1.7\lib\idea_rt.jar
         */


        //测试自定义java.lang.String 无法调用getValue()方法
        //java.lang.String s  = new java.lang.String();
        //System.out.println(s.getValue());


        try {
            Class<?> clazz = Class.forName("java.lang.String");
            System.out.println(clazz);
            System.out.println(clazz.getClassLoader()); //null classLoader 父委托机制
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
