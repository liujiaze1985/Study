package com.ljz.concurrent.class_loader.chapter3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Copyright © 2019年12月23日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.class_loader
 * @Description: 自定义类加载器
 * @author: liujiaze
 * @date: 2019年12月23日 13:14
 * @version: V1.0
 */
public class MyClassLoader extends ClassLoader {
    private final static String DEFAULT_DIR = "D:\\work\\classes\\classloader1";

    //允许调用者传地址
    private String dir = DEFAULT_DIR;


    //classLoader 的名字
    private String classLoaderName;


    public MyClassLoader() {
        super();
    }

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }


    /**
     * 可以指定父加载器
     * @param classLoaderName
     * @param parent
     */
    public MyClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }


    //实现findClass

    /**
     *

     * @param name
     *   "java.lang.String"
     *   "javax.swing.JSpinner$DefaultEditor" 内部类
     *   "java.security.KeyStore$Builder$FileBuilder$1"
     *   "java.net.URLClassLoader$3$1"
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        //String classPath = name.replaceAll(".", "/");
        String classPath = name.replace(".","/");
        System.out.println("classPath= >" + classPath);
        File classFile = new File(dir, classPath+".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + "not found under " + dir);
        }
        //当文件存放到一个数组里
        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("load the class " + name + " failed");
        }


        return this.defineClass(name, classBytes, 0, classBytes.length); //名字，字节数组 ，从0开始读取，长度
    }

    /**
     * 把文件变为一个数组
     * 将文件输入流变为内存输出流
     *
     * @param classFile
     * @return
     */
    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(classFile)
        ) {
            byte[] buffer = new byte[1024];//一次最多1024字节
            int len;//读了多少个，-1 表示读完
            while ((len = fis.read(buffer)) != -1) {
                 baos.write(buffer,0,len);//
            }
            baos.flush();
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
