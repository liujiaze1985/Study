package com.ljz.java8.lambda_expression.optional_;

import java.util.Optional;

/**
 * Copyright © 2019年11月24日 LiuJiaZe. All rights reserved.
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.optional_
 * @Description: Optional 解决空指针问题
 * @author: LiuJiaZe
 * @date: 2019年11月24日 17:19
 * @version: V1.0
 */
public class OptionalUsage {
    public static void main(String[] args) {
        //创建optional
        //1.1 empty()
        Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();
        //        insuranceOptional.get();//用empty()构建的直接调用会报NoSuchElementException
        //2. of() 不会抛异常
        Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());
        insuranceOptional1.get();
        //3. ofNullable 结合了empty 和 of 有值使用of 无值 使用empty
        Optional<Object> o = Optional.ofNullable(null);

        //optional get方法
        //1
        o.orElseGet(Insurance::new);
        //2
        o.orElse(new Insurance());
        //3
        //有值返回无值抛个异常
        //        o.orElseThrow(RuntimeException::new);
        //使用一个调用构造函数的异常， 不可用函数推导
        //        o.orElseThrow(() -> new RuntimeException("Not have reference"));

        // optional filter
        //Optional 如果为空 NoSuchElementException

        Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
        Insurance insurance = insuranceOptional2.filter(i -> i.getName() == null).get();
        System.out.println(insurance);

        // optional map 将原有optional再生成一个optional
        insuranceOptional2 = Optional.of(new Insurance());
        Optional<String> s = insuranceOptional2.map(i -> i.getName());
        System.out.println(s.orElse("empty Value"));


        //isPresent() 判断值是否存在
        System.out.println(s.isPresent());
        //是否有值， 有值的话输出
        s.ifPresent(System.out::println);
        System.out.println("================================================");
        //使用传统判断的方法
        System.out.println(getInsuranceName(null));
        //使用Optional
        System.out.println(getInsuranceNameByOptional(null));
    }


    private static String getInsuranceName(Insurance insurance) {
        if (null == insurance) {
            return "unknown";
        }
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
