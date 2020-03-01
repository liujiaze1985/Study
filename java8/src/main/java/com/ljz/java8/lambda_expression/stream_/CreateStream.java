package com.ljz.java8.lambda_expression.stream_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Copyright © 2019年11月22日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_
 * @Description:
 * @author: LiuJiaZe
 * @date: 2019年11月22日 11:33
 * @version: V1.0
 */
public class CreateStream {
    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        System.out.println("==============================================");
        createStreamFromValues().forEach(System.out::println);
        System.out.println("==============================================");
        createStreamFromArrays().forEach(System.out::println);
        System.out.println("==============================================");
        Stream<String> streamFromFile = createStreamFromFile();
        System.out.println(streamFromFile);
        System.out.println("==============================================");
        createStreamFromIterator().forEach(System.out::println);
        System.out.println("==============================================");
        createStreamFromGenerate().forEach(System.out::println);
        System.out.println("==============================================");
        createObjStreamFromGenerate().forEach(System.out::println);
    }

    /**
     * Generate the stream_ from collection. list.stream_ 保留原有list顺序
     */
    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "ales", "LiuJiaZe", "world", "stream_");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("hello", "ales", "LiuJiaZe", "world", "stream_");
    }

    private static Stream<String> createStreamFromArrays() {
        String[] strings = {"hello", "ales", "LiuJiaZe", "world", "stream_"};
        return Arrays.stream(strings);
    }

    /**
     * 读取文件内容创建流
     */
    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("d:\\Users\\ljz\\Documents\\test.txt");
        try {
            Stream<String> lines = Files.lines(path);
            //            lines.forEach(System.out::println);

            return lines;
        } catch (IOException e) {
            //            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    /**
     * iterate 创建的是一个无限的
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2).limit(10);
        return iterate;
    }

    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(10);
    }

    /**
     * 自定义生成obj stream_
     * @return
     */
    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjStream()).limit(10);
    }

    static class ObjStream implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name->" + index);
        }

        @Override
        public String toString() {
            return "ObjStream{" +
                    "index=" + index +
                    ", random=" + random +
                    '}';
        }
    }

    static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.name = name;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}