package com.ljz.concurrent.util.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2020年02月04日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter4
 * @Description: 将多个关系型数据库(DB2,ORACLE,MYSQL...)中的数据抓取到数据湖中(Hive集群)用于后续分析等使用
 *               1: 数据抓取
 *               2: 每个表(table)需要做数据验证（record count , column schema, size , checksum）
 *               3: 每个Event 管理 多个(table)
 *               4: 完成数据验证 通知event
 *               5: 所有event完成后 通知 event管理组
 *               6: 将所有记录update到记录表中
 * @author: liujiaze
 * @date: 2020年02月04日 11:59
 * @version: V1.0
 */
public class CountDownLatchExample4 {
    private final static Random random = new Random(System.currentTimeMillis());

    /**
     * 哪个event 处理哪些表
     * @param event
     * @return
     */
    private static List<Table> capture(Event event) {

        List<Table> list = new ArrayList<Table>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000));
        }
        return list;
    }

    public static void main(String[] args) {
        Event[] evnents = {new Event(1), new Event(2)};
        //用于并行执行
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (Event event : evnents) {
            List<Table> tables = capture(event);
            //判断table中的检查点
            for (Table table : tables) {
                TrustSourceColumns columnsRunnable = new TrustSourceColumns(table);
                TrustSourceRecordCount recordCountRunnable = new TrustSourceRecordCount(table);
                pool.submit(columnsRunnable);
                pool.submit(recordCountRunnable);

            }

        }
    }

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name='a'><column name = 'col1' type = 'varchar2'></column></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }
    }

    static class TrustSourceRecordCount implements Runnable {
        private final Table table;

        public TrustSourceRecordCount(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;//完全信任source
            System.out.println("The table " + table.tableName + " target record count capture done  and update the data.");
        }
    }

    static class TrustSourceColumns implements Runnable {
        private final Table table;

        public TrustSourceColumns(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;//完全信任source
            System.out.println("The table " + table.tableName + " target columns capture done and update the data.");
        }
    }
}
