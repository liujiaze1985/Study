package com.ljz.concurrent.class_loader.chapter4;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter4
 * @Description: 简单加密解密
 * @author: liujiaze
 * @date: 2019年12月23日 16:02
 * @version: V1.0
 */
public class SimpleEncrypt {
    private final static String plain = "Hello ClassLoader";
    private final static byte ENCRYPT_FACTOR = (byte) 0xff;

    public static void main(String[] args) {
        byte[] codes = demonstrate();
        demonstrate1(codes);

    }

    /**
     * 解密
     */
    private static void demonstrate1(byte[] code) {


        byte[] decrypt = new byte[code.length];

        for (int i = 0; i < code.length; i++) {
            decrypt[i] = (byte) (code[i]^ENCRYPT_FACTOR);
        }

        System.out.println(new String(decrypt));

    }

    /**
     * 加密
     */
    private static byte[] demonstrate() {
        byte[] bytes = plain.getBytes();//因操作系统 和JVM版本有差异

        byte[] encrypt = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            encrypt[i] = (byte) (bytes[i]^ENCRYPT_FACTOR);
        }

        System.out.println(new String(encrypt));

        return encrypt;
    }
}
