package com.ljz.concurrent.chapter5;

/**
 * Copyright © 2019年12月15日 LiuJiaZe. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter5
 * @Description: 门 ：共享资源
 * @author: LiuJiaZe
 * @date: 2019年12月15日 18:09
 * @version: V1.0
 */
public class Gate {
    private int counter = 0;
    private String name = "NoBody"; //名称以地名首字母命名 例如：甘肃人为 甘佬
    private String address = "NoWhere";

    /**
     * 临界值
     *
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        /*race 竞争*/
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        if (this.name.charAt(0)!= this.address.charAt(0)) {
            System.out.println("*************BROKEN**************" + toString());
        }
    }

    public synchronized String toString() {
        return "No." + counter +" : " +name +" , " + address; //第几个人来自哪里
    }
}
/**
 * 不论读写都用了锁，变成了串行化执行，使用程序的运行效率比较低，
 * 可以通过读写锁分离提升性能
 *
 */