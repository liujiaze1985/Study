package com.ljz.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright © 2020年03月02日 liujiaze. All rights reserved.
 * @Project: javaStudy
 * @Package: com.ljz.framework.annotation
 * @Description:
 * @author: liujiaze
 * @date: 2020年03月02日 14:54
 * @version: V1.0
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FYRequestParam {
    String value() default "";
}
