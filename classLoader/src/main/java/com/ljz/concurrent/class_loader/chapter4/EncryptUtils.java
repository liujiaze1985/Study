package com.ljz.concurrent.class_loader.chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter4
 * @Description: 对文件加密
 *                 工具类，不可修改，不可继承
 * @author: liujiaze
 * @date: 2019年12月23日 16:14
 * @version: V1.0
 */
public final class EncryptUtils {

    public final static byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtils() {
        //empty
    }


    /**
     *
     * @param source 源地址
     * @param target 输出地址
     */
    public static void doEncrypt(String source, String target) {
        try (FileInputStream fis = new FileInputStream(source);
                FileOutputStream fos = new FileOutputStream(target)
        ) {
            int data;
            //读取
            while ((data = fis.read()) != -1) {
                fos.write(data ^ ENCRYPT_FACTOR); //异或 加密
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        doEncrypt("D:\\work\\classes\\classloader3\\com\\ljz\\concurrent\\class_loader\\chapter3\\MyObject.class",
                "D:\\work\\classes\\classloader3\\com\\ljz\\concurrent\\class_loader\\chapter3\\MyObject1.class");
    }
}
