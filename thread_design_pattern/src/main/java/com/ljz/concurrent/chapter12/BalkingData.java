package com.ljz.concurrent.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Copyright © 2019年12月19日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.chapter12
 * @Description: Balking对应一个文件，当有新的内容进来的时候，往文件写内容，
 *              如果没有新的内容进来，就不会进行写操作 ，例如保存文件，如果文件没有变化 ，那就不会去再保存
 *              change，save，方法必须要成对出现，否则多次change，一次save，会导致change的数据丢失
 *
 * @author: liujiaze
 * @date: 2019年12月19日 17:11
 * @version: V1.0
 */
public class BalkingData {

    private final String fileName;
    private String content;
    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true; //因为给了新的内容
    }

    /**
     * 主动发生变化
     * @param newContent
     */
    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    /**
     *
     *  轮询
     *  查看是否变化，如果没有变化，返回
     *  如果有变化，处理，将状态设置为false，表示已经处理了
     */
    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        //相当于将文件保存
        System.out.println(Thread.currentThread().getName() + " calls do save , content = " + content);
        try (Writer writer = new FileWriter(fileName, true)) {

            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
