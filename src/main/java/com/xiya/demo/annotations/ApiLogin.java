package com.xiya.demo.annotations;

import java.lang.annotation.*;

/**
 * @author liguanghui02
 * @date 2020/3/25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLogin {
}
