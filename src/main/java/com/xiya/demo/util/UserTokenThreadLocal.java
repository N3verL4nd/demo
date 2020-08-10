package com.xiya.demo.util;


import com.xiya.demo.vo.UserLoginModel;

/**
 * @author N3verL4nd
 * @date 2020/3/25
 */
public class UserTokenThreadLocal {
    private static ThreadLocal<UserLoginModel> contents = new ThreadLocal<>();

    public static void set(UserLoginModel userLoginModel) {
        contents.set(userLoginModel);
    }

    public static UserLoginModel get() {
        return contents.get();
    }

    public static void clear() {
        contents.remove();
    }
}
