package com.ljz.concurrent.class_loader.chapter7;

import com.ljz.concurrent.class_loader.chapter3.MyClassLoader;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter7
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月23日 19:48
 * @version: V1.0
 */
public class ThreadContextClassLoader {





    public static void main(String[] args) throws ClassNotFoundException {
        //demonstrate();
        //demonstrate1();
        demonstrate2();

    }

    private static void demonstrate2() throws ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            Connection conn = DriverManager.getConnection(""); //此时编程上下文是Application ，Driver的ClassLoader是根，
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void demonstrate1() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println(contextClassLoader);

        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

    /**
     * main线程的上下文
     */
    private static void demonstrate() {
        //
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println(contextClassLoader);
        /**
         * sun.misc.Launcher$AppClassLoader@42a57993
         */}
}
