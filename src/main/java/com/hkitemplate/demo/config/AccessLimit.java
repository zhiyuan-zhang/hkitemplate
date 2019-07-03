package com.hkitemplate.demo.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-07-03 14:09
 * @Description:
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    int time() default 10;
    int max() default 5;
    boolean verifyToken() default true;

}
