package com.xiya.demo.controller;

import com.xiya.demo.annotations.ApiLogin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liguanghui02
 * @date 2020/3/25
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiLogin
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
