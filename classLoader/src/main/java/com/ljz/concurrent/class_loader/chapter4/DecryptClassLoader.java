package com.ljz.concurrent.class_loader.chapter4;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader.chapter4
 * @Description: 自定义加解密类加载器
 * @author: liujiaze
 * @date: 2019年12月23日 16:25
 * @version: V1.0
 */
public class DecryptClassLoader extends ClassLoader {
    private final static String DEFAULT_DIR = "D:\\work\\classes\\classloader3";

    private String dir = DEFAULT_DIR;

    public void setDir(String dir) {
        this.dir = dir;
    }

    public DecryptClassLoader() {
        super();
    }

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + "not found under " + dir);
        }
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }


        return this.defineClass(name, classBytes, 0, classBytes.length); //名字，字节数组 ，从0开始读取，长度;
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(classFile)
        ) {
            int data;
            while ((data = fis.read())!=-1) {
                baos.write(data^EncryptUtils.ENCRYPT_FACTOR);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }

    }
}
