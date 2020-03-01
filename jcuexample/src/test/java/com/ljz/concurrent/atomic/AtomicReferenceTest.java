package com.ljz.concurrent.atomic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;

/**
 * Copyright © 2019年12月26日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.concurrent.atomic
 * @Description:
 * @author: liujiaze
 * @date: 2019年12月26日 20:24
 * @version: V1.0
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        //定义一个原子操作对象
        AtomicReference<Simple> atomic = new AtomicReference<>();
        AtomicReference<Simple> atomic1 = new AtomicReference<>(new Simple("Alex", 10));
        //对象是引用类型，也是4个字节
        System.out.println(atomic.get());
        System.out.println(atomic1.get());
        boolean result = atomic1.compareAndSet(new Simple("sdfs", 12), new Simple("sdfs", 234));
        System.out.println(result);
        System.out.println(atomic1.get());

        //点击鼠标事件，发生变化
        JButton button = new JButton();
        //默认值
        AtomicReference<Simple> atomic2 = new AtomicReference<>(new Simple("Alex", 10));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atomic2.set(new Simple("qwe", 324));
            }
        });

    }

    static class Simple {
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
