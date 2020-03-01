package java.lang;

/**
 * Copyright © 2019年12月22日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: java.lang
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月22日 16:54
 * @version: V1.0
 */
public class String1 {
    static{
        System.out.println("my custom string class.");
    }

    private static int i = 1;

    private int x;

    private Object obj = new Object();

    public String1(int x) {
        this.x = 10;
    }

    public int getValue() {
        this.obj.hashCode(); // obj 链接-解析：把类中的符号引用转换为直接引用
        return 1;
    }
}
//二进制 文件中的魔术因子
    //魔术因子 代表了文件类型


//i 在链接的准备阶段已经被分配了内存，并初始化为默认值 ,在初始化阶段，将初始值0赋予i

//obj