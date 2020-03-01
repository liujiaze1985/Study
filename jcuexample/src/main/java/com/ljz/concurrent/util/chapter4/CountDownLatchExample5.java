package com.ljz.concurrent.util.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2020年02月04日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.util.chapter4
 * @Description:


 *               将多个关系型数据库(DB2,ORACLE,MYSQL...)中的数据抓取到数据湖中(Hive集群)用于后续数据清洗，版本控制，分析等使用
 *               1: 数据抓取
 *               2: 每个表(table)需要做数据验证（record count , column schema, size , checksum）
 *               3: 每个Event 管理 多个(table)
 *               4: 完成数据验证 通知event
 *               5: 所有event完成后 通知 event管理组
 *               6: 将所有记录update到记录表中，每一检查项对应一张表
 *               6.1:将每张表的多个检查项都完成后，批量记录数据
 *              CountDownLatchExample5解决CountDownLatchExample4中存在的问题：
 *                1:每次完成都要update数据,效率很低
 *                2:不知道table的状态是否成功
 * @author: liujiaze
 * @date: 2020年02月04日 11:59
 * @version: V1.0
 */
public class CountDownLatchExample5 {
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
            //每个event对应一个taskGroup
            TaskGroup taskGroup = new TaskGroup(event, tables.size());

            //判断table中的检查点
            for (Table table : tables) {
                //++++++++++++++++++++++++++++++++++
                TaskBatch taskBatch = new TaskBatch(taskGroup, 2);

                //++++++++++++++++++++++++++++++++++
                TrustSourceColumns columnsRunnable = new TrustSourceColumns(table, taskBatch);
                TrustSourceRecordCount recordCountRunnable = new TrustSourceRecordCount(table, taskBatch);

                pool.submit(columnsRunnable);
                pool.submit(recordCountRunnable);

            }

        }
    }

    interface Watcher {

        //void startWatch();

        void done(Table table);
    }

    /**
     * 处理多个表
     */
    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    static class TaskBatch implements Watcher {
        private CountDownLatch countDownLatch;
        private TaskGroup taskGroup;

        public TaskBatch(TaskGroup taskGroup, int size) {
            this.taskGroup = taskGroup;
            this.countDownLatch = new CountDownLatch(size);
        }

        //@Override
        //public void startWatch() {
        //
        //}

        @Override
        public void done(Table table) {
            countDownLatch.countDown();//减1
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
                taskGroup.done(table); //通知group 其中一个表已经做完
            }

        }
    }

    /**
     * 用来管理event
     */
    static class TaskGroup implements Watcher {
        private CountDownLatch countDownLatch;
        private Event event;

        public TaskGroup(Event event, int size) {
            this.event = event;
            this.countDownLatch = new CountDownLatch(size);
        }

        //@Override
        //public void startWatch() {
        //
        //}

        @Override
        public void done(Table table) {
            countDownLatch.countDown();//减1
            if (countDownLatch.getCount() == 0) {
                System.out.println("All of table done in event: " + event.id);
            }
        }
    }

    /**
     * 数据表
     */
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

        @Override
        public String toString() {
            return "Table{" + "tableName='" + tableName + '\'' + ", sourceRecordCount=" + sourceRecordCount + ", targetCount=" + targetCount + ", sourceColumnSchema='" + sourceColumnSchema + '\'' + ", targetColumnSchema='" + targetColumnSchema + '\'' + '}';
        }
    }

    /**
     * SourceRecord 执行任务
     */
    static class TrustSourceRecordCount implements Runnable {
        private final Table table;
        //+++++++++++++++++++
        private final TaskBatch taskBatch;
        //+++++++++++++++++++

        public TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;//完全信任source
            //System.out.println("The table " + table.tableName + " target record count capture done  and update the data.");
            //+++++++++++++++++++
            taskBatch.done(table); //工作结束，发送通知
            //+++++++++++++++++++
        }
    }

    /**
     * SourceColumns  执行任务
     */
    static class TrustSourceColumns implements Runnable {
        private final Table table;
        //+++++++++++++++++++
        private final TaskBatch taskBatch;

        //+++++++++++++++++++
        public TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumnSchema = table.sourceColumnSchema;//完全信任source
            //System.out.println("The table " + table.tableName + " target columns capture done and update the data.");
            taskBatch.done(table); //工作结束，发送通知
        }

    }
}
/**
 *若干离散任务，加逻辑关系
 *  countDownLatch.countDown();//减1
 *  if (countDownLatch.getCount() == 0)
 *
 */